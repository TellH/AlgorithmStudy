package Graph.Tree;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/3.
 * 最小堆
 * 堆的内部维护一个数组，该数组从下标1开始存数
 */
public class MinHeap<T extends Comparable<T>> {
    private int n;
    private T[] h;

    public MinHeap(int n, T[] h) {
        this.n = n;
        this.h = h;
        //从最后一个非叶节点到第一个节点依次向上调整
        for (int i = n / 2; i >= 1; i++) {
            swiftdown(i, n, h);
        }
    }

    public MinHeap(Class<T> clz) {
        h = (T[]) Array.newInstance(clz, 100);
        n = 0;
    }

    protected void swap(int i, int j, T[] h) {
        T t = h[i];
        h[i] = h[j];
        h[j] = t;
    }

    //下沉
    private void swiftdown(int i, int last, T[] h) {
        int sankVex;//下沉的点
        while (i * 2 <= last) {
            //与最小的子节点交换
            sankVex = i;
            //与左子节点比较
            if (h[sankVex].compareTo(h[i * 2]) > 0)
                sankVex = i * 2;
            //与右子节点比较
            if (i * 2 + 1 <= last && h[sankVex].compareTo(h[i * 2 + 1]) > 0)
                sankVex = i * 2 + 1;
            if (i != sankVex) {
                swap(i, sankVex, h);
                i = sankVex;//更新i的编号
            } else break;
        }
    }

    //上浮
    private void swiftUp(int i, T[] h) {
        int swimVex;
        while (i / 2 >= 1) {
            //与父节点比较
            if (h[i].compareTo(h[i / 2]) < 0) {
                swap(i, i / 2, h);
                i /= 2;
            } else break;
        }
    }

    //往堆中添加元素
    public void push(T elem) {
        h[++n] = elem;
        swiftUp(n, h);
    }

    public T peek() {
        if (n == 0)
            return null;
        return h[1];
    }

    //删除堆顶元素
    public T pop() {
        if (n == 0)
            return null;
        swap(1, n, h);
        n--;
        swiftdown(1, n, h);
        return h[n + 1];
    }

    public void update(int pos, T value) {
        T bak = h[pos];
        if (value.compareTo(bak) == 0) return;
        h[pos] = value;
        if (bak.compareTo(value) > 0)
            swiftUp(pos, h);
        else swiftdown(pos, n, h);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T[] heapSortLarge2Small() {
        T[] result = (T[]) Array.newInstance(h.getClass().getComponentType(), n + 1);
        int last = n;
        //public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        // src:源数组； srcPos:源数组要复制的起始位置； dest:目的数组； destPos:目的数组放置的起始位置； length:复制的长度
        System.arraycopy(h, 1, result, 1, n);
        //因为是最小堆，堆顶始终是堆中的最小数。每次把堆顶元素丢到后面，重新调整成堆，直到堆空，所得数组就是从大到小排序的
        while (last > 1) {
            swap(1, last, result);
            last--;
            swiftdown(1, last, result);
        }
        return result;
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(Integer.class);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            heap.push(in.nextInt());
        }
        Integer[] r = heap.heapSortLarge2Small();
        for (Integer integer : r) {
            System.out.print(integer + " ");
        }
        in.close();
    }
}
