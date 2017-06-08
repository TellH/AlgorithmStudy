package SwordToOffer;

/**
 * Created by tlh on 2017/3/17.
 */
public class FindGreatestSumOfSubArray {
    public int findGreatestSumOfSubArray(int[] array) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (curSum <= 0) {
                curSum = array[i];
            } else {
                curSum += array[i];
            }
            if (curSum > maxSum)
                maxSum = curSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(new FindGreatestSumOfSubArray().findGreatestSumOfSubArray(new int[]{-2, 11, -4, 16, -5, -2}));
    }
}
