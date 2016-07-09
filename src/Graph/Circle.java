package Graph;

import Graph.base.Edge;
import Graph.base.Graph;

/**
 * Created by tlh on 2016/7/9.
 * 判断图是否有环
 */
public class Circle {
    private boolean[] marked;
    private boolean hasCircle;

    public boolean hasCircle() {
        return hasCircle;
    }

    public Circle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v])
                dfs(graph, v, v);
        }
    }

    private void dfs(Graph graph, int cur, int pre) {
        marked[cur] = true;
        for (Edge edge : graph.adj(cur)) {
            if (!marked[edge.v])
                dfs(graph, edge.v, cur);
            //如果这个邻接点已经被访问过而又不是父节点，那么就必定存在环
            else if (edge.v != pre) hasCircle = true;
        }
    }
}
