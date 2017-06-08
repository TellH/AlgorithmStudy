package leetcode;

import java.util.Stack;

/**
 * Created by tlh on 2017/5/27.
 * 计算逆波兰式
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-(stack.pop() - stack.pop()));
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new EvaluateReversePolishNotation().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }
}
