package response;

import model.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class ResponseReaderImpl implements ResponseReader{

    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        InputStream stream = socketChannel.socket().getInputStream();
        stream.read(bytes);
        return deserializeRequest(bytes);
    }

    private Response deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(byteStream);
        return (Response) stream.readObject();
    }
}
