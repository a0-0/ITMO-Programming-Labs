package connection.request;

import transferobjects.Request;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface RequestReader {
    Request getRequest(SelectionKey selectionKey) throws IOException, ClassNotFoundException;
}
