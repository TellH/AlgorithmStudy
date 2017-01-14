package SwordToOffer;

/**
 * Created by tlh on 2017/1/13.
 * 二叉搜索树转有序的双向链表
 */
public class T27_ConvertBinarySearchTree {
    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node lastNodeInList = null;

    //中序遍历二叉搜索树，lastNodeInList指向转换好的双向链表的最后一个结点。
    public void convert(Node pCurrent) {
        if (pCurrent == null)
            return;
        if (pCurrent.left != null)
            convert(pCurrent.left);
        pCurrent.left = lastNodeInList;
        if (lastNodeInList != null)
            lastNodeInList.right = pCurrent;
        lastNodeInList = pCurrent;
        if (pCurrent.right != null)
            convert(pCurrent.right);
    }
}
