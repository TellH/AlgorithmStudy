package dp;

/**
 * Created by tlh on 2017/3/22.
 * 求最长公共子序列
 * 用二维动态规划
 * 用dp数组表示s1的i之前的字符串与s2的j之间的字符串的最长公共子序列的长度
 * dp[i][j] =
 * dp[i-1][j-1] +1 if s1[i] == s[j]
 * MAX{dp[i-1][j],dp[i][j-1]} if s1[i] != s2[j]
 */
public class LongestCommonSequence {
    public int lcs(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = i == 0 || j == 0 ? 1 : dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = i == 0 || j == 0 ? 0 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSequence().lcs("BDCABA", "ABCBDAB"));
    }
}
