package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by tlh on 2017/3/8.
 */
public class PrintListFromTailToHead {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode p = listNode;
        Stack<Integer> s = new Stack<>();
        while (p != null) {
            s.push(p.val);
            p = p.next;
        }
        ArrayList<Integer> result = new ArrayList<>(s.size());
        while (!s.isEmpty()) {
            result.add(s.pop());
        }
        return result;
    }
}
