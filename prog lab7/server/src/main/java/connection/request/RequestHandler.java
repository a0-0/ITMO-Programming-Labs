package connection.request;

import exception.AuthException;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import transferobjects.Request;
import transferobjects.Response;

public interface RequestHandler {
    Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException, AuthException;
}
