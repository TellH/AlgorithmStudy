package Graph.Tree;

/**
 * Created by tlh on 2016/7/3.
 * 最大堆
 * 堆的内部维护一个数组，该数组从下标1开始存数
 */
public class MaxHeap {
    int n;
    int[] h;

    public MaxHeap(int n, int[] h) {
        this.n = n;
        this.h = h;
        //从最后一个非叶节点到第一个节点依次向上调整
        for (int i = n / 2; i >= 1; i++) {
            swiftdown(i, h);
        }
    }

    public MaxHeap() {
        h = new int[100];
        n = 0;
    }

    private void swap(int i, int j, int[] h) {
        int t = h[i];
        h[i] = h[j];
        h[j] = t;
    }

    //下沉
    private void swiftdown(int i, int[] h) {
        int sankVex;//下沉的点
        while (i * 2 <= n) {
            //与最小的子节点交换
            sankVex = i;
            //与左子节点比较
            if (h[sankVex] < h[i * 2])
                sankVex = i * 2;
            //与右子节点比较
            if (i * 2 + 1 <= n && h[sankVex] < h[i * 2 + 1])
                sankVex = i * 2 + 1;
            if (i != sankVex) {
                swap(i, sankVex, h);
                i = sankVex;//更新i的编号
            } else break;
        }
    }

    //上浮
    private void swiftUp(int i, int[] h) {
        int swimVex;
        while (i / 2 >= 1) {
            //与父节点比较
            if (h[i] > h[i / 2]) {
                swap(i, i / 2, h);
                i /= 2;
            } else break;
        }
    }

    //往堆中添加元素
    public void push(int elem) {
        h[++n] = elem;
        swiftUp(n, h);
    }

    //删除堆顶元素
    public int pop() {
        if (n == 0)
            return 0;
        swap(1, n, h);
        n--;
        swiftdown(1, h);
        return h[n + 1];
    }

    public int[] heapSortSmall2Large() {
        int[] result = new int[n];
        int last = n;
        System.arraycopy(h, 1, result, 1, n);
        //因为是最小堆，堆顶始终是堆中的最小数。每次把堆顶元素丢到后面，重新调整成堆，直到堆空，所得数组就是从大到小排序的
        while (last > 1) {
            swap(1, last, result);
            last--;
            swiftdown(1, result);
        }
        return result;
    }
}
