package leetcode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 */
public class PathSum {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return findPath(root, sum);
    }

    private boolean findPath(TreeNode root, int sum) {
        if (root.left == null && root.right == null) return sum - root.val == 0;
        boolean found = false;
        if (root.left != null) found = findPath(root.left, sum - root.val);
        return found || root.right != null && findPath(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        System.out.println(new PathSum().hasPathSum(root, -2));
    }
}
