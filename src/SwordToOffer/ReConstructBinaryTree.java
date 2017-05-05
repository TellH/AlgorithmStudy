package SwordToOffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tlh on 2017/3/9.
 */
public class ReConstructBinaryTree {
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
//        reConstructBinaryTree(preOrder, inOrder);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
        public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
            if (pre == null | in == null || pre.length != in.length)
                throw new RuntimeException("Error");
            return reConstructBinaryTree(in, 0, findMidIndex(in, pre[0], 0), in.length - 1, pre, 0);
        }

        public static int findMidIndex(int[] in, int root, int from) {
            for (int i = from; i < in.length; i++) {
                if (in[i] == root)
                    return i;
            }
            throw new RuntimeException("Error");
        }

        public static TreeNode reConstructBinaryTree(int[] in, int inFrom, int inRootIndex, int inEnd, int[] pre, int preRootIndex) {
            TreeNode root = new TreeNode(pre[preRootIndex]);
            if (inFrom < inRootIndex) {
                root.left = reConstructBinaryTree(in, inFrom, findMidIndex(in, pre[preRootIndex + 1], inFrom), inRootIndex - 1, pre, preRootIndex + 1);
            }
            if (inRootIndex < inEnd) {
                int rightRootIndex = preRootIndex + inRootIndex - inFrom + 1;
                root.right = reConstructBinaryTree(in, inRootIndex + 1, findMidIndex(in, pre[rightRootIndex], inRootIndex + 1), inEnd, pre, rightRootIndex);
            }
            return root;
        }

    */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null | in == null || pre.length != in.length)
            throw new RuntimeException("Error");
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> map) {
        if (ps > pe)
            return null;
        TreeNode root = new TreeNode(pre[ps]);
        Integer index = map.get(pre[ps]);
        // 右边下标-左边下标+1=元素个数，因为包含了root节点要再减去1
        root.left = reConstructBinaryTree(pre, ps + 1, ps + index - is, in, is, index - 1, map);
        root.right = reConstructBinaryTree(pre, ps + index - is + 1, pe, in, index + 1, ie, map);
        return root;
    }


    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++)
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        return root;
    }
}
