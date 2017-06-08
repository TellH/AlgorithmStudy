package leetcode;

/**
 * Created by tlh on 2017/6/6.
 * Given a binary tree containing digits from0-9only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path1->2->3which represents the number123.
 * Find the total sum of all root-to-leaf numbers.
 */
public class SumRootToLeafNumbers {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return preOrder(root, 0);
    }

    private int preOrder(TreeNode root, int sum) {
        if (root == null)
            return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null)
            return sum;
        return preOrder(root.left, sum) + preOrder(root.right, sum);
    }
}
