package IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by tlh on 2017/5/2.
 */
public class BlockingChannelClient {
    public static void main(String[] args) {
        blockingClient();
    }

    private static void blockingClient() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 1234));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            writeString(buffer, sc, "hello");
            writeString(buffer, sc, "world");
            writeString(buffer, sc, "exit");
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nonBlockingClient() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1", 1234));
            while (!sc.finishConnect()) {
                System.out.println("connection has not finished,wait...");
                TimeUnit.SECONDS.sleep(1);
            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            writeString(buffer, sc, "hello");
            writeString(buffer, sc, "world");
            writeString(buffer, sc, "exit");
            sc.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeString(ByteBuffer buffer, SocketChannel sc, String str) {
        buffer.clear();
        buffer.put(str.getBytes()).flip();
        try {
            sc.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
