package Search;

/**
 * Created by tlh on 2016/7/7.
 * 基于二分查找的有序表
 */
public class BinarySearch<K extends Comparable, V> {
    private K[] keys;
    private V[] values;
    private int n;

    public BinarySearch(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public V get(K key) {
        if (key == null) return null;
        int pos = binSearch(key);
        if (pos < n && keys[pos].compareTo(key) == 0) return values[pos];
        else return null;
    }

    public void put(K key, V value) {
        //查找键的位置
        int pos = binSearch(key);
        //键已经存在
        if (pos < n && keys[pos].compareTo(key) == 0) {
            values[pos] = value;
            return;
        }
        // 调整数组容量，防止越界
        if (n == keys.length) resize(2 * keys.length);

        for (int i = n; i > pos; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[pos] = key;
        values[pos] = value;
        n++;
    }

    //迭代法
    //无论表中是否存在key，该方法都是返回表中小于它的键的数量，
    // 这就解释了为什么当最后是return start;
    private int binSearch(K key) {
        int start = 0;
        int end = n - 1;
        int mid = start + (end - start) / 2;
        while (start <= end) {
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) end = mid - 1;
            else if (cmp > 0) start = mid + 1;//start的值在说明比key小的键的数量至少有多少个
            else return mid;
        }
        return start;//找不到这个键，返回应该插入这个键的位置，也就是小于它的键的数量
    }

    //递归法
    private int binSearch_(K key, int start, int end) {
        if (start > end) return start;
        int mid = start + (end - start) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return binSearch_(key, start, end - 1);
        else if (cmp > 0) return binSearch_(key, start + 1, end);
        else return mid;
    }

    public void delete(K key) {
        if (key == null) throw new NullPointerException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = binSearch(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        values[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

    }

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    private boolean isEmpty() {
        return n == 0;
    }
}
