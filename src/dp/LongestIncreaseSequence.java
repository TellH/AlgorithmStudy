package dp;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tlh on 2017/3/22.
 * 求数组中的最长递增子序列
 * dp[i] 表示在数组中以i下标结尾的最长递增序列的长度。
 * dp[i] =
 * MAX(1,MAX(1+dp[k])) 0<=k<i && a[k]<a[i]
 */
public class LongestIncreaseSequence {

    /**
     * 使用动态规划的方式
     */
    private static class DpSolution {
        public int lis(int[] a) {
            /*
              dp[i] 表示在数组中以i下标结尾的最长递增序列的长度。
              dp[i] =
              MAX(1,MAX(1+dp[k])) 0<=k<i && a[k]<a[i]
             */
            int[] dp = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                dp[i] = 1;
                for (int k = i; k >= 0; k--) {
                    if (a[k] < a[i]) {
                        dp[i] = Math.max(1 + dp[k], dp[i]);
                    }
                }
            }
            // 取dp数组中的最大值为最长递增序列的长度
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
    }

    /**
     * 使用暴力深度优先搜索的方式
     */
    private static class BruceForceSolution {
        int max = Integer.MIN_VALUE;

        public int lis(int[] a) {
            if (a == null || a.length == 0) return 0;
            // 深度优先搜索递增子序列
            dfs(a, new Stack<Integer>(), 0);
            // 返回子序列长度的最大值
            return max;
        }

        /**
         * 深度优先搜索递增子序列
         *
         * @param array 源数组
         * @param path  用一个栈记录搜索过程中所形成的子序列
         * @param start 开始搜索的数组下标
         */
        private void dfs(int[] array, Stack<Integer> path, int start) {
            // 搜索结束，回溯
            if (start >= array.length) return;
            // 搜索数组中start下标之后的元素
            for (int i = start; i < array.length; i++) {
                if (path.isEmpty() || array[i] > path.peek()) {
                    path.push(array[i]);
                    max = Math.max(max, path.size());
                } else continue;
                dfs(array, path, i + 1);
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int a[] = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        System.out.println(new DpSolution().lis(a));
//        System.out.println(new DpSolution().lis(new int[]{1, -1, 5, 6, -1, 7, 8, 9, 2, 3, 5, 6}));
//        System.out.println(new BruceForceSolution().lis(new int[]{1, -1, 5, 6, -1, 7, 8, 9, 2, 3, 5, 6}));
    }
}
