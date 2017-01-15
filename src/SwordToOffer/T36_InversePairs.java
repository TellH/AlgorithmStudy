package SwordToOffer;

import java.util.Scanner;

/**
 * Created by tlh on 2017/1/15.
 * 在数组中的两个数字如果前面一个数字大于后面的数组，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 先把数组分隔成子数组，统计出子数组内部的逆序对的数目，然后再统计出两个相邻子数组之间的逆序对数目。再统计逆序对的过程中，还需要对子数组进行排序。
 * 这个过程就是归并排序的改版。
 */
public class T36_InversePairs {
    public static int inversePairs(int[] data) {
        if (data == null)
            return 0;
        int[] bak = new int[data.length];
        return inversePairsCore(data, bak, 0, data.length - 1);
    }

    //自顶向下的归并排序
    private static int inversePairsCore(int[] data, int[] bak, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count = 0;
        count += inversePairsCore(data, bak, start, mid);
        count += inversePairsCore(data, bak, mid + 1, end);
        count += merge(data, bak, start, mid, end);
        return count;
    }

    private static int merge(int[] data, int[] bak, int start, int mid, int end) {
        if (data[mid] < data[mid + 1])
            return 0;
        int count = 0;
        int i = mid, j = end;
        System.arraycopy(data, start, bak, start, end - start + 1);//备份
        for (int k = end; k >= start; k--) {
            if (i < start) data[k] = bak[j--];
            else if (j < mid + 1) data[k] = bak[i--];
            else if (bak[i] > bak[j]) {
                data[k] = bak[i--];
                count += j - mid;
            } else data[k] = bak[j--];
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] data = new int[in.nextInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextInt();
        }
        System.out.println(inversePairs(data));
    }
}
