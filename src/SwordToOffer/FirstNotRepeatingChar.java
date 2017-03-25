package SwordToOffer;

/**
 * Created by tlh on 2017/3/17.
 */
public class FirstNotRepeatingChar {
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.isEmpty()) return -1;
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map[ch]++;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map[ch] == 1)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new FirstNotRepeatingChar().firstNotRepeatingChar("google"));
    }
}
