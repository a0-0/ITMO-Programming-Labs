package commands;

import client.AuthModule;
import exception.CommandExecutionException;

import java.io.IOException;

public class AuthCommand extends AbstractCommand{
    private final AuthModule authModule;

    public AuthCommand(AuthModule authModule) {
        this.authModule = authModule;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            authModule.authorize();
        } catch (IOException e) {
            throw new CommandExecutionException("unknown error");
        }
    }
}
