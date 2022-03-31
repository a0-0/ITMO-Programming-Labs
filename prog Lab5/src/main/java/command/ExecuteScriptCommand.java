package command;

import main.ProductCollectionManager;
import exception.InvalidArgumentException;

import java.io.*;

public class ExecuteScriptCommand implements Command{
    ProductCollectionManager routeManager;
    String[] args;
    String fileName;

    ExecuteScriptCommand(ProductCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                fileName = args[0];
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
                if (CommandInvoker.scripts.contains(fileName)) {
                    throw new IllegalStateException("recursive script call: " + fileName);
                }
                CommandInvoker.addScript(fileName);

                CommandInvoker commandInvoker = new CommandInvoker(routeManager, reader);

                while (reader.ready()) {
                    String commands = reader.readLine();
                    commandInvoker.execute(commands);
                }
        } catch (IllegalStateException ise) {
            System.err.println("error while trying to execute the script");
        } catch (FileNotFoundException fnfe) {
            System.err.println("script file not found: " + fileName);
        } catch (IOException e) {
            System.err.println("error while reading the script: " + e.getMessage());
        }
        CommandInvoker.removeScript(fileName);
    }
}
