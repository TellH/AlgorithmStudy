package Sort;

/**
 * Created by tlh on 2016/7/6.
 * 插入排序
 */
public class Insertion extends AbstractSort {
    //升序排序
    public static void sort(Comparable[] a) {
        int n = a.length;
        //逐个遍历数组所有元素，让它们逐个往前插（挪）
        for (int i = 0; i < n; i++) {
            for (int j = i; j < 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
