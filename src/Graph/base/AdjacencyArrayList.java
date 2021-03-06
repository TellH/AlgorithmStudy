package Graph.base;

import Graph.base.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/3.
 * 数组的方式构建邻接表
 * first[i]保存顶点edges[i].u的第一条边的编号
 * next[i]保存编号为i的边的下一条边的编号
 */
public class AdjacencyArrayList {
    private int n;//顶点数
    private int m;//边数
    public Edge[] edges;//边数组
    private int[] first;
    private int[] next;

    public AdjacencyArrayList(int n, int m) {
        this(n, m, new Edge[m]);
        this.n = n;
        this.m = m;
    }

    public AdjacencyArrayList(int n, int m, Edge[] edges) {
        this.n = n;
        this.m = m;
        this.edges = edges;
        first = new int[n];
        next = new int[m];
        //初始时没有边加入，数组初始化为-1
        for (int i = 0; i < n; i++) {
            first[i] = -1;
        }
    }

    public void add(int pos, int u, int v, int w) {
        edges[pos] = new Edge(u, v, w);
        next[pos] = first[edges[pos].u];
        first[edges[pos].u] = pos;
    }

    public AdjacencyArrayList() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        edges = new Edge[m];
        first = new int[n];
        next = new int[m];
        //初始时没有边加入，数组初始化为-1
        for (int i = 0; i < n; i++) {
            first[i] = -1;
        }
        //读入边，i是边的编号
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
            next[i] = first[edges[i].u];
            first[edges[i].u] = i;
        }
        in.close();
    }

    public List<Edge> getAdjacentEdge(int vertex) {
        List<Edge> list = new ArrayList<>(m);
        int edgeNum = first[vertex];
//        list.add(edges[edgeNum]);
        do {
            list.add(edges[edgeNum]);
            edgeNum = next[edgeNum];
        } while (edgeNum != -1);
        return list;
    }
}
