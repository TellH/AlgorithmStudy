package SwordToOffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by tlh on 2017/3/15.
 */
public class FindTopK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k <= 0 || input == null || k > input.length)
            return result;
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };
        Queue<Integer> q = new PriorityQueue<>(k, cmp);
        for (int i = 0; i < input.length; i++) {
            q.add(input[i]);
        }
        while (!q.isEmpty() && result.size() < k) {
            result.add(q.poll());
        }
        return result;
    }
}
