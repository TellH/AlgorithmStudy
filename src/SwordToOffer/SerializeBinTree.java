package SwordToOffer;

/**
 * Created by tlh on 2017/3/29.
 */
public class SerializeBinTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    StringBuilder sb = new StringBuilder();

    String Serialize(TreeNode root) {
        saveTree(root);
        return sb.toString();
    }

    private void saveTree(TreeNode root) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        saveTree(root.left);
        saveTree(root.right);
    }

    TreeNode Deserialize(String str) {
        String[] nodes = str.split(",");
        return rebuildTree(nodes, new int[]{0});
    }

    private TreeNode rebuildTree(String[] nodes, int[] i) {
        if (i[0] >= nodes.length) return null;
        String val = nodes[i[0]];
        TreeNode node;
        if (!val.equals("#")) {
            node = new TreeNode(Integer.valueOf(nodes[i[0]]));
            i[0]++;
            node.left = rebuildTree(nodes, i);
            i[0]++;
            node.right = rebuildTree(nodes, i);
        } else {
            node = null;
        }
        return node;
    }
}
