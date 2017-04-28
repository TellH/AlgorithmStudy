package Sort;

/**
 * Created by tlh on 2017/3/15.
 * 数组下标从0开始
 */
public class HeapSort {
    public void sort(int[] a) {
        int n = a.length - 1;
        // 从第一个非叶节点开始
        for (int i = n / 2; i >= 1; i--) {
            shiftDown(a, i, n);
        }

        while (n >= 1) {
            swap(1, n--, a);
            shiftDown(a, 1, n);
        }
    }

    private void shiftDown(int[] a, int start, int end) {
        while (true) {
            int left = start * 2;
            int right = start * 2 + 1;
            int sankIndex = start;
            if (left <= end && a[sankIndex] < a[left])
                sankIndex = left;
            if (right <= end && a[sankIndex] < a[right])
                sankIndex = right;
            if (sankIndex != start) {
                swap(start, sankIndex, a);
                start = sankIndex;
            } else break;
        }
    }


    protected void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {0, 4, 3, 34, 23, 56, 35, 45};
        new HeapSort().sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
