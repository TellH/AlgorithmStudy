package leetcode;

/**
 * Created by tlh on 2017/5/28.
 * 链表排序
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private class MergeSort {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode mid = findMid(head);
            ListNode right = sortList(mid.next);
            mid.next = null;
            ListNode left = sortList(head);
            return merge(left, right);
        }

        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode p = new ListNode(-1), head = p;
            if (p1 == null)
                return p2;
            else if (p2 == null)
                return p1;
            while (true) {
                if (p1 == null) {
                    p.next = p2;
                    break;
                } else if (p2 == null) {
                    p.next = p1;
                    break;
                } else if (p1.val < p2.val) {
                    p.next = p1;
                    p = p.next;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p = p.next;
                    p2 = p2.next;
                }
            }
            return head;
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    private class QuickSort {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            quickSort(head, null);
            return head;
        }

        private void quickSort(ListNode head, ListNode tail) {
            if (head == tail)
                return;
            ListNode mid = partition(head);
            quickSort(head, mid);
            quickSort(mid.next, null);
        }

        private void swap(ListNode a, ListNode b) {
            int t = a.val;
            a.val = b.val;
            b.val = t;
        }

        // 保证slow之前的节点值都比基准值小，slow和fast之间的节点值都比基准值大
        private ListNode partition(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null) {
                if (fast.val < head.val) {
                    slow = slow.next;
                    swap(slow, fast); // 节点的值交换
                }
                fast = fast.next;
            }
            swap(head, slow);
            return slow;
        }

    }

    private class InsertionSort {
        // 分成一个有序链表和一个原始无序链表，每次取原始无序链表的表头节点插入到有序链表里面
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode dummy = new ListNode(Integer.MIN_VALUE);
            while (head != null) {
                ListNode p = dummy;
                // insert head into dummy list
                while (p.next != null && head.val >= p.next.val) {
                    p = p.next;
                }
                ListNode temp = head.next;
                head.next = p.next;
                p.next = head;
                head = temp;
            }
            return dummy.next;
        }
    }
}
