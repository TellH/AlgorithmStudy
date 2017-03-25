package dp;

/**
 * Created by tlh on 2017/3/22.
 * 背包问题
 * 背包容量为cap，第i件物品重量为w[i]，价值为v[i]。
 * (1) 基本背包：
 * 两种状态，分别是某样物品的放与不放。
 * dp[i][j] 表示将前i件物品放入容量为j的背包的最大价值
 * dp[i][j] =
 * v[i]  if i == 0
 * dp[i - 1][j] if w[i] > j
 * MAX{dp[i-1][j],dp[i-1][j-w[i]]+v[i]} else
 * (2) 完全背包：
 * 有k种状态，某样物品放k个
 * dp[i][j] = MAX{dp[i-1][j-k*w[i]]+v[i]*k} (0<=k*w[i]<=j)
 */
public class Bag {
    public int getMostValue(int cap, int[] w, int[] v) {
        int n = w.length;
        int[][] dp = new int[n][cap + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= cap; j++) {
                if (w[i] > j) // 超重，则不放该物品
                    dp[i][j] = i == 0 ? 0 : dp[i - 1][j];
                else
                    dp[i][j] = i == 0 ? v[i] : Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
            }
        }
        return dp[n - 1][cap];
    }

    public static void main(String[] args) {
        System.out.println(new Bag().getMostValue(10, new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}));
    }
}
