package Search.BST;

/**
 * Created by tlh on 2016/7/7.
 * ref:"http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html"
 */
public class RedBlackBST<K extends Comparable, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node root;

    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V val) {
        //将新节点插到叶节点上，并用红链接相连
        if (h == null) return new Node(key, val, RED);
        //二叉搜索树的递归插入
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        //插入完成后，需要回溯调整，将红链接向上传递。
        // 在沿着插入点到跟结点的路径向上回溯时所经过的每一个结点中顺序完成以下操作，就能完成插入操作
        //因为是顺序完成，所以用三个if语句而非if else
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    //与二叉搜索树相同
    private V get(K key) {
        if (key == null) return null;
        Node p = root;
        int cmp;
        while (p != null) {
            cmp = key.compareTo(p.key);
            if (cmp < 0) p = p.left;
            else if (cmp > 0) p = p.right;
            else return p.val;
        }
        return null;
    }


}
