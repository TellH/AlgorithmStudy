package SwordToOffer;

import Graph.Tree.MaxHeap;
import Graph.Tree.MinHeap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by tlh on 2017/1/17.
 * 寻找数据流中的中位数
 * 将数据分成两个堆来维护，前面是最大堆，后面是最小堆。
 * 1. 为了保证数据分配到两个堆中，当数据总数是偶数时将新数据插入到最小堆中，否则插入到最大堆中。
 * 2. 还要保证最大堆中所有数据都要小于最小堆中的数据，当需要把新数据插入到最小堆中并且这个新数据比最大堆中堆顶还要小，先把这个新数据插入到最大堆，
 * 接着把最大堆中的堆顶拿出来插入到最小堆中。
 * <p>
 * 最后，当有奇数个数据时，最小堆的堆顶是中位数；当有偶数个数据时，中位数时最小堆堆顶与最大堆堆顶之间的平均值。
 */
public class T64_GetStreamMedian {
    public static int getMedian(int[] a) {
        if (a.length == 0)
            throw new RuntimeException("No numbers are available.");
        MaxHeap<Integer> maxHeap = new MaxHeap<>(Integer.class);
        MinHeap<Integer> minHeap = new MinHeap<>(Integer.class);
        for (int i = 0; i < a.length; i++) {
            int t = a[i];
            if ((i & 1) == 0) {
                if (maxHeap.size() > 0 && t < maxHeap.peek()) {
                    maxHeap.push(t);
                    t = maxHeap.pop();
                }
                minHeap.push(t);
            } else {
                if (minHeap.size() > 0 && t > minHeap.peek()) {
                    minHeap.push(t);
                    t = minHeap.pop();
                }
                maxHeap.push(t);
            }
        }
        if ((a.length & 1) == 0)
            return (maxHeap.peek() + minHeap.peek()) / 2;
        else return minHeap.peek();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        System.out.println(getMedian(a));
    }

    PriorityQueue<Integer> minQ = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    PriorityQueue<Integer> maxQ = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private static int count = 0;

    public void Insert(Integer num) {
        count++;
        if ((count & 1) == 1) {
            if (!minQ.isEmpty() && num > minQ.peek()) {
                minQ.offer(num);
                num = minQ.poll();
            }
            maxQ.offer(num);
        } else {
            if (!maxQ.isEmpty() && num < maxQ.peek()) {
                maxQ.offer(num);
                num = maxQ.poll();
            }
            minQ.offer(num);
        }
    }

    public Double GetMedian() {
        if ((count & 1) == 1) {
            return Double.valueOf(maxQ.peek());
        } else {
            return (minQ.peek() + maxQ.peek()) / 2.0;
        }
    }
}
