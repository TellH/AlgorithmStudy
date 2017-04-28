package others;

import java.util.Stack;

/**
 * Created by tlh on 2017/4/27.
 * 单调栈
 * 给定一个数组，找出这个数组中每一个数右边的第一个比它大的数，比如9 6 5 7 3 2 1 5 9 10，返回的是 10 7 7 9 5 5 5 9 10 N
 */
public class MonotonePriorityStack {
    public int[] get(int[] a) {
        int[] result = new int[a.length - 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            if (stack.isEmpty()) stack.push(i);
            else if (a[i] <= a[stack.peek()]) stack.push(i);
            else {
                int n = a[i];
                do {
                    result[stack.pop()] = n;
                } while (!stack.isEmpty() && a[stack.peek()] < n);
                stack.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new MonotonePriorityStack().get(new int[]{9, 6, 5, 7, 3, 2, 1, 5, 9, 10});
        for (int num : a) {
            System.out.print(num + " ");
        }
    }
}
