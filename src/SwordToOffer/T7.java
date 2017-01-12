package SwordToOffer;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tlh on 2017/1/12.
 * 用两个栈实现队列
 * 完成在队列尾部插入结点和在队列头部删除结点的功能
 * <p>
 * 解法：
 * 实例化两个栈stack1和stack2
 * 每次要往插入结点，往stack1压入
 * 每次要删除队列头部时，当stack2不为空，直接弹出stack2；当stack2为空，将stack1中的元素依次弹出并依次压入stack2，最后弹出stack2
 *
 * 拓展：
 * 用两个队列实现一个栈
 * 解法：
 * 实例化两个队列q1和q2
 * 每次压栈时，插入到q1的队尾
 * 每次要弹出栈时，如果q1只有一个元素，直接删除之；如果q1为空，将q2的元素依次出队，依次往q1入队，直到q2只剩一个元素时，删除之；
 * 否则，将q1的元素依次出队，依次往q2入队，直到q1只剩一个元素时，删除之。
 */
public class T7 {
    private static class SQueue<T> {
        private Stack<T> stack1;
        private Stack<T> stack2;

        SQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(T n) {
            stack1.push(n);
        }

        public T deleteHead() {
            if (isEmpty())
                return null;
            if (stack2.isEmpty())
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            return stack2.pop();
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static void main(String[] args) {
        SQueue<Integer> q = new SQueue<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            boolean b = in.nextBoolean();
            if (b)
                q.appendTail(in.nextInt());
            else System.out.println(q.deleteHead());
        }
    }
}
