package Graph;

import java.util.List;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/4.
 * R.Tarjan发明了基于DFS的寻找割点和割边算法
 * 当深度优先遍历时访问到u点，此时图就会被k点分割成两部分，一部分是已经被访问过的点，另一部分是没有被访问过的点。如果u点是割点
 * 那么剩下的没有访问过的点中至少有一个点在不经过u点的情况下，是无论如何再也回不到已访问过的点了。
 * 用dfn[u]记录节点u在DFS过程中被遍历到的次序号(时间戳)，
 * low[u]记录节点u或u的子树通过非父节点追溯到最早的祖先节点的时间戳（即DFS次序号最小）。
 * 那么low[u]的计算过程如下：
 * low[u]={ min{low[u], low[v]}      (u,v)为树边(在DFS过程中访问未访问节点v时所经过的边)
 * or  min{low[u], dfn[v]}      (u,v)为回边(在DFS过程中遇到已访问节点v时所经过的边)且v不为u的父亲节点
 * }
 * 对于某个顶点u，如果存在一个顶点v（即顶点u的儿子），使得low[v]>=dfn[u] 即不能回到祖先，那么u是割点
 * 整个算法只需深度优先遍历一次
 */
public abstract class FindCutVertexEdge {
    abstract void onFoundCutVertex(int cutVex);

    abstract void onFoundCutEdge(int u, int v);

    int n, m;
    int[] dfn, low;
    int step = 0;
    AdjacencyArrayList adjList;
    int root;

    public FindCutVertexEdge() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        dfn = new int[n];
        low = new int[n];
        //因为是无向图，所以是2m
        adjList = new AdjacencyArrayList(n, 2 * m);
        //读入边
        int count = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            adjList.add(count++, u, v, 1);
            adjList.add(count++, v, u, 1);
        }
        in.close();
    }

    public void run() {
        root = 0;
        dfs(0, root);
    }

    void dfs(int curVex, int parent) {
        step++;
        dfn[curVex] = step;
        low[curVex] = step;
        int child = 0;
        List<Edge> adjacentEdge = adjList.getAdjacentEdge(curVex);
        for (Edge edge : adjacentEdge) {
            //如果顶点v是时间戳为0，说明顶点v还没被访问过，v是parent的子节点
            if (dfn[edge.v] == 0) {
                child++;
                //继续深度优先搜索
                dfs(edge.v, curVex);
                //此时已经搜索完了一条支路
                //更新一下low数组，看能否通过子节点回到更早顶点
                low[curVex] = Math.min(low[curVex], low[edge.v]);
                //如果不经过父节点不能回到祖先节点和父节点，则这条边是割边
                if (low[edge.v] > dfn[curVex])
                    onFoundCutEdge(edge.u, edge.v);
                //判断割点时由一个特殊情况，curVex是DFS搜索树的根节点
                //当前节点curVex不是根节点，存在子节点不经过curVex就无法回到祖先节点，则curVex是割点
                if (curVex != root && low[edge.v] >= dfn[curVex]) {
                    onFoundCutVertex(curVex);
                }
                //当前节点curVex是根节点，并且DFS搜索树至少两个子节点，则根节点是割点
                else if (curVex == root && child == 2)
                    onFoundCutVertex(curVex);
            } else if (edge.v != parent) {
                //顶点v已经访问过，并且这个顶点不是当前顶点curVex的父节点
                //则更新当前节点curVex能访问更早节点的时间戳
                low[curVex] = Math.min(low[curVex], dfn[edge.v]);
            }
        }
    }

    public static void main(String[] args) {
        new FindCutVertexEdge() {
            @Override
            void onFoundCutVertex(int cutVex) {
                System.out.println("割点：" + cutVex);
            }
            @Override
            void onFoundCutEdge(int u, int v) {
                System.out.println("割边：" + u + "-" + v);
            }
        }.run();
    }
}
