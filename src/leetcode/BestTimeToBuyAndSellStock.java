package leetcode;

/**
 * Created by tlh on 2017/6/10.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 1. If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock);
 * 2. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times);
 * 3. You may complete at most two transactions
 * design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStock {
    private static class Solution1 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            // 构造差价数组
            int[] diff = new int[prices.length];
            diff[0] = 0;
            for (int i = 1; i < diff.length; i++) {
                diff[i] = prices[i] - prices[i - 1];
            }
            // 求出差价数组字串最大和
            int profit = 0;
            int maxProfit = 0;
            for (int i = 1; i < diff.length; i++) {
                profit += diff[i];
                if (profit < 0) profit = 0;
                else if (maxProfit < profit) maxProfit = profit;
            }
            return maxProfit;
        }
    }

    private static class Solution2 {
        // 求所有递增区间所得利润之和
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            // 构造差价数组
            int[] diff = new int[prices.length];
            diff[0] = 0;
            for (int i = 1; i < diff.length; i++) {
                diff[i] = prices[i] - prices[i - 1];
            }
            // 求出差价数组所有正数的和
            int profit = 0;
            for (int i = 1; i < diff.length; i++) {
                if (diff[i] > 0) profit += diff[i];
            }
            return profit;
        }
    }
}
