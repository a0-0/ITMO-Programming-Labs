package request;

import client.AuthModule;
import model.Product;
import transferobjects.Request;
import transferobjects.RequestType;

public class RequestCreator {
    private final AuthModule authModule;

    public RequestCreator(AuthModule authModule) {
        this.authModule = authModule;
    }

    public Request createProductRequest(String userString) {
        Request request = new Request(RequestType.PRODUCT_REQUEST, userString, null);
        request.setUser(authModule.getUser());
        return request;
    }

    public Request createCommandRequest(String userString, Product product) {
        Request request = new Request(RequestType.COMMAND_REQUEST, userString, product);
        request.setUser(authModule.getUser());
        return new Request(RequestType.COMMAND_REQUEST, userString, product);
    }
}
