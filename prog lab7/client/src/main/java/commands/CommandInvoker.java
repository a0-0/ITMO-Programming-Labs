package commands;

import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import model.Product;

import java.util.*;

public class CommandInvoker {
    private final Map<String, Command> commands = new HashMap<>();
    private static final Set<String> scripts = new HashSet<>();

    public Set<String> getScripts() {
        return scripts;
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void addScript(String name) {
        scripts.add(name);
    }

    public void removeScript(String name) {
        scripts.remove(name);
    }

    public void execute(String inputString, Product product) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            return;
        }
        Command command;
        String[] split = inputString.split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase().trim())) {
            command = commands.get(split[0].toLowerCase().trim());
            command.setArgs(args);

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
                throw new CommandNotFoundException("command not found" + ": " + split[0]);
            }
        }
    }
}
