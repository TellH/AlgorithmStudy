package SwordToOffer;

import java.util.Stack;

/**
 * Created by tlh on 2017/3/19.
 */
public class FindFirstCommonListNode {
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        ListNode p = pHead1;
        while (p != null) {
            s1.push(p);
            p = p.next;
        }
        p = pHead2;
        while (p != null) {
            s2.push(p);
            p = p.next;
        }
        if (s1.peek() != s2.peek())
            return null;
        ListNode p1 = null;
        ListNode p2 = null;
        while (true) {
            p1 = s1.pop();
            p2 = s2.pop();
            if (p1 != p2)
                break;
            if (s1.isEmpty() || s2.isEmpty())
                return p1;
        }
        return p1.next;
    }
}
