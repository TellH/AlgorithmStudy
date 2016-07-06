package Graph.UnionFind;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tlh on 2016/7/6.
 * 加权quick-union算法
 */
public class WeightedQuickUnion {
    int n;
    int[] roots;
    int[] weight;

    public WeightedQuickUnion() {
        init();
    }

    public WeightedQuickUnion(int n) {
        this.n = n;
        roots = new int[n];
        weight = new int[n];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
            weight[i] = 1;
        }
    }

    private int findRoot(int v) {
        //将递归改为迭代
        Stack<Integer> stack = new Stack();//回退栈
        while (roots[v] != v) {
            stack.add(v);
            v = roots[v];
        }
        //将栈中roots值修改为祖先节点，加快搜索速度
        while (!stack.isEmpty()) {
            roots[stack.pop()] = v;
        }
        return v;
    }

    public void merge(int u, int v) {
        //寻找各自的王
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        if (uRoot == vRoot) return;
        //一树不能容二王，需要合并
        //将小树（权值小）的根节点连接到大树（权值大）的根节点
        if (weight[uRoot]>weight[vRoot]){
            roots[vRoot]=uRoot;
            weight[uRoot]+=weight[vRoot];
        }else {
            roots[uRoot]=vRoot;
            weight[vRoot]+=weight[uRoot];
        }
    }

    protected void init() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        roots = new int[n];
        weight = new int[n];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
            weight[i] = 1;
        }
//        in.close();
    }

    public boolean connected(int i, int j) {
        return findRoot(i) == findRoot(j);
    }

    public static void main(String[] args) {
        WeightedQuickUnion unionFind = new WeightedQuickUnion() {
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
