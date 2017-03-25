package Sort;

/**
 * Created by tlh on 2017/3/15.
 * 选择排序
 */
public class SelectSort {
    public void sort(int[] a) {
        if (a == null)
            return;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 34, 23, 56, 35, 45};
        new SelectSort().sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
