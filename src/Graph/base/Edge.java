package Graph.base;

import Graph.Graph;

public class Edge {
    public int u;//起点
    public int v;//终点
    public int w;//边的权值

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}