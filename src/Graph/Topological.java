package Graph;

import Graph.base.Graph;

/**
 * Created by tlh on 2016/7/9.
 * 拓扑排序，用于解决多个任务的调度问题。
 * 当且仅当一幅有向图是无环图时它才能进行拓扑排序
 * 一幅有向无环图的拓扑排序即为所有顶点的逆后序排序
 * 对于任意边u->v，在调用dfs(v)时，dfs(w)要么已经被调用，要么在dfs(v)之前返回，
 * 所以越早回溯(越深)的顶点就会被压倒栈底。
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Graph graph) {
        DirectedCycle cycle = new DirectedCycle(graph);
        if (!cycle.hasCircle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(graph);
            order = dfo.reversePost();
        }
    }

    //拓扑排序后的顶点
    public Iterable<Integer> order() {
        return order;
    }

    //图是有向无环图吗
    public boolean isDAG() {
        return order != null;
    }
}
