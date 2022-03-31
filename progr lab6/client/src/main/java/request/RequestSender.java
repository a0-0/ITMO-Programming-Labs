package request;

import model.Request;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface RequestSender {
    void initOutputStream(SocketChannel socketChannel) throws IOException;
    void sendRequest(SocketChannel socketChannel, Request request) throws IOException;
}
