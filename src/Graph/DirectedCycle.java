package Graph;

import Graph.base.Edge;
import Graph.base.Graph;

import java.util.Stack;

/**
 * Created by tlh on 2016/7/9.
 * 检测有向图是否有环
 * 因为一幅图含有环的数量可能是图的大小的指数级别，所以我们只需找出一个环即可。仍然基于DFS。
 * 对图的所有连通子图进行深度优先搜索，维护一个onStack数组，用于记录当前还没有回溯的路径上的顶点（没有分叉）。
 * 一旦找到了一条有向边u->v且v已经存在于栈中，就找到了一个环，因为栈表示的是一条有u->v的有向路径，而v->u正好补全了这个环。
 */
public class DirectedCycle {
    private boolean[] marked;//记录是否经过DFS访问过
    private boolean[] onStack;//记录DFS已经访问但还没回溯的路径上的顶点
    private int[] edgeTo;//记录访问的前继结点，用于找到环后回溯得到环上的结点
    private Stack<Integer> cycle;//如果存在环，保存有向环的所有结点

    public DirectedCycle(Graph graph) {
        final int N = graph.V();
        marked = new boolean[N];
        onStack = new boolean[N];
        edgeTo = new int[N];
        //DFS所有连通子图
        for (int u = 0; u < N; u++) {
            if (!marked[u])
                dfs(graph, u);
        }
    }

    private void dfs(Graph graph, int u) {
        marked[u] = true;
        onStack[u] = true;
        for (Edge edge : graph.adj(u)) {
            if (hasCircle()) return;//找到有向环了，啥都不用往下做了
            else if (!marked[edge.v]) {//DFS没有访问过的结点
                edgeTo[edge.v] = u;
                dfs(graph, edge.v);
            } else if (onStack[edge.v]) {//搜索路径存在环
                //保存环上的结点
                cycle = new Stack<>();
                for (int x = u; x != edge.v; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(edge.v);
                cycle.push(u);
            }
        }
        onStack[u] = false;//最后一定要在每个顶点回溯时重置对应的onStack
    }

    public boolean hasCircle() {
        return cycle != null;
    }

    public Iterable<Integer> getCycle() {
        return cycle;
    }
}
