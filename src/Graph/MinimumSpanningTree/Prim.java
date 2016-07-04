package Graph.MinimumSpanningTree;

import Graph.Tree.MinHeap;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/4.
 * Prim算法求最小生成树
 * 1.从任意一个顶点开始构造生成树，用一个一位数组book[]来标记哪些顶点已经被加入到生成树中，
 * 用数组dis2MST[]记录生成树到没加入到生成树的顶点的距离。
 * 2.从数组dis2MST[]中选出离生成树最近的顶点v(用堆优化)加入到生成树中，book[v]=1  再以v为起点，更新dis2MST[]
 * 3.重复第二步，直到加入生成树的顶点为n个为止。
 *
 * 用堆优化：
 * 将dis2MST[]中还没有被加入到生成树的顶点放入可以更新值的堆中，用一个一维数组posInHeap保存顶点在堆中数组的下标，
 * 以便在堆中找到并更新值。
 */
public class Prim {
    private static final int INF = 99999999;

    //用于堆排序的点
    private static class Node implements Comparable<Node> {
        int vexId;
        int dis;

        public Node(int vexId, int dis) {
            this.vexId = vexId;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    public static void main(String[] args) {
        int minWeight = 0;//生成树权值
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//顶点数
        int m = in.nextInt();//边数
        int[][] e = new int[n][n];//邻接矩阵
        //初始化邻接矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    e[i][j] = 0;
                else e[i][j] = INF;
            }
        }
        //读入边,无向图！
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            e[u][v] = w;
            e[v][u] = w;
        }
        //各个顶点到生成树的距离
        int[] dis2MST = new int[n];
        //记录顶点是否在生成树中
        int[] book = new int[n];
        //顶点对应在堆中数组的下标，便于修改堆中的值
        int[] posInHeap = new int[n];
        MinHeap<Node> heap = new MinHeap<Node>(Node.class) {
            @Override
            protected void swap(int i, int j, Node[] h) {
                super.swap(i, j, h);

                //同步更新
                int t = posInHeap[h[i].vexId];
                posInHeap[h[i].vexId] = posInHeap[h[j].vexId];
                posInHeap[h[j].vexId] = t;
            }
        };
//        dis2MST[0] = 0;
        //将0号顶点加入到生成树中
        book[0] = 1;
        //初始化dis2MST posInHeap heap
        for (int i = 1; i < n; i++) {
            dis2MST[i] = e[0][i];
            posInHeap[i] = heap.size() + 1;
            heap.push(new Node(i, dis2MST[i]));
        }
        while (!heap.isEmpty()) {
            //取出离生成树最近的顶点
            Node nearest = heap.pop();
            //将顶点加入到生成树中
            book[nearest.vexId] = 1;
            minWeight += nearest.dis;
//            dis2MST[min.vexId] = 0;//没必要
            //以顶点nearest.vexId为起点，遍历所有邻边，松弛dis2MST数组
            for (int i = 1; i < n; i++) {
                if (book[i] == 0 && e[nearest.vexId][i] < INF && dis2MST[i] > e[nearest.vexId][i]) {
                    dis2MST[i] = e[nearest.vexId][i];
                    heap.update(posInHeap[i], new Node(i, dis2MST[i]));//更新堆中节点的值，自动调整
                }
            }
        }
        in.close();

        //输出结果
        System.out.println(minWeight);
    }
}
