package IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by tlh on 2017/5/2.
 */
public class ChannelServer {
    public static void main(String[] args) {
        blockingServer();
    }

    private static void blockingServer() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            SocketChannel sc = null;
            try {
                ssc.bind(new InetSocketAddress(8080));
                sc = ssc.accept();
                System.out.println("accept connection from:" + sc.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (sc.read(buffer) != -1) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    handleRequest(new String(bytes));
                    buffer.clear();
                }
            } finally {
                if (sc != null)
                    sc.close();
                ssc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nonBlockingServer() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            SocketChannel sc = null;
            try {
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress(1234));
                while ((sc = ssc.accept()) == null) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("try to accept again...");
                }
                System.out.println("accept connection from:" + sc.getRemoteAddress());

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (sc.read(buffer) != -1) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));
                    buffer.clear();
                }
            } finally {
                if (sc != null)
                    sc.close();
                ssc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(String data) {
    }
}
