package Search.BST;

/**
 * Created by tlh on 2016/7/7.
 * ref:"http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html"
 * 红黑树的五个性质：
 * 1）每个结点要么是红的，要么是黑的。
 * 2）根结点是黑的。
 * 3）每个叶结点，即空结点（NIL）是黑的。
 * 4）如果一个结点是红的，那么它的俩个儿子都是黑的。红结点必须在左边
 * 5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点。
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

        void cover(Node with) {
            key = with.key;
            val = with.val;
        }

        void replace(Node with) {
            key = with.key;
            val = with.val;
            left = with.left;
            right = with.right;
        }
    }

    public class Student {
        public int i;

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
    public V get(K key) {
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

//    private Node[] _get(K key) {
//        if (key == null) return null;
//        Node[] p = new Node[2];
//        p[0] = root;
//        p[1] = p[0];
//        int cmp;
//        while (p[1] != null) {
//            cmp = key.compareTo(p[1].key);
//            if (cmp < 0) {
//                p[0] = p[1];
//                p[1] = p[1].left;
//            } else if (cmp > 0) {
//                p[0] = p[1];
//                p[1] = p[1].right;
//            } else return p;
//        }
//        return null;
//    }
//
//    //删除节点
//    public void delete(K key) {
//        Node[] ps = _get(key);
//        Node p = ps[1], front = ps[0];
//        if (p == null) return;
//        if (root == p) {
//            root = null;
//            return;
//        }
//        Node pt = front.right;
//        if (pt == null) {
//            p.replace(p.left);
//        }
//        while (pt.right != null) {
//            front = pt;
//            pt = pt.right;
//        }
//        front.right = pt.left;
//        if (front.right != null)
//            front.right.color = BLACK;
//        p.cover(pt);
//    }

}
