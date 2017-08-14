package leetcode;

/**
 * Created by tlh on 2017/5/29.
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 * <p>
 * 解题报告：
 * 用两个快慢指针找到链表的中间节点，然后将后半段链表反转，
 * 然后合并两个链表,即将后半部分插入前半部分的奇数位置后面
 */
public class ReorderList {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        // 找到链表的中点
        ListNode mid = findMid(head);
        // 反转后半部分的链表
        ListNode postHead = reverseList(mid.next);
        mid.next = null;
        // 合并两个链表
        merge(head, postHead);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void merge(ListNode head1, ListNode head2) {
        int index = 0;
        ListNode dummy = new ListNode(0);
        while (head1 != null && head2 != null) {
            if (index % 2 == 0) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            index++;
        }
        if (head1 != null) {
            dummy.next = head1;
        } else {
            dummy.next = head2;
        }
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
