package Sort;

/**
 * Created by tlh on 2017/3/15.
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
        int sankIndex;
        while (2 * start <= end) {
            sankIndex = start;
            if (a[sankIndex]< a[2 * start])
                sankIndex = 2 * start;
            if (2 * start + 1 <= end && a[sankIndex] < a[2 * start + 1])
                sankIndex = 2 * start + 1;
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
        int[] a = {100, 4, 3, 34, 23, 56, 35, 45};
        new HeapSort().sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
