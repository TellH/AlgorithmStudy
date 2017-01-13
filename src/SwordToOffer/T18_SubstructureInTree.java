package SwordToOffer;

/**
 * Created by tlh on 2017/1/13.
 * 输入两棵二叉树A,B，判断B是不是A的子结构。
 *
 */
public class T18_SubstructureInTree {
    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 先遍历找出父树的结点中与子树的根结点相同的结点
    // 以该结点为基础调用doesTreeHasSubtree，继续判断子树结点是否相等。
    public boolean hasSubtree(Node root, Node subRoot) {
        if (root == null || subRoot == null)
            return false;
        boolean result = false;
        if (root.value == subRoot.value)
            result = doesTreeHasSubtree(root, subRoot);
        if (!result)
            result = hasSubtree(root.left, subRoot);
        if (!result)
            result = hasSubtree(root.right, subRoot);
        return result;
    }

    private boolean doesTreeHasSubtree(Node root, Node subRoot) {
        //如果子树结点为空，证明前面比较过的结点都是相等。此时root可以是非空的
        if (subRoot == null)
            return true;
        //subRoot不为空，root为空，不符合题意
        if (root == null)
            return false;
        return root.value == subRoot.value && doesTreeHasSubtree(root.left, subRoot.left) && doesTreeHasSubtree(root.right, subRoot.right);
    }
}
