package Sort;

/**
 * Created by tlh on 2016/7/6.
 * 排序类模板
 */
class AbstractSort {
    protected static int compare(Comparable a, Comparable b) {
        if (a == null && b == null)
            return 0;
        if (a != null)
            return a.compareTo(b);
        return b.compareTo(a);
    }

    protected static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
