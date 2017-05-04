package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by tlh on 2017/5/2.
 */
public class SimpleClientSocket {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 8080));
            OutputStream os = socket.getOutputStream();
            try {
                os.write("hello".getBytes());
                os.write("world".getBytes());
                os.write("exit".getBytes());
            } finally {
                os.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
