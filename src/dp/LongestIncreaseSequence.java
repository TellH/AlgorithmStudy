package dp;

/**
 * Created by tlh on 2017/3/22.
 * 求数组中的最长递增子序列
 * dp[i] 表示在数组中以i下标结尾的最长递增序列的长度。
 * dp[i] =
 * MAX(1,MAX(1+dp[k])) 0<=k<i && a[k]<a[i]
 */
public class LongestIncreaseSequence {
    public int lis(int[] a) {
        int[] dp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            dp[i] = 1;
            for (int k = i; k >= 0; k--) {
                if (a[k] < a[i]) {
                    dp[i] = Math.max(1 + dp[k], dp[i]);
                }
            }
        }
        return Max(dp);
    }

    private int Max(int[] dp) {
        int max = Integer.MIN_VALUE;
        for (int num : dp) {
            if (max < num)
                max = num;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreaseSequence().lis(new int[]{1, 4, 5, 6, -1, 7, 8, 9, 2, 3, 5, 6}));
    }
}
