package SwordToOffer;

/**
 * Created by tlh on 2017/3/11.
 */
public class FindKthToTail {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0)
            return null;
        ListNode before = head;
        ListNode after = head;
        for (int i = 1; i < k; i++) {
            if (after == null)
                return null;
            after = after.next;
        }
        if (after == null)
            return null;
        while (after.next != null) {
            before = before.next;
            after = after.next;
        }
        return before;
    }
}
