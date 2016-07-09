package Search.DepthFirstSearch;

import Graph.Graph;
import Graph.Edge;

import java.util.Stack;

/**
 * Created by tlh on 2016/7/9.
 * 图的深度优先搜索
 */
public class DepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPath(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int cur) {
        marked[cur] = true;
        for (Edge edge : graph.adj(cur)) {
            if (!marked[edge.v]) {
                edgeTo[edge.v] = cur;
                dfs(graph, edge.v);
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path=new Stack<>();
        for (int x=v;x!=s;x=edgeTo[x]){
            path.push(x);
        }
        return path;
    }
}
