package SwordToOffer;

import java.util.Stack;

/**
 * Created by tlh on 2017/3/11.
 */
public class QueueWith2Stack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack1.pop();
        } else {
            return stack2.pop();
        }
    }
}
