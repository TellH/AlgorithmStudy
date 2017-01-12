package SwordToOffer;

import java.util.Scanner;

/**
 * Created by tlh on 2017/1/12.
 * 重建二叉树
 * 输入某二叉树的前序遍历和后续遍历，重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class T6 {
    private int[] preOrder;//前序遍历数组
    private int[] inOrder;//中序遍历数组

    private T6(int[] preOrder, int[] inOrder) {
        this.preOrder = preOrder;
        this.inOrder = inOrder;
    }

    private class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node run() {
        return build(0, findLocInOrder(0, inOrder.length, preOrder[0]), inOrder.length - 1, 0);
    }

    private Node build(int left, int mid, int right, int rootInPreOrder) {
        //left~mid-1是左子树，mid+1~right是右子树
        Node root = new Node(inOrder[mid]);
        if (mid > left) {
            //递归构建左子树
            int locInPreOrder = rootInPreOrder + 1;
            int valLeftRoot = preOrder[locInPreOrder];
            int locLeftRoot = findLocInOrder(left, mid, valLeftRoot);
            root.left = build(left, locLeftRoot, mid - 1, locInPreOrder);
        } /*else root.left = null;*/
        if (right > mid) {
            //递归构建右子树
            int locInPreOrder = rootInPreOrder + mid - left + 1;
            int valRightRoot = preOrder[locInPreOrder];
            int locRightRoot = findLocInOrder(mid + 1, right, valRightRoot);
            root.right = build(mid + 1, locRightRoot, right, locInPreOrder);
        }
        return root;
    }

    /**
     * 在inOrder数组中找出值为val的元素，返回它的下标
     *
     * @param left
     * @param right
     * @param val
     * @return
     */
    private int findLocInOrder(int left, int right, int val) {
        int locLeftRoot = -1;
        for (int i = left; i <= right; i++) {
            if (inOrder[i] != val)
                continue;
            locLeftRoot = i;
            break;
        }
        if (locLeftRoot == -1)
            throw new RuntimeException("invalid input!");
        return locLeftRoot;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] preOrder = new int[n];
        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            inOrder[i] = in.nextInt();
        }
        T6 t6 = new T6(preOrder, inOrder);
        Node root = t6.run();
        System.out.println(root.value);
    }
}
