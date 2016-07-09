package Graph;

import Graph.base.Edge;
import Graph.base.Graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tlh on 2016/7/9.
 * 基于DFS的顶点排序
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;//前序
    private Queue<Integer> post;//后序
    private Stack<Integer> reversePost;//逆后序

    public DepthFirstOrder(Graph graph) {
        int n = graph.V();
        pre = new ArrayBlockingQueue<>(n);
        post = new ArrayBlockingQueue<>(n);
        reversePost = new Stack<>();
        marked = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(graph, v);
    }

    private void dfs(Graph graph, int u) {
        marked[u] = true;
        pre.add(u);//在递归调用前将顶点加入队列
        for (Edge edge : graph.adj(u))
            if (!marked[edge.v]) dfs(graph, edge.v);
        post.add(u);//在递归调用之后将顶点加入队列
        reversePost.push(u);//在递归调用之后将顶点入栈
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
