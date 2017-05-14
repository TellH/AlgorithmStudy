package SwordToOffer;

import java.util.Scanner;

/**
 * Created by tlh on 2017/1/12.
 * 合并或替换字符串或数组
 * 有两个排序的数组a1和a2，内存在a1的末尾有足够多的空余空间容纳a2.实现一个函数，把a2的所有数字插入到a1中并且所有的数字是排序的。
 * <p>
 * 解法：
 * 从后往前替换
 */
public class MergeArrays {
    private final int l1;
    private final int l2;
    private int[] a1;
    private int[] a2;

    private MergeArrays(int[] a1, int[] a2, int l1, int l2) {
        this.a1 = a1;
        this.a2 = a2;
        this.l1 = l1;
        this.l2 = l2;
        if (l1 + l2 > a1.length)
            throw new RuntimeException("数组a1的长度不够");
    }

    private int[] run() {
        int p1 = l1 - 1;
        int p2 = l1 + l2 - 1;
        int p3 = l2 - 1;
        while (p2 >= 0) {
            if (p1 < 0)
                a1[p2--] = a2[p3--];
            else if (p3 < 0)
                a1[p2--] = a2[p1--];
            else {
                int t1 = a1[p1];
                int t2 = a2[p3];
                if (t1 > t2)
                    a1[p2--] = a1[p1--];
                else a1[p2--] = a2[p3--];
            }
        }
        return a1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] a1 = new int[100];
        int[] a2 = new int[100];
        int l1 = in.nextInt();
        int l2 = in.nextInt();
        for (int i = 0; i < l1; i++) {
            a1[i] = in.nextInt();
        }
        for (int i = 0; i < l2; i++) {
            a2[i] = in.nextInt();
        }
        int[] result = new MergeArrays(a1, a2, l1, l2).run();
        for (int i = 0; i < l1 + l2; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
