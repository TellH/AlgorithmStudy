package Sort;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/1.
 * 快速排序
 * 对于小数组，用插入排序效率优于快速排序
 */
public class QuickSort<T extends Comparable> extends AbstractSort {
    private T[] a;
    int n;

    public QuickSort(T[] a, int n) {
        this.a = a;
        this.n = n;
    }

    public void quickSort() {
        if (this.a == null) return;
        quickSort(0, n - 1);
    }

    //三向切分的快速排序,优化了排序数组重复元素多的情况
    public static void partition3WaySort(Comparable[] a, int start, int end) {
        if (start >= end) return;//递归结束条件
        //声明三个指针，p是移动探测指针，
        //low是负责接收比切分元素小的指针，该指针会往后移，左边全是比base小的元素，low到p之间的是等于base的元素
        //up是负责接收比切分元素大的指针，该指针会往前移，右边全是比base大的元素，p到up之间的元素是没处理的元素
        //因此，切分后，start到low是小于base的元素，low到up的元素是等于base的元素，base到end的元素是大于base的元素
        int p = start + 1, low = start, up = end;
        Comparable base = a[start];//切分元素
        //p到up之间的元素是没处理的元素
        while (p <= up) {
            int cmp = compare(a[p], base);
            if (cmp < 0) swap(a, low++, p++);//a[p]小于base，扔到左边去
            else if (cmp > 0) swap(a, up--, p++);//a[p]大于base，扔到右边去
            else p++;//a[p]等于base，不用管继续走
        }
        partition3WaySort(a, start, low - 1);
        partition3WaySort(a, up + 1, end);
    }

    private void quickSort(int left, int right) {
        if (right <= left)
            return;
        //找最左边的数作为基准数
        T base = a[left];
        //声明两个探测指针，一个在最左边往右走，一个在最右边往左走
        int i = left, j = right;
        //开始探测，直到两个探测指针相遇为止
        while (i != j) {
            //首先，右边指针先往左走，直到找到比基准数小的数为止
            while (compare(a[j], base) >= 0 && j > i)
                j--;
            //然后，左边指针往右走，直到找到比基准数大的数为止
            while (compare(a[i], base) <= 0 && i < j)
                i++;
            //交换两个指针的值
            if (i < j)//当i==j时就没必要交换了
                swap(a, i, j);
        }
        //此时i==j
        //基准数归位
        swap(a, left, i);
        //递归地排左侧与右侧区间的数
        quickSort(left, i - 1);
        quickSort(i + 1, right);
    }
/*
    public static void main(String[] args) {
        Integer[] inputArray;
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        inputArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = in.nextInt();
        }
        QuickSort qs = new QuickSort(inputArray, n);
        qs.quickSort();
        for (int i = 0; i < n; i++) {
            System.out.print(qs.a[i] + " ");
        }
        System.out.println();
    }*/

    public QuickSort() {
    }

    public void sort(int[] a, int left, int right) {
        if (a == null || right <= left)
            return;
        int base = a[left];
        int i = left + 1, j = right;
        while (true) {
            while (i < j && a[j] >= base)
                j--;
            while (i < j && a[i] <= base)
                i++;
            if (i != j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            } else {
                break;
            }
        }
        a[left] = a[i];
        a[i] = base;
        sort(a, left, i - 1);
        sort(a, i + 1, right);
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 34, 23, 56, 35, 45};
        new QuickSort<Integer>().sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
