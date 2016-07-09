package Graph.UnionFind;

import Graph.base.Edge;
import Graph.base.Graph;
import Graph.DepthFirstOrder;

/**
 * Created by tlh on 2016/7/9.
 * 有向图的强连通分量
 * Kosaraju算法
 */
public class StrongConnectedComponent {
    private boolean[] marked;//是否已经访问过，即是否归类于某个子图
    private int[] type;//强连通子图的id
    private int count;//强连通分量

    public StrongConnectedComponent(Graph graph) {
        int n = graph.V();
        marked = new boolean[n];
        type = new int[n];
        //按照逆后序的顶点顺序进行DFS
        DepthFirstOrder order=new DepthFirstOrder(graph);
        for (Integer v : order.reversePost()) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    //每一次dfs都会搜索一个强连通子图的所有顶点
    private void dfs(Graph graph, int u) {
        marked[u] = true;
        type[u] = count;
        for (Edge edge : graph.adj(u)) {
            if (!marked[edge.v])
                dfs(graph, edge.v);
        }
    }
    //只有同一类才是连通的
    public boolean StringConnected(int u,int v){
        return type[u]==type[v];
    }
    public int getConnectedComponentId(int v){
        return type[v];
    }
    public int getCount(){
        return count;
    }
}
