package dp;

/**
 * Created by tlh on 2017/3/22.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s ="aab",
 * Return1since the palindrome partitioning["aa","b"]could be produced using 1 cut.
 * 求可得到回文字符串的最小切分
 */
public class MinCutPalindrome {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            dp[i] = i; // 最多要切分i次
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(s.substring(j, i + 1))) {
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                } else {
                    dp[i] = j == 0 ? dp[i] : Math.min(dp[i], dp[j - 1] + i - j + 1);
                }
                if (dp[i] == 0)
                    break;
            }
        }
        return dp[s.length() - 1];
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MinCutPalindrome().minCut("abaa"));
    }
}
