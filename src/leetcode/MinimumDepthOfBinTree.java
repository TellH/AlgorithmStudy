package leetcode;

/**
 * Created by tlh on 2017/5/27.
 * 难度：Easy
 * 求二叉树的最小深度
 * 需要考虑单子树的情形。
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
public class MinimumDepthOfBinTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int minDepth = Integer.MAX_VALUE;

    public int run(TreeNode root) {
        if (root == null)
            return 0;
        run(root, 1);
        return minDepth;
    }

    private void run(TreeNode root, int depth) {
        // 叶节点
        if (root.left == null && root.right == null) {
            minDepth = Math.min(minDepth, depth);
            return;
        }
        if (root.left != null)
            run(root.left, depth + 1);
        if (root.right != null)
            run(root.right, depth + 1);
    }

    // 另一种写法
    public int getMinDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left != null && root.right != null)
            return 1 + Math.min(getMinDepth(root.left), getMinDepth(root.right));
            // getMinDepth(root.left) 和 getMinDepth(root.right) 必然有一个等于0
        else return 1 + getMinDepth(root.left) + getMinDepth(root.right);
    }
}
