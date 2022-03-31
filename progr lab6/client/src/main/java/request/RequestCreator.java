package request;

import model.Product;
import model.Request;
import model.RequestType;

public class RequestCreator {
    public Request createProductRequest(String userString) {
        return new Request(RequestType.PRODUCT_REQUEST, userString, null);
    }

    public Request createCommandRequest(String userString, Product product) {
        return new Request(RequestType.COMMAND_REQUEST, userString, product);
    }
}
