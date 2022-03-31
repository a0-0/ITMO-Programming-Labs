package connection;

import log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class ConnectionListenerImpl implements ConnectionListener {
    private Selector selector;

    private ServerSocketChannel serverChannel;

    private InetSocketAddress socketAddress;

    public Selector openConnection() throws IOException {
        selector = SelectorProvider.provider().openSelector();

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        serverChannel.socket().bind(socketAddress);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        return selector;
    }

    public void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void stop() throws IOException {

    }

    public Selector getSelector() {
        return selector;
    }

    public void setSocketAddress(InetSocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }
}
