package Sort;

/**
 * Created by tlh on 2017/3/15.
 */
public class InsertSort {
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int insertNum = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > insertNum) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = insertNum;
        }
    }
}
