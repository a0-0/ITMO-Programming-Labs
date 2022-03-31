package connection;

import java.io.IOException;
import java.nio.channels.Selector;

public interface ConnectionListener {
    void openConnection(String address, int port) throws IOException;
    Selector listen() throws IOException;
    void stop() throws IOException;
}
