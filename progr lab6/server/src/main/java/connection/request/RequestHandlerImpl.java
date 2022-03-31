package connection.request;

import command.CommandInvoker;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import log.Log;
import response.Creator;
import model.Request;
import model.RequestType;
import model.Response;

public class RequestHandlerImpl implements RequestHandler{
    private final Creator responseCreator;
    private final CommandInvoker commandInvoker;

    public RequestHandlerImpl(CommandInvoker commandInvoker, Creator responseCreator) {
        this.commandInvoker = commandInvoker;
        this.responseCreator = responseCreator;
    }

    public Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException {
        if (request.getType().equals(RequestType.PRODUCT_REQUEST)) {
            return handleProductRequest(request);
        } else {
            return handleCommandRequest(request);
        }
    }

    private Response handleProductRequest(Request request) throws CommandNotFoundException {
        Log.getLogger().info("Client asks if a collection object is needed to process this command");
        if (commandInvoker.checkProductRequirement(request.getUserString())) {
            Log.getLogger().info("Forming a request with a positive response");
            return responseCreator.createResponse("", true, true);
        } else {
            Log.getLogger().info("Forming a query with a negative answer");
            return responseCreator.createResponse("", true, false);
        }
    }

    private Response handleCommandRequest(Request request) throws CommandExecutionException, CommandNotFoundException {
        Log.getLogger().info(String.format("Try to process %s command", request.getUserString()));
        commandInvoker.execute(request.getUserString(), request.getProduct());
        return responseCreator.createResponse();
    }
}
