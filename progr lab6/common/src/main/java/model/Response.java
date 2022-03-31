package model;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -5622461857486946378L;

    private String message;
    private boolean success;
    private boolean productRequired;

    public Response(String message, boolean b, boolean b2) {
        this.message = message;
        this.success = b;
        this.productRequired = b2;
    }

    public Response() {
        message = "";
        success = true;
        productRequired = false;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isProductRequired() {
        return productRequired;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean b) {
        this.success = b;
    }

    public void setProductRequired(boolean productRequired) {
        this.productRequired = productRequired;
    }
}
