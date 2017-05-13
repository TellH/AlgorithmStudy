package SwordToOffer;

/**
 * Created by tlh on 2017/3/14.
 */
public class BinTree2OrderList {
    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    TreeNode head = null;
    TreeNode realHead = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return realHead;
    }
    public void ConvertSub(TreeNode root){
        if(root==null)
            return;
        Convert(root.left);
        if(realHead==null){
            realHead = head = root;
        }else{
            root.left=head;
            head.right= root;
            head = root;
        }
        Convert(root.right);
    }
}
