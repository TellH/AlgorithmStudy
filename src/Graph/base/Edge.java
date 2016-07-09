package Graph.base;

public class Edge {
    public int u;//起点
    public int v;//终点
    public double w;//边的权值

    public Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}