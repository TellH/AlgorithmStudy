package Sort;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by tlh on 2016/7/6.
 * 归并排序
 * 先递归地将数组分成两半分别排序，然后将两个有序数组归并成一个更大的有序数组。
 */
public class Merge extends AbstractSort {
    private Comparable[] bak;//备份辅助数组

    //归并两个有序的数组为一个有序数组
    public void merge(Comparable[] a, int start, int mid, int end) {
        //如果前一个子数组的最大值小于等于后一个子数组的最小值，证明此时数组已经有序，无需进行归并操作
        if (compare(a[mid], a[mid + 1]) <= 0) return;
        //有两个哨兵，分别从两个数组的头部开始
        int i = start, j = mid + 1;
        System.arraycopy(a, start, bak, start, end - start);//备份
        //开始归并
        for (int k = start; k <= end; k++) {
            if (i > mid) a[k] = a[j++];
            else if (j > end) a[k] = a[i++];
            else if (compare(bak[i], bak[j]) < 0) a[k] = a[i++];
            else a[k] = a[j++];
        }
    }

    //自顶向下的归并排序
    public void sort(Comparable[] a) {
        bak = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int start, int end) {
        if (end <= start) return;
        int mid = start + (end - start) / 2;
        sort(a, start, mid);//将左半边排序
        sort(a, mid, end);//将右半边排序
        merge(a, start, mid, end);//归并排序结果
    }

    //自底向上的归并排序
    //先两两归并，再四四归并，八八归并....最后完成整个数组的归并
    public void sort_(Comparable a[]) {
        int n = a.length;
        bak = new Comparable[a.length];
        ///sz是子数组的大小，每次sz的变化是自身乘以2，因为每完成一次归并，子数组大小会扩大一倍
        for (int sz = 1; sz < n; sz = sz + sz) {
            //归并必然有两个子数组，所以每次归并的起点间隔是两个sz
            //start是每次归并的起点，中点是start+sz-1，终点自然是start+sz+sz-1，但要防止超出数组的右边界n-1
            //最后一个子数组的大小只有在n是sz的整数倍才会等于sz，否则都比sz小
            for (int start = 0; start < n - sz; start = start + sz * 2) {
                merge(a, start, start + sz - 1, Math.min(n - 1, start + sz * 2 - 1));
            }
        }
    }
}
