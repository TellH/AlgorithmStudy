package Graph.BipartiteGraph;

import Graph.base.Edge;
import Graph.base.Graph;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/5.
 * 判断该图是否是二分图
 * 利用深度优先遍历这个图，沿途为每一个节点染色
 * 染色规则是：如果当前节点u还没被染色，则染成黑色；其相邻的点v如果没有被染色则染成红色，如果v已经有色，判断其色是否与节点u的颜色一致，
 * 如果一致，则断定该图不是二分图，return，结束算法
 * 如果不一致，继续往下DFS。
 */
public class JudgeBipartiteGraph {
    private Graph graph;//图结构
    private int[] book;//用于DFS寻找增广路时记录走过的路径
    private int[] color;//记录各个顶点的颜色，1表示黑色，-1表示红色，0表示无色
    private boolean flag;//判断是否是二分图的标志

    public JudgeBipartiteGraph() {
        flag = true;//初始化的时候设置成true
        Scanner in = new Scanner(System.in);
        graph = new Graph(in,false);//无向图，输入时要注意
        int n = graph.V();
        int m = graph.E();
        book = new int[n];
        color = new int[n];
        in.close();
    }

    private void dfs(int curVex) {
        //如果当前节点还没有被染色，将它染成黑色
        if (color[curVex] == 0)
            color[curVex] = 1;
        //找出相邻的顶点v
        for (Edge edge : graph.adj(curVex)) {
            //如果顶点v没有被染色，则染成与curvex不同的颜色
            if (color[edge.v] == 0) {
                color[edge.v] = color[curVex] * -1;
            } else if (color[edge.v] == color[curVex]) {//如果已经顶点v已经有颜色了，就可以判断其颜色了
                flag = false;
                return;
            }
            //继续往下搜索遍历
            if (book[edge.v] == 0) {
                book[edge.v] = 1;
                dfs(edge.v);
            }
        }
    }

    public void judge() {
        //从0号顶点开始搜索
        book[0] = 1;
        dfs(0);
        if (!flag)
            System.out.println("该图不是二分图");
        else System.out.println("该图是二分图");
    }

    public static void main(String[] args) {
        JudgeBipartiteGraph jbg = new JudgeBipartiteGraph();
        jbg.judge();
    }
}
