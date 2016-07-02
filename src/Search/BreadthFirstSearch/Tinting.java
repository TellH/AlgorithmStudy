package Search.BreadthFirstSearch;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tlh on 2016/7/2.
 * 着色法
 * Q:用10*10的二维数组表示地图，图中0表示海洋，正数表示陆地。输入一个点坐标，求出该点所在的岛屿的面积（格子数）
 * A：从输入点开始进行广度优先搜索，每次向上下左右四个方向拓展，但拓展出的点大于0则加入队列，知道队列拓展完毕。所有被加入到队列的点
 * 的总数就是岛屿的面积，该算法称为着色法。
 */
public class Tinting {
    private static final int SIZE_X = 10;
    private static final int SIZE_Y = 10;

    private static class Note {
        int x;
        int y;

        Note(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[][] g;
    private int[][] next = {{0, 1},//向右走
            {1, 0},//向下走
            {0, -1},//向左走
            {-1, 0}};//向上走
    private int[][] book;
    private Queue<Note> queue;
    private int size;
    private Note startPoint;

    public Tinting(int[][] g, Note sp) {
        this.g = g;
        book = new int[SIZE_X][SIZE_Y];
        queue = new ArrayBlockingQueue<>(SIZE_X * SIZE_Y);
        size = 0;
        startPoint = sp;
    }

    public void bfs() {
        queue.add(startPoint);
        book[startPoint.x][startPoint.y] = 1;
        size++;
        while (!queue.isEmpty()) {
            //从队列中取出一个点
            Note now = queue.poll();
            //以该点向是个方向进行拓展
            for (int i = 0; i < 4; i++) {
                Note nextPoint = new Note(now.x + next[i][0], now.y + next[i][1]);
                //判断是否越界
                if (nextPoint.x >= SIZE_X || nextPoint.y >= SIZE_Y || nextPoint.x < 0 || nextPoint.y < 0)
                    continue;
                //判断是否是陆地或者是否曾经走过的
                if (g[nextPoint.x][nextPoint.y] > 0 && book[nextPoint.x][nextPoint.y] != 1) {
                    queue.add(nextPoint);
                    size++;
                    book[nextPoint.x][nextPoint.y] = 1;
                }
            }
        }
        System.out.println(size);
    }

    public static void main(String[] args) {
        int[][] g = new int[SIZE_X][SIZE_Y];
        Scanner in = new Scanner(System.in);
        Note sp = new Note(in.nextInt(), in.nextInt());
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                g[i][j] = in.nextInt();
            }
        }
        Tinting t = new Tinting(g, sp);
        t.bfs();
    }
}
