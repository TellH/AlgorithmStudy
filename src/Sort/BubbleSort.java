package Sort;

/**
 * Created by tlh on 2017/3/15.
 * 冒泡排序
 */
public class BubbleSort {
    public void sort(int[] a) {
        if (a == null)
            return;
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 34, 23, 56, 35, 45};
        new BubbleSort().sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
