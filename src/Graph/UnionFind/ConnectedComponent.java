package Graph.UnionFind;

import Graph.base.Edge;
import Graph.base.Graph;

/**
 * Created by tlh on 2016/7/9.
 * 连通分量
 * 比较这种基于DFS解决图的连通性问题的方法与union-find算法
 * union-find算法不需要完整地构造并表示一幅图，这是一种动态算法，能在任何时候判断两个顶点是否连通
 * DFS更适合用于实现图的抽象数据类型，union-find算法更适合有大量连通性查询和插入操作混合等操作
 */
public class ConnectedComponent {
    private boolean[] marked;//是否已经访问过，即是否归类于某个子图
    private int[] type;//连通子图的id
    private int count;//连通分量

    public ConnectedComponent(Graph graph) {
        int n = graph.V();
        marked = new boolean[n];
        type = new int[n];
        for (int v = 0; v < n; v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    //每一次dfs都会遍历一个连通子图的所有顶点
    private void dfs(Graph graph, int u) {
        marked[u] = true;
        type[u] = count;
        for (Edge edge : graph.adj(u)) {
            if (!marked[edge.v])
                dfs(graph, edge.v);
        }
    }
    //只有同一类才是连通的
    public boolean connected(int u,int v){
        return type[u]==type[v];
    }
    public int getConnectedComponentId(int v){
        return type[v];
    }
    public int getCount(){
        return count;
    }
}
