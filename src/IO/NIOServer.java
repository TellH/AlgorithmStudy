package IO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tlh on 2017/5/5.
 */
public class NIOServer {
    private Selector selector;
    private ExecutorService service = Executors.newCachedThreadPool();

    private void startServer() throws Exception {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), 8000));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                iterator.remove();
                if (sk.isAcceptable()) {
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {
                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                }
            }
        }
    }

    private void doWrite(SelectionKey sk) {
        service.execute(new Writer(sk));
    }

    private void doRead(SelectionKey sk) {
        service.execute(new Reader(sk));
    }

    private void disconnect(SelectionKey sk) {
        try {
            sk.channel().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new ResponseQueue()); // 每个请求绑定一个响应数据队列
            System.out.println("Accepted connection from " + clientChannel.socket().getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ResponseQueue {
        private Queue<ByteBuffer> sendQ;

        ResponseQueue() {
            sendQ = new LinkedList<>();
        }

        public Queue<ByteBuffer> getSendQ() {
            return sendQ;
        }

        public void enqueue(ByteBuffer buffer) {
            sendQ.add(buffer);
        }
    }

    private class Reader implements Runnable {
        private final SelectionKey sk;

        private Reader(SelectionKey sk) {
            this.sk = sk;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) sk.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 8); // 8M
            List<Byte> data = new ArrayList<>();
            try {
                while ((channel.read(buffer) != -1)) {
                    buffer.flip(); // Prepare for writing
                    while (buffer.hasRemaining()) {
                        data.add(buffer.get());
                    }
                    buffer.clear(); // Prepare for reading
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect(sk);
            }
            ResponseQueue sendQ = (ResponseQueue) sk.attachment();
            handleRequest(data, sendQ);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            // 强制 select() 立即返回
            selector.wakeup();
        }

        private void handleRequest(List<Byte> data, ResponseQueue sendQ) {

        }
    }

    private class Writer implements Runnable {
        private final SelectionKey sk;

        private Writer(SelectionKey sk) {
            this.sk = sk;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) sk.channel();
            Queue<ByteBuffer> sendQ = ((ResponseQueue) sk.attachment()).getSendQ();
            // 写数据

            sk.interestOps(SelectionKey.OP_READ);
        }
    }
}
