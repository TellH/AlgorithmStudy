package Graph.ShortestPath;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/2.
 * 求多源最短路径
 * 从i号顶点到j号顶点只经过前k号点的最短路径
 * 最开始只允许经过1号顶点进行中转，接下来只允许经过1号和2号顶点进行中转...允许经过1~n号所有顶点进行中转，
 * 求任意两点之间的最短路径
 */
public class FloydWarshall {
    private static final int INF = 99999999;
    int n;//顶点数
    int m;//边数
    int[][] dis;//有各点间距离的邻接矩阵

    public FloydWarshall() {
        Scanner in = new Scanner(System.in);
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.dis = new int[n][n];
        //初始化邻接矩阵
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == j)
                    dis[i][j] = 0;
                else dis[i][j] = INF;
            }
        //读入图(有向图)的边，存成邻接矩阵
        for (int k = 0; k < m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            int d = in.nextInt();
            dis[i][j] = d;
//            dis[j][i] = d;
        }
        in.close();
    }

    public void run() {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dis[i][k] < INF && dis[k][j] < INF && dis[i][j] > dis[i][k] + dis[k][j])
                        dis[i][j] = dis[i][k] + dis[k][j];
    }

    public static void main(String[] args) {
        FloydWarshall m = new FloydWarshall();
        m.run();
        for (int i = 0; i < m.n; i++) {
            for (int j = 0; j < m.n; j++) {
                System.out.print(m.dis[i][j] + " ");
            }
            System.out.println();
        }
    }
}
