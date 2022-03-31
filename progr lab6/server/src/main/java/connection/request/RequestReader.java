package connection.request;

import model.Request;

import java.io.IOException;
import java.nio.channels.Selector;

public interface RequestReader {
    Request getRequest(Selector selector) throws IOException, ClassNotFoundException;
}
