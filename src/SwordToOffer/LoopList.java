package SwordToOffer;

/**
 * Created by tlh on 2017/3/28.
 */
public class LoopList {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) return null;
        ListNode spt = pHead, fpt = pHead;
        while (true) {
            if (spt.next == null || fpt.next == null || fpt.next.next == null)
                return null;
            spt = spt.next;
            fpt = fpt.next.next;
            if (spt == fpt) // 在环内
                break;
        }
        int loopCnt = 0;
        while (true) {
            spt = spt.next;
            loopCnt++;
            if (spt == fpt)
                break;
        }

        fpt = pHead;
        spt = pHead;
        int step = 0;
        while (true) {
            fpt = fpt.next;
            step++;
            if (step == loopCnt)
                break;
        }
        while (true) {
            if (spt == fpt)
                break;
            spt = spt.next;
            fpt = fpt.next;
        }
        return spt;
    }
}
