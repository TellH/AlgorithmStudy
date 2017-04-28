package SwordToOffer;

/**
 * Created by tlh on 2017/3/28.
 */
public class NextInOrderTreeNode {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        if (pNode.right != null) { // 如果有右子树，返回右子树最左的节点
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        TreeLinkNode parent = pNode.next;
        while (parent != null) {
            if (parent.left == pNode) {
                return parent;
            } else {
                pNode = parent;
                parent = parent.next;
            }
        }
        return null;
    }
}
