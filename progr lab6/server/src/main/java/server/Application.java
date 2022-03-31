package server;

import collection.ProductCollectionManager;
import collection.ProductLinkedListManager;
import command.*;
import connection.ConnectionListener;
import connection.ConnectionListenerImpl;
import exception.*;
import file.*;
import io.ConsoleIO;
import io.UserIO;
import log.Log;
import connection.request.RequestHandler;
import connection.request.RequestHandlerImpl;
import connection.request.RequestReader;
import connection.request.RequestReaderImpl;
import response.Creator;
import response.ResponseCreator;
import response.ResponseSender;
import response.ResponseSenderImpl;
import model.Request;
import model.Response;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;

public class Application {
    private boolean isRunning = true;

    public void start(String address, int port, String fileName) {
        Selector selector;
        Creator creator = new ResponseCreator();
        ProductCollectionManager productManager = new ProductLinkedListManager(creator);
        ProductReader reader = new XmlProductReader(productManager, fileName);
        ProductWriter writer = new XmlProductWriter(productManager, fileName);
        CommandHistory commandHistory = new CommandHistory();
        CommandInvoker commandInvoker = new CommandInvoker(commandHistory);

        try {
            reader.read();
        } catch (InvalidArgumentException e) {
            log.Log.getLogger().error(e.getMessage());
        }
        ConnectionListener connectionListener = new ConnectionListenerImpl();
        RequestReader requestReader = new RequestReaderImpl();
        RequestHandler requestHandler = new RequestHandlerImpl(commandInvoker, creator);
        ResponseSender responseSender = new ResponseSenderImpl();
        putCommands(commandInvoker, productManager, creator, commandHistory);
        putServerCommands(commandInvoker, writer, connectionListener);
        consoleStart(commandInvoker);

        try {
            connectionListener.openConnection(address, port);
        } catch (IOException e) {
            return;
        }

        while (isRunning) {
            try {

                try {
                    selector = connectionListener.listen();
                } catch (ClosedSelectorException e){
                    return;
                }
                Request request = requestReader.getRequest(selector);
                log.Log.getLogger().info("request received");
                try {
                    log.Log.getLogger().info("Processing request");
                    Response response = requestHandler.handleRequest(request);
                    responseSender.sendResponse(selector, response);
                } catch (CommandNotFoundException | CommandExecutionException e) {
                    Log.getLogger().error(e.getStackTrace());
                    Response response = new Response(e.getMessage(), false, false);
                    responseSender.sendResponse(selector, response);
                }
                Log.getLogger().info(request.toString());
            } catch (IOException | ClassNotFoundException ioe) {
                Log.getLogger().error(ioe);
                Log.getLogger().error(ioe.getStackTrace());
                try {
                    connectionListener.stop();
                    connectionListener.openConnection(address, port);
                } catch (IOException e) {
                    Log.getLogger().error(e.getStackTrace());
                    isRunning = false;
                }
            }
        }
    }

    private void consoleStart(CommandInvoker commandInvoker) {
        Thread consoleThread = new Thread(() -> {
            UserIO userIO = new ConsoleIO();
            while(!Thread.interrupted()) {
                userIO.printUserPrompt();
                try {
                    String str = userIO.readLine();
                    commandInvoker.execute(str);
                } catch (IOException | CommandNotFoundException | CommandExecutionException ioe) {
                    userIO.printErrorMessage(ioe.getMessage());
                }
            }
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }

    private void putCommands(CommandInvoker commandInvoker, ProductCollectionManager manager, Creator creator, CommandHistory commandHistory) {
        commandInvoker.addCommand("help", new HelpCommand(false, creator));
        commandInvoker.addCommand("info", new InfoCommand(manager, false));
        commandInvoker.addCommand("clear", new ClearCommand(manager, false));
        commandInvoker.addCommand("show", new ShowCommand(manager, false));
        commandInvoker.addCommand("remove_by_id", new RemoveByIdCommand(manager, false));
        commandInvoker.addCommand("history", new HistoryCommand(commandHistory, creator, false));
        commandInvoker.addCommand("add", new AddCommand(manager, true));
        commandInvoker.addCommand("update", new UpdateCommand(manager, true));
        commandInvoker.addCommand("insert_at", new InsertAtCommand(manager, true));
        commandInvoker.addCommand("remove_greater", new RemoveGreaterCommand(manager, true));
        commandInvoker.addCommand("sort", new SortCommand(manager, false));
        commandInvoker.addCommand("remove_any_by_unit_of_measure", new RemoveAnyByUnitOfMeasureCommand(manager, false));
        commandInvoker.addCommand("min_by_manufacturer", new MinByManufacturerCommand(manager, false));
        commandInvoker.addCommand("print_field_ascending_manufacturer", new PrintFieldAscendingManufacturerCommand(manager, false));
    }

    private void putServerCommands(CommandInvoker commandInvoker, ProductWriter productWriter, ConnectionListener connectionListener) {
        commandInvoker.addServerCommand("exit", new ExitCommand(productWriter, connectionListener, this));
        commandInvoker.addServerCommand("save", new SaveCommand(productWriter));
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
