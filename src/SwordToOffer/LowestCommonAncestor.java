package SwordToOffer;

/**
 * Created by tlh on 2017/4/27.
 * 二叉树中两个节点的最近公共父节点
 * 解法1. 用两个栈分别记录从根节点到指定节点的路径
 * 解法2. 后序遍历
 * 解法3. 如果是二查搜索树呢？
 */
public class LowestCommonAncestor {
    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
    }

    // 后序遍历
    public TreeNode getLowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null)
            return null;
        if (root == a || root == b) return root;
        TreeNode left = getLowestCommonAncestor(root.left, a, b);
        TreeNode right = getLowestCommonAncestor(root.right, a, b);
        if (left != null && right != null) return root; // a和b分布在root的两侧
        return left != null ? left : right; // a和b分布在root的同侧
    }

    // 如果是二叉排序树
    public TreeNode getLowestCommonAncestor_(TreeNode root, TreeNode a, TreeNode b) {
        int min = Math.min(a.data, b.data);
        int max = Math.max(a.data, b.data);
        TreeNode p = root;
        while (p != null) {
            if (p.data >= min && p.data <= max) return p;
            else if (p.data > min && p.data > max) p = p.left;
            else p = p.right;
        }
        return null;
    }
}
