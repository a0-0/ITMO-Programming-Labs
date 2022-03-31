package connection.request;

import command.CommandInvoker;
import exception.AuthException;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import log.Log;
import response.Creator;
import server.UserAuthModule;
import transferobjects.Request;
import transferobjects.RequestType;
import transferobjects.Response;

public class RequestHandlerImpl implements RequestHandler{
    private final Creator responseCreator;
    private final CommandInvoker commandInvoker;
    private final UserAuthModule userAuthModule;

    public RequestHandlerImpl(CommandInvoker commandInvoker, Creator responseCreator, UserAuthModule userAuthModule) {
        this.commandInvoker = commandInvoker;
        this.responseCreator = responseCreator;
        this.userAuthModule = userAuthModule;
    }

    public Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException, AuthException {
        Response response;
        if (request.getType().equals(RequestType.PRODUCT_REQUEST)) {
            response = handleProductRequest(request);
        } else if (request.getType().equals(RequestType.COMMAND_REQUEST)){
            response = handleCommandRequest(request);
        } else if (request.getType().equals(RequestType.AUTH_REQUEST)){
            response = handleAuthRequest(request);
        } else {
            response = handleRegisterRequest(request);
        }
        return response;
    }

    private Response handleProductRequest(Request request) throws CommandNotFoundException, AuthException {
        Log.getLogger().info("Client asks if a collection object is needed to process this command");
        if (request.getUser() == null || !userAuthModule.authUser(request.getUser())) {
            throw new AuthException("not authorized");
        }
        if (commandInvoker.checkProductRequirement(request.getUserString())) {
            Log.getLogger().info("Forming a request with a positive response");
            return responseCreator.createResponse("", true, true);
        } else {
            Log.getLogger().info("Forming a query with a negative answer");
            return responseCreator.createResponse("", true, false);
        }
    }

    private Response handleCommandRequest(Request request) throws CommandExecutionException, CommandNotFoundException, AuthException {
        Log.getLogger().info(String.format("Try to process %s command", request.getUserString()));
        if (request.getUser() == null || !userAuthModule.authUser(request.getUser())) {
            throw new AuthException("not authorized");
        }
        commandInvoker.execute(request.getUserString(), request.getProduct());
        return responseCreator.createResponse();
    }

    private Response handleAuthRequest(Request request) {
        boolean result = userAuthModule.authUser(request.getUser());
        return responseCreator.createResponse(userAuthModule.getReason(), result, result);
    }

    private Response handleRegisterRequest(Request request) {
        boolean result = userAuthModule.registerUser(request.getUser());
        return responseCreator.createResponse(userAuthModule.getReason(), result, result);
    }
}
