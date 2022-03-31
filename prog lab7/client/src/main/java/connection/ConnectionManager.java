package connection;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface ConnectionManager {
    SocketChannel openConnection(String address, int port) throws IOException;
    SocketChannel getConnection();
    void closeConnection() throws IOException;
}
