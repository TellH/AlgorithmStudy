package Graph;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tlh on 2016/7/2.
 * 图的遍历
 */
public abstract class Traversal {
    private static final int INF = 999999;
    private int n;
    private int[][] g;
    private int[] book;
    private int visitNum;

    private Traversal(int n, int[][] g) {
        this.n = n;
        this.g = g;
        book = new int[n];
        visitNum = 0;
    }

    public void dfs() {
        book[0] = 1;
        dfs(0);
    }

    public void dfs(int vertexNum) {
//        book[vertexNum] = 1;
        //先访问节点
        visit(vertexNum);
        visitNum++;
        //判断是否遍历完成
        if (visitNum == n) return;
        //寻找该点的邻接顶点
        for (int i = 0; i < n; i++) {
            if (i != vertexNum && g[vertexNum][i] != INF && book[i] == 0) {
                //标记该点为已访问
                book[i] = 1;
                //访问该顶点
                dfs(i);
                //所有顶点只需访问一次，无需在dfs之后取消对该点的标记，book[i]=0;
            }
        }
    }

    public void bfs() {
        bfs(0);
    }

    public void bfs(int startPoint) {
        Queue<Integer> queue = new ArrayBlockingQueue(n);
        //访问起点并把起点入队
        queue.add(startPoint);
        visit(startPoint);
        book[startPoint] = 1;
        while (!queue.isEmpty()) {
            int nowPoint = queue.poll();
            for (int i = 0; i < n; i++) {
                if (i != nowPoint && g[nowPoint][i] != INF && book[i] == 0) {
                    visit(i);
                    book[i] = 1;
                    queue.add(i);
                }
            }
        }
    }

    abstract void visit(int vertexNum);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//顶点数
        int m = in.nextInt();//边数
        int[][] g = new int[n][n];//图的邻接矩阵
        //初始化邻接矩阵
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == j)
                    g[i][j] = 0;
                else g[i][j] = INF;
            }
        //读入图的边，存成邻接矩阵
        for (int k = 0; k < m; k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            g[i][j] = 1;
            g[j][i] = 1;
        }
        Traversal t = new Traversal(n, g) {
            @Override
            void visit(int vertexNum) {
                System.out.print(vertexNum + "　");
            }
        };
//        t.bfs();
        t.dfs();
    }
}
