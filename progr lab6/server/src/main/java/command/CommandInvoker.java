package command;

import commands.Command;
import commands.ProductCommand;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import log.Log;
import model.Product;

import java.util.*;

public class CommandInvoker {

    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, ServerCommand> serverCommands = new HashMap<>();
    private final CommandHistory commandHistory;

    public CommandInvoker(CommandHistory commandHistory) {
        this.commandHistory = commandHistory;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void addServerCommand(String commandName, ServerCommand command) {
        serverCommands.put(commandName, command);
    }

    public void execute(String inputString, Product product) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error("effected eof");
            throw new CommandNotFoundException("effected eof");
        }
        Command command;
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase(Locale.ROOT))) {
            command = commands.get(split[0].toLowerCase(Locale.ROOT));
            command.setArgs(args);
            commandHistory.pushHistory(inputString);

            if(product == null) {
                command.execute();
            } else {
                ProductCommand productCommand = (ProductCommand) command;
                productCommand.execute(product);
            }
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException("command not found");
            } else {
                throw new CommandNotFoundException("command not found " + split[0]);
            }
        }
    }

    public void execute(String inputString) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error("effected eof");
            throw new CommandNotFoundException("effected eof");
        }
        ServerCommand command;
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(serverCommands.containsKey(split[0].toLowerCase())) {
            command = serverCommands.get(split[0].toLowerCase());
            command.execute();
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException("command not found");
            } else {
                throw new CommandNotFoundException("command not found" + ": " + split[0]);
            }
        }
    }

    public boolean checkProductRequirement(String str) throws CommandNotFoundException {
        String[] split = str.trim().split("\\s+");
        if (commands.get(split[0].toLowerCase()) == null) {
            throw new CommandNotFoundException("command not found" + ": " + split[0].toLowerCase());
        }
        return commands.get(split[0].toLowerCase()).isProductRequired();
    }
}
