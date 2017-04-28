import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by tlh on 2017/3/7.
 */
public class AliDecode {
    public static void main(String[] args) {
        try {
            String test = "56477,56465,51835,63190,44858,62933,40492";
            String[] strings = test.split(",");
            int[] raw = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                raw[i] = Integer.valueOf(strings[i]);
            }
            System.out.println(decode(raw));
        } catch (Exception e) {
            System.out.println("无法解码");
        }
    }

    private static String decode(int[] raw) throws UnsupportedEncodingException {
        if (raw == null || raw.length <= 0)
            throw new RuntimeException("输入参数错误，无法解码");
        byte[] bytes = new byte[raw.length];
        for (int i = 0; i < raw.length; i++) {
            int temp = raw[i];
            temp = temp >> 8;
            byte ram = (byte) ~temp;

            temp = raw[i];
            temp = temp & 0xff;
            temp = temp + ram;
            bytes[i] = (byte) temp;
        }
        shift(bytes);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i];
        }
        return new String(bytes, "UTF-8");
    }

    private static byte[] shift(byte[] raw) {
        int i = 0, j = raw.length - 1;
        while (i != j) {
            byte temp = raw[i];
            raw[i] = raw[j];
            raw[j] = temp;
            i++;
            j--;
        }
        return raw;
    }
}
