package commands;

import exception.CommandExecutionException;

public interface Command {
    void execute() throws CommandExecutionException;
    default void setArgs(String[] args) {

    }
    boolean isProductRequired();
}
