package client;

import commands.ClientHelpCommand;
import commands.CommandInvoker;
import commands.ExecuteScriptCommand;
import commands.ExitCommand;
import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import io.ConsoleIO;
import io.ConsoleProductParser;
import io.SingleProductReader;
import io.UserIO;
import model.Request;
import model.RequestType;
import model.Response;
import request.RequestCreator;
import request.RequestSender;
import request.RequestSenderImpl;
import response.ResponseReader;
import response.ResponseReaderImpl;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Application {
    private final String address;
    private final int port;
    private final UserIO userIO;
    private final CommandInvoker commandInvoker;
    private final ConnectionManager connectionManager;
    private final RequestCreator requestCreator;
    private final RequestSender requestSender;
    private final ResponseReader reader;
    private final SingleProductReader productReader;
    private boolean isRunning = true;
    private SocketChannel socketChannel;


    public Application(String address, int port) {
        this.address = address;
        this.port = port;
        userIO = new ConsoleIO();
        commandInvoker = new CommandInvoker();
        connectionManager = new ConnectionManagerImpl();
        requestCreator = new RequestCreator();
        requestSender = new RequestSenderImpl();
        reader = new ResponseReaderImpl();
        productReader = new ConsoleProductParser(userIO);
        setCommands(commandInvoker);
    }

    public void setIsRunning(boolean b) {
        isRunning = b;
    }

    public void start() {
        try {
            socketChannel = connectionManager.openConnection(address, port);
        } catch (IOException e) {
            userIO.printErrorMessage("Server is unreachable");
            return;
        }
        while(isRunning) {
            userIO.printUserPrompt();
            String userString = "";
            try {
                userString = userIO.readLine();
                commandInvoker.execute(userString, null);
            } catch (CommandExecutionException executionException) {
                userIO.printErrorMessage("error during command execution" + ": " + executionException.getMessage());
            } catch (IOException ioe) {
                userIO.printErrorMessage(ioe.getMessage());
            } catch (CommandNotFoundException ise) {
                try {
                    Response response = communicateWithServer(userString, productReader);
                    userIO.printLine(response.getMessage());
                } catch (EOFException eofe) {
                    userIO.printErrorMessage("server sent too large response, can't process");
                } catch (IOException | ClassNotFoundException ioe) {
                    userIO.printErrorMessage("network error occurred");
                } catch (IllegalStateException e) {
                    userIO.printErrorMessage(e.getMessage());
                }
            }
        }
    }

    public Response communicateWithServer(String userString, SingleProductReader productReader) throws IOException, ClassNotFoundException {
        Request request = requestCreator.createProductRequest(userString);
        requestSender.initOutputStream(socketChannel);
        requestSender.sendRequest(socketChannel, request);
        Response response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());


        if (response.isProductRequired()) {
            request.setProduct(productReader.read());
        }

        request.setType(RequestType.COMMAND_REQUEST);
        requestSender.initOutputStream(socketChannel);
        requestSender.sendRequest(socketChannel, request);
        response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());

        return response;
    }

    private void setCommands(CommandInvoker commandInvoker) {
        commandInvoker.addCommand("exit", new ExitCommand(this));
        commandInvoker.addCommand("execute_script", new ExecuteScriptCommand(this, commandInvoker, userIO));
        commandInvoker.addCommand("client_help", new ClientHelpCommand(userIO));
    }
}
