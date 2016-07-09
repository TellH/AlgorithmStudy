package Search.BreadthFirstSearch;

import Graph.base.Edge;
import Graph.base.Graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tlh on 2016/7/9.
 * 图的深度优先搜索
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(graph.V());
        marked[s] = true;
        queue.add(s);
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (Edge edge : graph.adj(cur)) {
                if (!marked[edge.v]) {
                    marked[edge.v]=true;
                    edgeTo[edge.v] = cur;
                    queue.add(edge.v);
                }
            }
        }

    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        return path;
    }
}
