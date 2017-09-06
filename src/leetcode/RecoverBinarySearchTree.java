package leetcode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * 中序遍历
 */
public class RecoverBinarySearchTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode pre, mis1, mis2;

    public void recoverTree(TreeNode root) {
        doTraversal(root);
        swapNode(mis1, mis2);
    }

    private void doTraversal(TreeNode root) {
        if (root == null) return;
        doTraversal(root.left);
        if (pre != null && pre.val > root.val) {
            if (mis1 == null) {
                mis1 = pre;
                mis2 = root;
            } else {
                mis2 = root;
            }
        }
        pre = root;
        doTraversal(root.right);
    }

    private void swapNode(TreeNode left, TreeNode right) {
        int temp = left.val;
        left.val = right.val;
        right.val = temp;
    }
}
