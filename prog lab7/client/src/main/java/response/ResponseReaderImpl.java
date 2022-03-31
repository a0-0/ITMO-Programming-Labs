package response;

import transferobjects.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseReaderImpl implements ResponseReader{

    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        /*byte[] bytes = new byte[32768];
        InputStream stream = socketChannel.socket().getInputStream();
        stream.read(bytes);*/
        ByteBuffer byteBuffer = ByteBuffer.allocate(16384);
        byteBuffer.clear();
        int numRead;
        do {
            numRead = socketChannel.read(byteBuffer);
        } while ((numRead == 0));
        return deserializeRequest(byteBuffer.array());
    }

    private Response deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(byteStream);
        return (Response) stream.readObject();
    }
}
