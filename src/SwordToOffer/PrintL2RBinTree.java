package SwordToOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tlh on 2017/3/29.
 */
public class PrintL2RBinTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) return result;
        int count = 1;
        queue.add(pRoot);
        while (true) {
            ArrayList<Integer> list = new ArrayList<>();
            count = printLevel(queue, count, list);
            result.add(list);
            if (count == 0) break;
        }
        return result;
    }

    private int printLevel(Queue<TreeNode> queue, int count, ArrayList<Integer> list) {
        int cnt = 0;
        for (int i = 0; i < count; i++) {
            TreeNode node = queue.poll();
            list.add(node.val);
            cnt = addNode(queue, cnt, node.left);
            cnt = addNode(queue, cnt, node.right);
        }
        return cnt;
    }

    private int addNode(Queue<TreeNode> queue, int count, TreeNode node) {
        if (node == null) return count;
        queue.offer(node);
        count++;
        return count;
    }
}
