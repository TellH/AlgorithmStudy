package SwordToOffer;

import Sort.Merge;

/**
 * Created by tlh on 2017/3/12.
 */
public class MergeList {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 != null)
            return list2;
        if (list2 == null && list1 != null)
            return list1;
        if (list1 == null && list2 == null)
            return null;
        ListNode head, p;
        if (list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        p = head;
        while (true) {
            if (list1 == null && list2 != null) {
                p.next = list2;
                break;
            } else if (list2 == null && list1 != null) {
                p.next = list1;
                break;
            } else if (list1.val < list2.val) {
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            } else {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode13;
        listNode13.next = listNode15;
        ListNode listNode22 = new ListNode(2);
        ListNode listNode24 = new ListNode(4);
        ListNode listNode26 = new ListNode(6);
        listNode22.next = listNode24;
        listNode24.next = listNode26;
        Merge(listNode11, listNode22);
    }
}
