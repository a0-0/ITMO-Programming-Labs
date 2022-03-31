package transferobjects;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Response implements Serializable {
    private static final long serialVersionUID = -5622461857486946378L;

    private String message;
    private boolean success;
    private boolean productRequired;
    private Object obj;
    private transient SocketChannel socketChannel;

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

    public Object getObj() {
        return obj;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
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

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}
