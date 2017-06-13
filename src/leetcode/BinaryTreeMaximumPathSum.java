package leetcode;

/**
 * Created by tlh on 2017/6/9.
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * 后序遍历二叉树
 */
public class BinaryTreeMaximumPathSum {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        getPath(root);
        return maxSum;
    }

    private int getPath(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, getPath(root.left));
        int right = Math.max(0, getPath(root.right));
        maxSum = Math.max(maxSum, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
