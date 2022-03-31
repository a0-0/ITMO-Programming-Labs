package command;

import exception.CommandExecutionException;

public interface ServerCommand {
    void execute() throws CommandExecutionException;
}
