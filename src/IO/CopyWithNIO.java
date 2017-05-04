package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tlh on 2017/5/1.
 */
public class CopyWithNIO {
    public static void copy(FileInputStream in, FileOutputStream out) throws IOException {
        FileChannel fcin = in.getChannel();
        FileChannel fcout = out.getChannel();
        fcin.transferTo(0, fcin.size(), fcout);
//        fcout.transferFrom(fcin, 0, fcin.size());
    }

    public static void copy_(FileInputStream in, FileOutputStream out) throws IOException {
        FileChannel fcin = in.getChannel();
        FileChannel fcout = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while ((fcin.read(buffer) != -1)) {
            buffer.flip(); // Prepare for writing
            fcout.write(buffer);
            buffer.clear(); // Prepare for reading
        }
    }

    public static void main(String[] args) throws IOException {
        copy(new FileInputStream(new File("data.txt")), new FileOutputStream(new File("bak.txt")));
    }
}
