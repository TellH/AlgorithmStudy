package SwordToOffer;

/**
 * Created by tlh on 2017/3/20.
 */
public class IsBalancedTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public class Depth {
        int depth;

        public Depth(int depth) {
            this.depth = depth;
        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return IsBalanced_Solution(root, new Depth(0));
    }

    public boolean IsBalanced_Solution(TreeNode root, Depth depth) {
        if (root == null) {
            depth.depth = 0;
            return true;
        }
        Depth leftDepth = new Depth(0);
        Depth rightDepth = new Depth(0);
        if (IsBalanced_Solution(root.left, leftDepth)
                && IsBalanced_Solution(root.right, rightDepth)) {
            int diff = Math.abs(leftDepth.depth - rightDepth.depth);
            if (diff <= 1) {
                depth.depth = Math.max(leftDepth.depth, rightDepth.depth) + 1;
                return true;
            }
        }
        return false;
    }
}
