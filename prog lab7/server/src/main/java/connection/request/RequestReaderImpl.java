package connection.request;

import log.Log;
import transferobjects.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class RequestReaderImpl implements RequestReader{
    @Override
    public Request getRequest(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        buffer.clear();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        int numRead = 1;
        try {
            while (numRead > 0) {
                numRead = socketChannel.read(buffer);
                System.out.println(numRead);
            }
        } catch (IOException e) {
            selectionKey.cancel();
            socketChannel.close();
        }
        Request request = deserializeRequest(buffer.array());
        request.setSocketChannel(socketChannel);
        return request;
    }

    private Request deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException{
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Request) stream.readObject();
    }
}
