package SwordToOffer;

/**
 * Created by tlh on 2017/3/28.
 */
public class SymmetricalTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null)
            return true;
        if (p1 == null || p2 == null)
            return false;
        return p1.val == p2.val && isSymmetrical(p1.left, p2.right) && isSymmetrical(p1.right, p2.left);
    }
}
