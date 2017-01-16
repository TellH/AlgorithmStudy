package SwordToOffer;

import com.sun.net.httpserver.Authenticator;

/**
 * Created by tlh on 2017/1/15.
 * 求二叉树的深度
 */
public class T39_BinTreeDepth {
    private class Node {
        private Node left;
        private Node right;
    }

    public static int treeDepth(Node node) {
        if (node == null)
            return 0;
        int leftDepth = treeDepth(node.left);
        int rightDepth = treeDepth(node.right);
        return Math.max(leftDepth + 1, rightDepth + 1);
    }

    public static int testBalanced(Node node) {
        if (node == null)
            return 0;
        int leftDepth = testBalanced(node.left);
        int rightDepth = testBalanced(node.right);
        if (leftDepth == -1 || rightDepth == -1)
            return -1;
        if (Math.abs(leftDepth - rightDepth) > 1)
            return -1;
        return Math.max(leftDepth + 1, rightDepth + 1);
    }
}
