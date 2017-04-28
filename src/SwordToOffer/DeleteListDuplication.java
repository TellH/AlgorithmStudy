package SwordToOffer;

/**
 * Created by tlh on 2017/3/28.
 */
public class DeleteListDuplication {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) return null;
        ListNode p = pHead.next, pre = pHead;
        ListNode bak;
        while (true) {
            bak = pre;
            pre = findDuplication(pre);
            if (pre != bak) {
                if (pre.next == null)
                    return null;
                pHead = pre = pre.next;
                p = pre.next;
            } else break;
        }
        while (true) {
            if (p == null)
                break;
            bak = p;
            p = findDuplication(p);
            if (p != bak) {
                pre.next = p.next;
                p = pre.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return pHead;
    }

    private ListNode findDuplication(ListNode p) {
        while (true) {
            if (p.next == null)
                break;
            if (p.val == p.next.val)
                p = p.next;
            else break;
        }
        return p;
    }

}
