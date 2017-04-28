package Sort;

/**
 * Created by tlh on 2017/4/27.
 * 数组下标从0开始
 */
public class HeapSort_ {
    public void sort(int[] a) {
        int last = a.length - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) {
            swiftDown_(a, i, last);
        }
        while (last != 0) {
            swap(a, 0, last--);
            swiftDown_(a, 0, last);
        }
    }

    // 调整最小堆
    private void swiftDown(int[] a, int p, int last) {
        while (true) {
            int left = p * 2 + 1;
            int right = p * 2 + 2;
            int sankVex = p;
            if (left <= last && a[sankVex] > a[left]) sankVex = left;
            if (right <= last && a[sankVex] > a[right]) sankVex = right;
            if (sankVex != p) {
                swap(a, p, sankVex);
                p = sankVex;
            } else break;
        }
    }

    // 调整最大堆
    private void swiftDown_(int[] a, int p, int last) {
        while (true) {
            int left = p * 2 + 1;
            int right = p * 2 + 2;
            int sankVex = p;
            if (left <= last && a[sankVex] < a[left]) sankVex = left;
            if (right <= last && a[sankVex] < a[right]) sankVex = right;
            if (sankVex != p) {
                swap(a, p, sankVex);
                p = sankVex;
            } else break;
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {100, 4, 3, 34, 23, 56, 35, 45};
        new HeapSort_().sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
