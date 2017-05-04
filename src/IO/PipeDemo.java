package IO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by tlh on 2017/5/2.
 * 在同一Java进程内，不同线程之间可以用单向管道进行通信。
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        //创建一个管道，并拿到管道两端的channel
        Pipe pipe = Pipe.open();
        WritableByteChannel writableByteChannel = pipe.sink();
        //创建一个线程从sink端写入数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        writableByteChannel.write(ByteBuffer.wrap("Hello, I am a new Thread".getBytes()));
                    } finally {
                        writableByteChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ReadableByteChannel readableByteChannel = pipe.source();
        //主线程从source端读取数据，并组成String打印
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (readableByteChannel.read(buffer) >= 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String str = new String(bytes);
            System.out.println(str);
            buffer.clear();
        }
        readableByteChannel.close();
    }
}
