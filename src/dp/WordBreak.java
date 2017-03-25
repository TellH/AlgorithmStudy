package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tlh on 2017/3/22.
 */
public class WordBreak {
    public boolean canBreak(String s, Set<String> dict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak().canBreak("cars", new HashSet<>(Arrays.asList("car", "ca", "rs"))));
    }
}
