package others;

/**
 * Created by tlh on 2017/5/5.
 * 求二叉树中节点的最大距离
 * 后序遍历，每个节点分别缓存左右子树的最长距离
 */
public class GetBinTreeMaxDistance {
    private int maxLen = Integer.MIN_VALUE;

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int maxLenLeft;
        int maxLenRight;
    }

    public void getMaxDistance(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null)
            root.maxLenLeft = 0;
        if (root.right == null)
            root.maxLenRight = 0;
        if (root.left != null)
            getMaxDistance(root.left);
        if (root.right != null)
            getMaxDistance(root.right);
        if (root.left != null) {
            root.maxLenLeft = Math.max(root.left.maxLenLeft, root.left.maxLenRight) + 1;
        }
        if (root.right != null) {
            root.maxLenRight = Math.max(root.right.maxLenLeft, root.right.maxLenRight) + 1;
        }
        int len = root.maxLenRight + root.maxLenLeft;
        if (maxLen < len)
            maxLen = root.maxLenLeft + root.maxLenRight;
    }
}
