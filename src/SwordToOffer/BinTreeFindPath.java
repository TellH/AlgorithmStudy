package SwordToOffer;

import java.util.ArrayList;

/**
 * Created by tlh on 2017/3/13.
 */
public class BinTreeFindPath {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        if (root == null)
            return paths;
        ArrayList<Integer> path = new ArrayList<>();
        FindPath(root, target, paths, path);
        return paths;
    }

    private void FindPath(TreeNode root, int target, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path) {
        if (root == null) {
            int sum = 0;
            for (Integer n : path)
                sum += n;
            if (sum == target) {
                ArrayList<Integer> copyList = new ArrayList<>();
                copyList.addAll(path);
                if (!paths.contains(copyList))
                    paths.add(copyList);
            }
            return;
        }
        path.add(root.val);
        FindPath(root.left, target, paths, path);
        FindPath(root.right, target, paths, path);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        BinTreeFindPath obj = new BinTreeFindPath();
    }
}
