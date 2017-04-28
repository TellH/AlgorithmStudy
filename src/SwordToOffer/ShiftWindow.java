package SwordToOffer;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by tlh on 2017/4/1.
 */
public class ShiftWindow {
    private ArrayDeque<Integer> q;


    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || num.length < size || size == 0) return result;
        int i = 0, j = 0;
        q = new ArrayDeque<>();
        for (; j <= size - 1; j++) {
            addToQueue(num[j]);
        }
        result.add(q.peekFirst());
        while (j <= num.length - 1) {
            if (num[i++] == q.peekFirst()) {
                q.pollFirst();
            }
            addToQueue(num[j++]);
            result.add(q.peekFirst());
        }
        return result;
    }

    private void addToQueue(int e) {
        if (q.isEmpty())
            q.offer(e);
        else if (q.peekLast() <= e) {
            do {
                q.pollLast();
            } while (!q.isEmpty() && q.peekLast() < e);
            q.offer(e);
        } else {
            q.offer(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ShiftWindow().maxInWindows(new int[]{10, 14, 12, 11}, 4).toString());
    }
}
