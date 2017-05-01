package others;

/**
 * Created by tlh on 2017/4/29.
 * 求子数组的最大乘积
 * 解法二：
 * 求数组所有元素的乘积P，判断P的正负值来确定。
 */
public class GreatestProductOfSubArray {
    public int getGreatestProduct(int[] array) {
        int[] s = new int[array.length];
        int[] t = new int[array.length];
        s[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            s[i] = s[i - 1] * array[i];
        }
        t[array.length - 1] = array[array.length - 1];
        for (int i = array.length - 2; i >= 1; i--) {
            t[i] = t[i + 1] * array[i];
        }
        int[] r = new int[array.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < r.length; i++) {
            if (i == 0) r[i] = t[i + 1];
            else if (i == r.length - 1) r[i] = s[i - 1];
            else r[i] = s[i - 1] * t[i + 1];
            if (max < r[i]) max = r[i];
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
