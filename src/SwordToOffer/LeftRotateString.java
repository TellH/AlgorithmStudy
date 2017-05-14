package SwordToOffer;

/**
 * Created by tlh on 2017/3/25.
 * 左旋字符串
 */
public class LeftRotateString {
    public String LeftRotateString_(String str, int n) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        char[] fronts = new char[n];
        char[] befores = new char[str.length() - n];
        str.getChars(0, n, fronts, 0);
        str.getChars(n, str.length(), befores, 0);
        return new String(befores) + new String(fronts);
    }

    public static void main(String[] args) {
        System.out.println(new LeftRotateString().LeftRotateString_("cdefgab", 2));
    }
}
