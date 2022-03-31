package connection.request;

import log.Log;
import model.Request;

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
    private Selector selector;

    public Request getRequest(Selector selector) throws IOException, ClassNotFoundException{
        this.selector = selector;
        return deserializeRequest(readBytes());
    }

    private byte[] readBytes() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(4096);
        SocketChannel channel = null;
        while (channel == null) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    channel = (SocketChannel)key.channel();
                    channel.read(buf);
                    channel.register(selector, SelectionKey.OP_WRITE);
                }
                iterator.remove();
            }
        }
        return buf.array();
    }

    private Request deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException{
        Log.getLogger().info("Deserialization request");
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Request) stream.readObject();
    }
}
