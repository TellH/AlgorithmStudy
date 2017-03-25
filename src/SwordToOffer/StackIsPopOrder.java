package SwordToOffer;

import java.util.Stack;

/**
 * Created by tlh on 2017/3/13.
 */
public class StackIsPopOrder {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length != popA.length)
            return false;
        Stack<Integer> stack = new Stack<>();
        int popCnt = 0;
        for (int i = 0; i < pushA.length; i++) {
            int push = pushA[i];
            stack.push(push);
            if (popA[popCnt] == stack.peek()) {
                stack.pop();
                popCnt++;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popA[popCnt++])
                return false;
        }
        return true;
    }
}
