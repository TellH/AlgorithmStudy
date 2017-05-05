package others;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Created by tlh on 2017/5/5.
 */
public class MaxElementQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private Deque<Integer> maxQueue = new LinkedList<>();

    public void EnQueue(Integer v) {
        queue.add(v);
        if (maxQueue.isEmpty() || maxQueue.peekLast() >= v)
            maxQueue.add(v);
        else {
            do {
                maxQueue.pollLast();
            } while (!maxQueue.isEmpty() && v > maxQueue.peekLast());
            maxQueue.add(v);
        }
    }

    public Integer DeQueue() {
        Integer elem = queue.poll();
        if (Objects.equals(maxQueue.peekFirst(), elem)) maxQueue.pollFirst();
        return elem;
    }

    public Integer MaxElement() {
        return maxQueue.peekFirst();
    }

    public static void main(String[] args) {
        MaxElementQueue queue = new MaxElementQueue();
        queue.EnQueue(36);
        queue.EnQueue(3);
        queue.EnQueue(100);
        queue.EnQueue(17);
        queue.EnQueue(100);
        queue.EnQueue(19);
        System.out.println(queue.MaxElement());
    }
}
