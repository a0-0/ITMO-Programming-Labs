package server;

import collection.ProductCollectionManager;
import collection.ProductLinkedListManager;
import command.*;
import connection.ConnectionListener;
import connection.ConnectionListenerImpl;
import data.DAOFactory;
import data.ProductDAO;
import exception.*;
import io.ConsoleIO;
import io.UserIO;
import connection.request.RequestHandler;
import connection.request.RequestHandlerImpl;
import connection.request.RequestReader;
import connection.request.RequestReaderImpl;
import response.Creator;
import response.ResponseCreator;
import response.ResponseSender;
import response.ResponseSenderImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;

public class Application {
    private boolean isRunning = true;

    public void start(String address, int port, String fileName) {
        Selector selector;
        Creator creator = new ResponseCreator();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        UserAuthModule userAuthModule = new UserAuthModule(daoFactory.getUserDAO());
        ProductCollectionManager productManager = new ProductLinkedListManager(creator, productDAO, userAuthModule);
        CommandHistory commandHistory = new CommandHistory();
        CommandInvoker commandInvoker = new CommandInvoker(commandHistory);

        try {
            productManager.addProducts();
        } catch (PersistentException e) {
            System.err.println(e.getMessage());
            return;
        }
        ConnectionListener connectionListener = new ConnectionListenerImpl();
        connectionListener.setSocketAddress(new InetSocketAddress(address, port));
        RequestReader requestReader = new RequestReaderImpl();
        RequestHandler requestHandler = new RequestHandlerImpl(commandInvoker, creator, userAuthModule);
        ResponseSender responseSender = new ResponseSenderImpl();
        putCommands(commandInvoker, productManager, creator, commandHistory);
        putServerCommands(commandInvoker, connectionListener);
        try {
            Server server = new Server(connectionListener, requestReader, requestHandler, responseSender);
            new Thread(server).start();
            consoleStart(commandInvoker);
        } catch (IOException e) {
            e.printStackTrace();
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

    private void putServerCommands(CommandInvoker commandInvoker, ConnectionListener connectionListener) {
        commandInvoker.addServerCommand("exit", new ExitCommand(connectionListener, this));
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
