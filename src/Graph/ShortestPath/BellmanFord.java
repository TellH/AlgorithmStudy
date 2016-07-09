package Graph.ShortestPath;

import Graph.base.Edge;
import Graph.base.Graph;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tlh on 2016/7/3.
 * 解决带有负权边的图的最短路径
 * 对所有边进行n-1次松弛操作，第i轮表示允许经过最多i条边松弛，第n-1轮表示允许经过最多所有n-1条边松弛
 * for(int k=1;k<=n-1;k++){
 * for(int i=0;i<m;i++){
 * if(dis[v[i]]>dis[u[i]]+w[i])
 * dis[v[i]]=dis[u[i]]+w[i];
 * }
 * }
 * 证明是否负权回路：如果在进行n-1轮松弛后，仍然可以继续松弛，那么此图必然存在负权回路。
 */
public abstract class BellmanFord {
    private static final int INF = 99999999;
    private final int n;
    private final int m;
    private final Edge[] edges;
    private int[] dis;
    private Graph graph;
    public BellmanFord() {
        Scanner in = new Scanner(System.in);
        graph=new Graph(in);
        n = graph.V();
        m = graph.E();
        dis = new int[n];
        edges = new Edge[m];
        //初始化dis数组
        for (int i = 0; i < n; i++)
            dis[i] = INF;
        //读入边
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        in.close();
    }

    public void run(int srcVex) {
        dis[srcVex] = 0;
        int[] bak = new int[n];
        for (int k = 0; k < n - 1; k++) {
            for (int i = 0; i < n; i++) bak[i] = dis[i];//备份dis数组
            for (int i = 0; i < m; i++) {
                if (dis[edges[i].v] > dis[edges[i].u] + edges[i].w)
                    dis[edges[i].v] = dis[edges[i].u] + edges[i].w;
            }
            //Bellman-Ford算法常常会在未到达n-轮松弛前已经计算出最短路径。
            boolean isUpdate = false;
            for (int i = 0; i < n; i++)
                if (bak[i] != dis[k]) {
                    isUpdate = true;
                    break;
                }
            if (!isUpdate) break;//如果dis数组没有更新，提前结束循环
        }
        onFinish(dis);
    }

    //每次仅对最短路径发生变化了的点的相邻边进行松弛操作，总有在那些在前一遍松弛中改变了最短路程估计值的顶点才可能引起它们邻接
    //点最短路程估计值发生变化。因此用一个队列来存放被成功松弛的顶点，之后只对队列中的点进行处理。初始时将源点加入队列；每次
    //从队首取出一个顶点，并对与其相邻的所有顶点进行松弛尝试，若某个相邻的顶点松弛成功，且这个相邻的顶点不在队列中，
    //则将它加入到队列中。
    public void optimizeWithQueue(int srcVex) {
        if (srcVex >= n) return;
        dis[srcVex] = 0;
        //用于记录该点是否已被添加到队列中
        int[] book = new int[n];
        Queue<Integer> queue = new ArrayBlockingQueue<>(n);
        queue.add(srcVex);
        book[srcVex] = 1;//将顶点入队一定要把该顶点的入队标志置1
        while (!queue.isEmpty()) {
            int startVex = queue.poll();
            book[startVex] = 0;//出队一定要把入队标志置零，因为同一个顶点可能会多次入队
            //尝试松弛每一条邻接边
            for (Edge edge : graph.adj(startVex)) {
                //如果松弛成功
                if (dis[edge.v] > dis[edge.u] + edge.w) {
                    dis[edge.v] = dis[edge.u] + edge.w;
                    //如果该点没有在队列中就添加到队列中
                    if (book[edge.v] == 0) {
                        queue.add(edge.v);
                        book[edge.v] = 1;//将顶点入队一定要把该顶点的入队标志置1
                    }
                }
            }
        }
        onFinish(dis);
    }

    abstract void onFinish(int[] dis);
}
