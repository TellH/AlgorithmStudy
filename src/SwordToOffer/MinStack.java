package SwordToOffer;

import java.util.Stack;

/**
 * Created by tlh on 2017/3/13.
 */
public class MinStack {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> min = new Stack<>();


    public void push(int node) {
        data.push(node);
        if (min.isEmpty() || node <= min())
            min.push(node);
    }

    public void pop() {
        Integer popData = data.pop();
        if (!min.isEmpty() && popData == min())
            min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        if (min.isEmpty())
            throw new RuntimeException("Stack is Empty!");
        return min.peek();
    }
}
