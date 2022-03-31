package connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface ConnectionListener {
    Selector openConnection() throws IOException;
    void accept(SelectionKey key) throws IOException;
    void setSocketAddress(InetSocketAddress socketAddress);
    void stop() throws IOException;
}
