package SwordToOffer;

import com.sun.org.apache.regexp.internal.RE;
import sun.text.normalizer.Replaceable;

import java.util.Scanner;

/**
 * Created by tlh on 2017/3/8.
 */
public class ReplaceSpace {
    public static class Solution {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            System.out.println(replaceSpace(new StringBuffer(str)));
        }

        public static String replaceSpace(StringBuffer str) {
            int len = str.length();
//            char[] buf = new char[len];
            int spaceCount = 0;
            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);
                if (c == ' ')
                    spaceCount++;
//                buf[i] = c;
            }
            int i = len - 1;
            int j = len + spaceCount * 2 - 1;
            for (int x = 0; x < spaceCount * 2; x++) {
                str.append(' ');
            }
            while (i >= 0) {
                if (str.charAt(i) == ' ') {
                    str.setCharAt(j--, '0');
                    str.setCharAt(j--, '2');
                    str.setCharAt(j--, '%');
                    i--;
                } else {
                    str.setCharAt(j, str.charAt(i));
                    i--;
                    j--;
                }
            }
            return str.toString();
        }
    }
}
