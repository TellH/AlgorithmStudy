package SwordToOffer;

/**
 * Created by tlh on 2017/3/30.
 * 求二叉搜索树的第k个节点
 */
public class KthNodeBST {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    private TreeNode result;
    private int count = 0;
    private int k;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null)
            return null;
        this.k = k;
        get(pRoot);
        return result;
    }

    private void get(TreeNode pRoot) {
        if (pRoot == null) return;
        get(pRoot.left);
        count++;
        if (count == k) {
            result = pRoot;
            return;
        }
        get(pRoot.right);
    }
}
