package commands;

import client.AuthModule;
import exception.CommandExecutionException;

import java.io.IOException;

public class RegisterCommand extends AbstractCommand{
    private final AuthModule authModule;

    public RegisterCommand(AuthModule authModule) {
        this.authModule = authModule;
    }
    @Override
    public void execute() throws CommandExecutionException {
        try {
            authModule.register();
        } catch (IOException e) {
            throw new CommandExecutionException("unknown error");
        }
    }
}
