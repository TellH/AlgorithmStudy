package others;

/**
 * Created by tlh on 2017/4/30.
 * 大数相加
 * 反转，按位相加，进位
 */
public class AddBigInteger {
    public static String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        char[] ra = new char[a.length()];
        char[] rb = new char[b.length()];
        for (int i = a.length() - 1; i >= 0; i--) {
            ra[a.length() - i - 1] = a.charAt(i);
        }
        for (int i = b.length() - 1; i >= 0; i--) {
            rb[b.length() - i - 1] = b.charAt(i);
        }
        int c = 0;
        for (int i = 0; i < a.length() || i < b.length(); i++) {
            int sum = (i < a.length() ? valueOf(ra[i]) : 0) + (i < b.length() ? valueOf(rb[i]) : 0);
            sb.append(sum % 10 + c);
            c = sum / 10;
        }
        if (c != 0) sb.append(c);
        return sb.reverse().toString();
    }

    public static int valueOf(char c) {
        return c - '0';
    }

    public static void main(String[] args) {
        System.out.println(add("12", "90"));
        System.out.println(add("0", "0"));
        System.out.println(add("10", "10000000000000000000000000"));
    }
}
