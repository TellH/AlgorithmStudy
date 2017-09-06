package leetcode;

public class ConvertSortedArrayToBinarySearchTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null) return null;
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(num[start]);
        int mid = (start + end) / 2 + (start + end) % 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, start, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, end);
        return root;
    }
}
