package leetcode;

/**
 * Given a binary tree
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set toNULL.
 * Initially, all next pointers are set toNULL.
 * You may only use constant extra space.
 */
public class PopulatingNextRightPointersInEachNode {
    private class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode p = root, q;
        TreeLinkNode dummy = new TreeLinkNode(-1);
        while (p != null) {
            q = p;
            TreeLinkNode link = dummy;
            while (q != null) {
                if (q.left != null) {
                    link.next = q.left;
                    link = link.next;
                }
                if (q.right != null) {
                    link.next = q.right;
                    link = link.next;
                }
                q = q.next;
            }
            p = getNextLevelNode(p);
        }
    }

    private TreeLinkNode getNextLevelNode(TreeLinkNode p) {
        while (p != null) {
            if (p.left != null)
                return p.left;
            else if (p.right != null)
                return p.right;
            else p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
