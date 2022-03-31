package request;

import model.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

public class RequestSenderImpl implements RequestSender{
    private OutputStream stream;

    public void initOutputStream(SocketChannel socketChannel) throws IOException {
        stream = socketChannel.socket().getOutputStream();
    }

    public void sendRequest(SocketChannel socketChannel, Request request) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(request);
        this.stream.write(byteStream.toByteArray());
        this.stream.flush();
    }
}
