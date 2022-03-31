package transferobjects;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class RequestOpsState implements Serializable {
    public static final int REGISTER = 1;
    public static final int CHANGEOPS = 2;
    public static final int DEREGISTER = 3;

    private SocketChannel socketChannel;
    private int type;
    private int ops;

    public RequestOpsState(SocketChannel channel, int type, int ops) {
        this.socketChannel = channel;
        this.type = type;
        this.ops = ops;
    }

    public RequestOpsState(SocketChannel channel, int type) {
        this.socketChannel = channel;
        this.type = type;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public int getOps() {
        return ops;
    }

    public int getType() {
        return type;
    }
}
