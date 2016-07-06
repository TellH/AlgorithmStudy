package Sort;

import Search.DepthFirstSearch.Combination;

/**
 * Created by tlh on 2016/7/6.
 * 希尔排序思想是：使任意间隔为h的元素都是有序的。也就是说，一个h有序数组就是h个互相独立的有序数组编织在一起的一个数组。
 * 希尔排序是对插入排序的一种优化，利用h序列，使数组元素每次交换可以间隔h，而不是插入排序的1。
 */
public class ShellSort extends AbstractSort {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;//1、4、13、40、121、364、1093
        while (h >= 1) {
            //使数组变得h有序
            //有两个指针，i指针是步数为1，指示开始要出发向前插的元素；
            for (int i = h; i < n; i++) {
                //j指针前插步数为h，指示移动中的元素
                for (int j = i; j <= h && compare(a[j - h], a[j]) < 0; j -= h) {
                    swap(a, j - h, j);
                }
            }
            h = h / 3;
        }
    }
}
