package Graph;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/3.
 * 并查集
 * 通过一个一维数组，维护一个森林。刚开始，森林的每个点都是孤立的，也可以理解为每个点就是一棵只有一个结点的数，
 * 之后通过两个结点间的连通性逐渐将小树合并。
 */
public class UnionFind {
    int n;
    int[] roots;

    protected void init() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        roots = new int[n];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
//        in.close();
    }

    private int findRoot(int v) {
        //自己是自己的王就是大王
        if (roots[v] == v)
            return v;
        else {
            roots[v] = findRoot(roots[v]);
            return roots[v];
        }
    }

    public void merge(int u, int v) {
        //寻找各自的王
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        //一树不能容二王，需要合并
        if (uRoot != vRoot) {
            //擒贼先擒王，右贼的王归属左贼的王
            roots[vRoot] = uRoot;
        }
    }

    public UnionFind() {
        init();
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind() {
            @Override
            protected void init() {
                super.init();
                Scanner in = new Scanner(System.in);
                int m = in.nextInt();
                for (int i = 0; i < m; i++) {
                    merge(in.nextInt(), in.nextInt());
                }
                in.close();
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    if (roots[i] == i)
                        sum++;
                }
                System.out.println(sum);
            }
        };
    }
}
