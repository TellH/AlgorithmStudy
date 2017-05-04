package IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tlh on 2017/5/2.
 */
public class SimpleServerSocket {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            ServerSocket serverSocket = new ServerSocket();
            try {
                serverSocket.bind(new InetSocketAddress(8080));
                while (!Thread.currentThread().isInterrupted()) {//主线程死循环等待新连接到来
                    Socket socket = serverSocket.accept();
                    executor.execute(new RequestHandler(socket));//为新的连接创建新的线程
                }
            } finally {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class RequestHandler implements Runnable {
        private final Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                // 死循环处理读写事件
                InputStream is = null;
                StringBuilder sb = new StringBuilder();
                try {
                    is = socket.getInputStream();
                    try {
                        byte[] bytes = new byte[1024];
                        while (is.read(bytes) != -1) {
                            String str = new String(bytes);
                            sb.append(str);
                        }
                        handleRequest(sb.toString());
                    } finally {
                        is.close();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(String data) {
            // ...
        }
    }
}
