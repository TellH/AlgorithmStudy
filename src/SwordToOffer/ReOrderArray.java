package SwordToOffer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tlh on 2017/3/11.
 * 调整数组顺序使奇数位于偶数前面
 */
public class ReOrderArray {
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0)
            return;
        int i = 0, j = array.length - 1;
        while (i != j) {
            while (i != j && (array[i] & 1) != 0)
                i++;
            while (i != j && (array[j] & 1) == 0)
                j--;
            if (i != j)
                swap(array, i, j);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void reOrderArray2(int[] array) {
        Queue<Integer> odd = new LinkedBlockingQueue<>(); //奇数
        Queue<Integer> even = new LinkedBlockingQueue<>(); //偶数
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            if ((num & 1) == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (!odd.isEmpty()) {
                array[i] = odd.poll();
            }
            else if (!even.isEmpty()) {
                array[i] = even.poll();
            }
        }
    }
}
