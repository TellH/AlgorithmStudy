package Sort;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/1.
 */
public class QuickSort {
    private int[] a;
    int n;

    public QuickSort(int[] a, int n) {
        this.a = a;
        this.n = n;
    }

    public void quickSort() {
        if (this.a == null) return;
        quickSort(0, n - 1);
    }

    private void quickSort(int left, int right) {
        if (right < left)
            return;
        //找最左边的数作为基准数
        int base = a[left];
        //声明两个探测指针，一个在最左边往右走，一个在最右边往左走
        int i = left, j = right;
        //开始探测，直到两个探测指针相遇为止
        while (i != j) {
            //首先，右边指针先往左走，直到找到比基准数小的数为止
            while (a[j] >= base && j > i)
                j--;
            //然后，左边指针往右走，直到找到比基准数大的数为止
            while (a[i] <= base && i < j)
                i++;
            //交换两个指针的值
            if (i < j)//当i==j时就没必要交换了
                swap(i, j);
        }
        //此时i==j
        //基准数归位
        swap(left, i);
        //递归地排左侧与右侧区间的数
        quickSort(left, i - 1);
        quickSort(i + 1, right);
    }

    private void swap(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] inputArray;
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = in.nextInt();
        }
        QuickSort qs = new QuickSort(inputArray, n);
        qs.quickSort();
        for (int i = 0; i < n; i++) {
            System.out.print(qs.a[i] + " ");
        }
        System.out.println();
    }

}
