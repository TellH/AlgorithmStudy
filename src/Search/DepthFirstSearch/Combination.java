package Search.DepthFirstSearch;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/2.
 * 组合算法 从n个数里取出m个数组合的算法
 * 用01组合法
 * 首先初始化，将数组前n个元素置1，表示第一个组合为前n个数。
 * 然后从左到右扫描数组元素值的“10”组合，找到第一个“10”组合后将其变为
 * “01”组合，同时将其左边的所有“1”全部移动到数组的最左端。
 * 当第一个“1”移动到数组的m-n的位置，即n个“1”全部移动到最右端时，就得到了最后一个组合。
 */
public class Combination {
    private int n;
    private int m;
    private int[] src;
    //零一数组
    private int[] zerones;

    public Combination(int m, int n, int[] src) {
        this.m = m;
        this.n = n;
        this.src = src;
        zerones = new int[n];
        for (int i = 0; i < m; i++) {
            zerones[i] = 1;
        }
    }

    public void run() {
        while (true) {
            outputCombination();
            if (!findOneZero())
                break;
        }
    }

    private void outputCombination() {
        for (int i = 0; i < n; i++) {
            if (zerones[i] == 1)
                System.out.print(src[i] + " ");
        }
        System.out.println();
    }

    private boolean findOneZero() {
        int i;
        for (i = 0; i < n - 1; i++) {
            if (zerones[i] == 1 && zerones[i + 1] == 0) {
                //将10组合变成01
                zerones[i] = 0;
                zerones[i + 1] = 1;
                int ones = 0;
                //将前面所有的1移到最右边
                for (int j = 0; j < i; j++) {
                    if (zerones[j] == 1) ones++;
                }
                for (int j = 0; j < i; j++) {
                    if (j < ones)
                        zerones[j] = 1;
                    else zerones[j] = 0;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] src = new int[n];
        for (int i = 0; i < n; i++) {
            src[i] = in.nextInt();
        }
        Combination p = new Combination(m, n, src);
        p.run();
        in.close();
    }
}
