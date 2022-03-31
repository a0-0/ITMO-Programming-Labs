package model;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = -4287447999382808577L;

    private RequestType type;
    private String userString;
    private Product product;

    public Request(RequestType type, String userString, Product product) {
        this.type = type;
        this.userString = userString;
        this.product = product;
    }

    public String getUserString() {
        return userString;
    }

    public Product getProduct() {
        return product;
    }


    public RequestType getType() {
        return type;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + userString + '\'' +
                ", product=" + product +
                '}';
    }
}
