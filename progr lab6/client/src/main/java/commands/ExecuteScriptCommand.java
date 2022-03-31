package commands;

import client.Application;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import exception.InvalidArgumentException;
import io.ScriptProductParser;
import io.UserIO;
import model.Response;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {
    CommandInvoker commandInvoker;
    Application application;
    UserIO userIO;
    String[] args;
    String fileName;

    public ExecuteScriptCommand(Application application, CommandInvoker commandInvoker, UserIO userIO) {
        this.application = application;
        this.commandInvoker = commandInvoker;
        this.userIO = userIO;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    @Override
    public void execute() throws CommandExecutionException {
       try {
            if (args.length == 1) {
                fileName = args[0];
            } else {
                throw new InvalidArgumentException(String.format("expected %d arguments, received %d", 1, args.length));
            }
        } catch (InvalidArgumentException iae) {
            throw new CommandExecutionException(iae.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            if (commandInvoker.getScripts().contains(fileName)) {
                throw new IllegalStateException("recursive script call"+": " + fileName);
            }
            commandInvoker.addScript(fileName);

            while (reader.ready()) {
                String commands = reader.readLine();
                try {
                    commandInvoker.execute(commands, null);
                } catch (CommandNotFoundException e) {
                    try {
                        Response response = application.communicateWithServer(commands, new ScriptProductParser(reader, userIO));
                        userIO.printLine(response.getMessage());
                    } catch (EOFException eofe) {
                        userIO.printErrorMessage("server sent too large response, can't process");
                    } catch (IOException | ClassNotFoundException ioe) {
                        userIO.printErrorMessage("network error occurred"+": " + ioe.getMessage());
                    } catch (IllegalStateException ex) {
                        userIO.printErrorMessage(e.getMessage());
                    }
                } catch (CommandExecutionException executionException) {
                    userIO.printErrorMessage("error during command execution" +
                            ": " + executionException.getMessage());
                }
            }
            commandInvoker.removeScript(fileName);
        } catch (IllegalStateException ise) {
            userIO.printErrorMessage("script execution error");
        } catch (FileNotFoundException fnfe) {
            userIO.printErrorMessage("no script found"+": " + fileName);
        } catch (IOException e) {
            userIO.printErrorMessage("error while reading script"+": " + e.getMessage());
        }
    }
}
