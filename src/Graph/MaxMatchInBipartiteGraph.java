package Graph;

import java.util.List;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/5.
 * 二分图的最大匹配 匈牙利算法
 * 二分图定义：如果一个图的所有顶点可以被分为X和Y两个集合，并且所有边的两耳顶点恰好一个属于集合X，另一个属于集合Y，
 * 即每个集合内的顶点没有边相连。
 * 算法的关键是寻找增广路，找到一条增广路，匹配数加1
 * 算法步骤：
 * 1.分别从每一个未被匹配的点u开始，从点u的边中任意选一条边开始配对。如果此时点v还没有被皮队，这配对成功，找到了一条增广路；
 * 如果此时点v已经被配对了，那就要尝试对点v进行一次DFS搜索。如果找到增广路，需要更新原来的匹配关系。用一个数组match来记录匹配关系。
 * 2.如果u与v匹配失败，再重新从点u的邻边中选一条边进行步骤一的尝试，直到点u匹配成功或尝试完所有过点u的所有边为止。
 */
public class MaxMatchInBipartiteGraph {
    int n;//顶点数
    int m;//边数
    int[] book;//用于DFS寻找增广路时记录走过的路径
    int[] match;//记录匹配顶点
    AdjacencyArrayList edgeList;//邻接表

    public MaxMatchInBipartiteGraph() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();//读入顶点数
        m = in.nextInt();//读入边数
        book = new int[n];
        match = new int[n];
        for (int i = 0; i < match.length; i++) {
            match[i] = -1;//-1表示该顶点未匹配
        }
        edgeList = new AdjacencyArrayList(n, 2 * m);//无向图，邻接表边数可以为2m
        //读入边
        int count = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            edgeList.add(count++, u, v, 1);
            edgeList.add(count++, v, u, 1);//无向图，同一条边要添加两次不同方向
        }
        in.close();
    }

    //清除上次DFS寻找增广路时的标记
    public void clearBook() {
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
    }

    //判断顶点i是否一定有匹配了
    public boolean isMatch(int i) {
        return match[i] != -1;
    }

    //关键方法，用DFS搜索增广路
    boolean dfs(int curVex) {
        List<Edge> adjacentEdge = edgeList.getAdjacentEdge(curVex);
        //尝试每一个与顶点curVex相连的顶点，根据二分图的定义，这些顶点一定是属于另一个集合的
        for (Edge edge : adjacentEdge) {
            if (book[edge.v] == 0) {
                book[edge.v] = 1;
                //如果点v未被匹配或者找到新的匹配，更新match数组，找到增广路立即return
                if (!isMatch(edge.v) || dfs(edge.v)) {
                    match[edge.v] = curVex;
                    match[curVex] = edge.v;
                    return true;
                }
            }
        }
        return false;//没有找到增广路
    }

    public static void main(String[] args) {
        int match = 0;//记录匹配数
        MaxMatchInBipartiteGraph bipartiteGraph = new MaxMatchInBipartiteGraph();
        //不放过任何一个没找到匹配的顶点
        for (int i = 0; i < bipartiteGraph.n; i++) {
            bipartiteGraph.clearBook();
            //从没有匹配的顶点开始寻找增广路
            //当然，不加条件!bipartiteGraph.isMatch(i) 也可以，就是会增加一些多余的搜索
            if (!bipartiteGraph.isMatch(i) && bipartiteGraph.dfs(i)) match++;
        }
        System.out.println(match);
    }
}
