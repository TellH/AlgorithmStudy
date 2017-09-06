package leetcode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given1->2->3->4->5->NULL, m = 2 and n = 4,
 * return1->4->3->2->5->NULL.
 */
public class ReverseLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 找到m结点所在位置
        ListNode preStart = dummy, start = head;
        for (int i = 1; i < m; i++) {
            preStart = start;
            start = start.next;
        }
        // 每读到一个结点，把它插入到preStart结点后面位置，然后m结点接到读到结点的下一个。
        for (int i = m; i < n; i++) {
            ListNode temp = start.next;
            start.next = temp.next;
            temp.next = preStart.next;
            preStart.next = temp;
        }
        return dummy.next;
    }
}
