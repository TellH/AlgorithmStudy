package SwordToOffer;

/**
 * Created by tlh on 2017/3/12.
 */
public class ReverseList {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode mid = head, pre = null, after = mid.next;
        while (after != null) {
            mid.next = pre;
            pre = mid;
            mid = after;
            after = after.next;
        }
        mid.next = pre;
        return mid;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        reverseList(listNode1);
    }
}
