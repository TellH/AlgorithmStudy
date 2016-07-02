package Graph.ShortestPath;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/2.
 * 单源最短路径
 * 每次找到离源点最近的顶点，然后以该顶点为中心进行扩展（松弛），最终得到源点到其余所有顶点的最短路径。
 * 步骤：
 * 1.将所有的顶点分为两部分：已知最短路径的顶点集合p和未知最短路径的顶点集合Q。通过一个book数组来记录哪些点在集合p中。
 * 2.初始化dis数组：设置源点s到自己的最短路径为0，若存在有源点能直接到达的顶点i，dis[i]=e[s][i];否则dis[i]=INF
 * 3.在Q集合的所有顶点中选择一个离源点s最近的顶点u（dis[u]最小），加入到集合P，并考察左右以点u为起点的边，
 * 对每一条边进行松弛操作。
 * 4.重复第3步，如果集合Q为空，算法结束。最终dis数组中的值就是源点到所有顶点的最短路径
 */
public class Dijkstra {
    private static final int INF = 99999999;
    int n;//顶点数
    int m;//边数
    int[][] e;//有各点间距离的邻接矩阵
    static class Dis {
        int vex;
        int len;

        public Dis(int vex, int len) {
            this.vex = vex;
            this.len = len;
        }
    }
    public Dijkstra() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        e = new int[n][n];
        //初始化邻接矩阵
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == j)
                    e[i][j] = 0;
                else e[i][j] = INF;
            }
        //读入图(有向图)的边，存成邻接矩阵
        for (int k = 0; k < m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            int d = in.nextInt();
            e[i][j] = d;
//            e[j][i] = d;
        }
        in.close();
    }

    private int[] dis;
    private int[] book;
    private int[] disPath;
    private Queue<Dis> queue;

    public void run(int srcVertex) {
        dis = new int[n];//最短距离的松弛数组
        book = new int[n];//标记顶点属于哪个集合Q or P
        disPath = new int[n];//用于回溯最短路径
        queue = new PriorityQueue<>(new Comparator<Dis>() {
            @Override
            public int compare(Dis o1, Dis o2) {
                return o1.len - o2.len;
            }
        });//最小堆
        //初始化dis数组
        for (int i = 0; i < n; i++) {
            dis[i] = e[srcVertex][i];
            disPath[i] = srcVertex;
        }
        book[srcVertex] = 1;
        //松弛n-1次即可
        for (int i = 0; i < n - 2; i++) {
            //在Q集合的所有顶点中选择一个离源点s最近的顶点u（dis[u]最小）
            int v = findNearestVertex();
            //加入到集合P
            book[v] = 1;
            //并以该点u为中转点，对每一条边进行松弛操作。
            for (int j = 0; j < n; j++) {
                if (e[v][j] < INF && dis[j] > dis[v] + e[v][j]) {
                    dis[j] = dis[v] + e[v][j];
                    disPath[j] = v;
                }
            }
        }

    }

    private int findNearestVertex() {
        queue.clear();
        for (int i = 0; i < n; i++) {
            if (book[i] == 0) {
                queue.add(new Dis(i, dis[i]));
            }
        }
        return queue.peek().vex;
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        d.run(0);
        for (int i = 0; i < d.n; i++) {
            System.out.print(d.dis[i]+" ");
        }
    }
}
