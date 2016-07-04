package Graph.Tree;


import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/3.
 * 最大堆
 * 堆的内部维护一个数组，该数组从下标1开始存数
 */
public class MaxHeap<T extends Comparable<T>> {
    private int n;
    private T[] h;

    public MaxHeap(int n, T[] h) {
        this.n = n;
        this.h = h;
        //从最后一个非叶节点到第一个节点依次向上调整
        for (int i = n / 2; i >= 1; i++) {
            swiftdown(i, n, h);
        }
    }

    public MaxHeap(Class<T> clz) {
        h = (T[]) Array.newInstance(clz, 100);
//        h = new T[100];
        n = 0;
    }

    private void swap(int i, int j, T[] h) {
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
            if (h[sankVex].compareTo(h[i * 2]) < 0)
                sankVex = i * 2;
            //与右子节点比较
            if (i * 2 + 1 <= last && h[sankVex].compareTo(h[i * 2 + 1]) < 0)
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
            if (h[i].compareTo(h[i / 2]) > 0) {
                swap(i, i / 2, h);
                i /= 2;
            } else break;
        }
    }

    public void update(int pos, T value) {
        T bak = h[pos];
        if (value.compareTo(bak) == 0) return;
        h[pos] = value;
        if (bak.compareTo(value) < 0)
            swiftUp(pos, h);
        else swiftdown(pos, n, h);
    }

    //往堆中添加元素
    public void push(T elem) {
        h[++n] = elem;
        swiftUp(n, h);
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

    public T[] heapSortSmall2Large() {
        T[] result = (T[]) Array.newInstance(h.getClass().getComponentType(), n + 1);
        int last = n;
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
        MaxHeap<Integer> heap = new MaxHeap<>(Integer.class);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            heap.push(in.nextInt());
        }
        Integer[] r = heap.heapSortSmall2Large();
        for (Integer integer : r) {
            System.out.print(integer + " ");
        }
        in.close();
    }
}
