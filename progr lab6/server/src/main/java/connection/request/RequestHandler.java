package connection.request;

import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import model.Request;
import model.Response;

public interface RequestHandler {
    Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException;
}
