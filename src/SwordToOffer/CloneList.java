package SwordToOffer;

/**
 * Created by tlh on 2017/3/14.
 * 复杂链表的复制
 */
public class CloneList {
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

    }

    public class RandomListNodeWrapper extends RandomListNode {
        final RandomListNode core;

        public RandomListNodeWrapper(RandomListNode core) {
            super(core.label);
            this.core = core;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RandomListNode)) return false;
            RandomListNode that = (RandomListNode) o;
            if (label != that.label) return false;
            if (next != null ? !next.equals(that.next) : that.next != null) return false;
            return random != null ? random.equals(that.random) : that.random == null;
        }

        @Override
        public int hashCode() {
            int result = label;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            result = 31 * result + (random != null ? random.hashCode() : 0);
            return result;
        }
    }

/*    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        Map<RandomListNodeWrapper, RandomListNode> map = new HashMap<>();
        RandomListNode pCloneHead = new RandomListNode(pHead.label);
        RandomListNode p = pCloneHead;
        while (pHead != null) {
            p.next = new RandomListNode(pHead.next.label);
            RandomListNode cloneNode = map.get(new RandomListNodeWrapper(pHead));
            if (cloneNode != null) {
                cloneNode.random = p;
            }
            if (pHead.random != null)
                map.put(new RandomListNodeWrapper(pHead.random), p);
            p = p.next;
            pHead = pHead.next;
        }
        return pCloneHead;
    }*/

/*    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode pClonedHead = new RandomListNode(pHead.label);
        pClonedHead.next = pHead.next;
        pClonedHead.random = pHead.random;
        pClonedHead.next = Clone(pHead.next);
        return pClonedHead;
    }*/

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode currNode = pHead;
        while (currNode != null) {
            RandomListNode clone = new RandomListNode(currNode.label);
            clone.next = currNode.next;
            currNode.next = clone;
            currNode = clone.next;
        }
        currNode = pHead;
        while (currNode != null) {
            RandomListNode cloneNode = currNode.next;
            if (currNode.random != null)
                cloneNode.random = currNode.random.next;
            currNode = cloneNode.next;
        }
        currNode = pHead;
        RandomListNode pClonedHead = pHead.next, temp;
        //拆分链表，让每一个节点的next都指向next.next
        while (currNode.next != null) {
            temp = currNode.next;
            currNode.next = currNode.next.next;
            currNode = temp;
        }
        return pClonedHead;
    }

    public static void main(String[] args) {
        CloneList run = new CloneList();
        RandomListNode n0 = new RandomListNode(0);
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        RandomListNode n4 = new RandomListNode(4);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
//        run.Clone()
    }
}
