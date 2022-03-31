package transferobjects;

import model.Product;
import user.User;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Request implements Serializable {
    private static final long serialVersionUID = -4287447999382808577L;

    private RequestType type;
    private RequestOpsState opsState;
    private String userString;
    private Product product;
    private User user;
    private transient SocketChannel socketChannel;

    public Request(RequestType type, String userString, Product product) {
        this.type = type;
        this.userString = userString;
        this.product = product;
    }

    public Request(RequestType type, User user) {
        this.type = type;
        this.user = user;
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

    public RequestOpsState getOpsState() {
        return opsState;
    }

    public User getUser() {
        return user;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
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

    public void setOpsState(RequestOpsState opsState) {
        this.opsState = opsState;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }


}
