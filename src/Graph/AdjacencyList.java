package Graph;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/3.
 * 邻接表
 * 1.用链表实现
 * 2.用数组实现
 */
public class AdjacencyList {
    static class Edge {
        int adjvex;//邻接顶点编号
        int weight;//边权值
        Edge next;//下一条边
        public Edge(int adjvex, int weight) {
            this.adjvex = adjvex;
            this.weight = weight;
        }
    }
    int n;//顶点数
    Edge[] vertexEdgeList;//邻接表，用数组下标表示顶点编号

    public AdjacencyList() {
        Scanner in = new Scanner(System.in);
        this.n = in.nextInt();
        this.vertexEdgeList = new Edge[n];
        Edge[] tailNode = new Edge[n];//指向每一个顶点邻接链表表尾节点的指针数组
        //读入边
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();//边的权值
            if (vertexEdgeList[u] == null && tailNode[u] == null) {
                tailNode[u] = vertexEdgeList[u] = new Edge(v, w);
            } else {
                tailNode[u].next = new Edge(v, w);
                tailNode[u] = tailNode[u].next;
            }
        }
        in.close();
    }


}
