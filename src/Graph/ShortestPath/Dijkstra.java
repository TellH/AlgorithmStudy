package Graph.ShortestPath;

import Graph.MinimumSpanningTree.Prim;
import Graph.Tree.MinHeap;

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
public abstract class Dijkstra {
    private static final int INF = 99999999;
    int n;//顶点数
    int m;//边数
    int[][] e;//有各点间距离的邻接矩阵

    static class Dis implements Comparable<Dis> {
        int vexId;
        int len;

        public Dis(int vexId, int len) {
            this.vexId = vexId;
            this.len = len;
        }

        @Override
        public int compareTo(Dis o) {
            return this.len - o.len;
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
        book = new int[n];//标记顶点属于哪个集合Q or P 如果用堆优化就不需要该数组了，在堆中顶点都是非最短路径
        disPath = new int[n];//用于回溯最短路径

        //顶点对应在堆中数组的下标，便于修改堆中的值
        int[] posInHeap = new int[n];
        //用于堆排序的点
        MinHeap<Dis> heap = new MinHeap<Dis>(Dis.class) {
            @Override
            protected void swap(int i, int j, Dis[] h) {
                super.swap(i, j, h);
                //同步更新
                int t = posInHeap[h[i].vexId];
                posInHeap[h[i].vexId] = posInHeap[h[j].vexId];
                posInHeap[h[j].vexId] = t;
            }
        };
        //初始化dis数组
        for (int i = 0; i < n; i++) {
            dis[i] = e[srcVertex][i];
            disPath[i] = srcVertex;
            posInHeap[i] = heap.size() + 1;
            heap.push(new Dis(i, dis[i]));
        }
        book[srcVertex] = 1;
        heap.pop();//弹出源点
        //松弛n-1次即可
        for (int i = 0; i < n - 1; i++) {
            //在Q集合的所有顶点中选择一个离源点s最近的顶点u（dis[u]最小）
            int v = heap.pop().vexId;//弹出已形成最短路径的顶点
            //加入到集合P
            book[v] = 1;
            //并以该点u为中转点，对每一条边进行松弛操作。
            for (int j = 0; j < n; j++) {
                if (e[v][j] < INF && book[j] == 0 && dis[j] > dis[v] + e[v][j]) {
                    dis[j] = dis[v] + e[v][j];
                    disPath[j] = v;
                    heap.update(posInHeap[j], new Dis(j, dis[j]));//更新在堆中对应顶点的dis值
                }
            }
        }
        onFinish(dis);
    }

    abstract void onFinish(int[] dis);

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra() {
            @Override
            void onFinish(int[] dis) {
                for (int i = 0; i < n; i++) {
                    System.out.print(dis[i] + " ");
                }
            }
        };
        d.run(0);
    }
}
