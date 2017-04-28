package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by tlh on 2017/3/29.
 */
public class PrintZhiBinTree {
    private ArrayList<ArrayList<Integer>> result;

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    boolean flag;

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        result = new ArrayList<>();
        int count = 1;
        s1.push(pRoot);
        while (true) {
            if (!flag) {
                count = printLevel1(s1, s2, count);
            } else {
                count = printLevel2(s2, s1, count);
            }
            if (count == 0) break;
            flag = !flag;
        }
        return result;
    }

    private int printLevel1(Stack<TreeNode> s1, Stack<TreeNode> s2, int count) {
        ArrayList<Integer> level = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < count; i++) {
            TreeNode node = s1.pop();
            level.add(node.val);
            cnt = addNode(s2, cnt, node.left);
            cnt = addNode(s2, cnt, node.right);
        }
        result.add(level);
        return cnt;
    }

    private int addNode(Stack<TreeNode> s, int cnt, TreeNode node) {
        if (node == null)
            return cnt;
        s.push(node);
        return ++cnt;
    }

    private int printLevel2(Stack<TreeNode> s1, Stack<TreeNode> s2, int count) {
        ArrayList<Integer> level = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < count; i++) {
            TreeNode node = s1.pop();
            level.add(node.val);
            cnt = addNode(s2, cnt, node.right);
            cnt = addNode(s2, cnt, node.left);
        }
        result.add(level);
        return cnt;
    }
}
