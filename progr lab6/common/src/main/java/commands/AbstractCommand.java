package commands;

import exception.CommandExecutionException;

abstract public class AbstractCommand implements Command {
    private final boolean productRequired;

    public AbstractCommand (boolean b) {
        productRequired = b;
    }

    public AbstractCommand () {
        productRequired = false;}

    public boolean isProductRequired() {
        return productRequired;
    }

    public abstract void execute() throws CommandExecutionException;
}
