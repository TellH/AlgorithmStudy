package Search.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by tlh on 2017/3/23.
 * 大家一定玩过“推箱子”这个经典的游戏。具体规则就是在一个N*M的地图上，有1个玩家、1个箱子、1个目的地以及若干障碍，其余是空地。
 * 玩家可以往上下左右4个方向移动，但是不能移动出地图或者移动到障碍里去。如果往这个方向移动推到了箱子，箱子也会按这个方向移动一格，
 * 当然，箱子也不能被推出地图或推到障碍里。当箱子被推到目的地以后，游戏目标达成。
 * 现在告诉你游戏开始是初始的地图布局，请你求出玩家最少需要移动多少步才能够将游戏目标达成。
 * 每个测试输入包含1个测试用例
 * 第一行输入两个数字N，M表示地图的大小。其中0<N，M<=8。
 * 接下来有N行，每行包含M个字符表示该行地图。其中 . 表示空地、X表示玩家、*表示箱子、#表示障碍、@表示目的地。
 * 每个地图必定包含1个玩家、1个箱子、1个目的地。
 */
public class TuiXiangZi {
    private static int N;
    private static int M;
    private static int[][][][] book;

    static class State {
        int ax;
        int ay;
        int bx;
        int by;

        public State(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }
    }

    static char[][] map;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] start;
        int[] boxPt;
        N = in.nextInt();
        M = in.nextInt();
        map = new char[N][M];
        book = new int[N][M][N][M];
        start = new int[2];
        boxPt = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < N; i++) {
            String next = in.next();
            for (int j = 0; j < M; j++) {
                char ch = next.charAt(j);
                if (ch == 'X') {
                    start[0] = i;
                    start[1] = j;
                } else if (ch == '@') {
                    end[0] = i;
                    end[1] = j;
                } else if (ch == '*') {
                    boxPt[0] = i;
                    boxPt[1] = j;
                }
                map[i][j] = ch;
            }
        }
        bfs(start, boxPt);
    }

    private static void bfs(int[] start, int[] boxPt) {
        int[][] steps = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<State> q = new LinkedList<>();
        book[start[0]][start[1]][boxPt[0]][boxPt[1]] = 1;
        q.offer(new State(start[0], start[1], boxPt[0], boxPt[1]));
        while (!q.isEmpty()) {
            State state = q.poll();
            for (int i = 0; i < steps.length; i++) {
                int fromX = state.ax;
                int fromY = state.ay;
                int toX = fromX + steps[i][0];
                int toY = fromY + steps[i][1];
                int boxX = state.bx;
                int boxY = state.by;
                int pushX = boxX + steps[i][0];
                int pushY = boxY + steps[i][1];
                if (!valid(toX, toY)) continue;
                // 人推箱子动
                if (toX == boxX && toY == boxY
                        && valid(pushX, pushY) && book[toX][toY][pushX][pushY] == 0) {
                    book[toX][toY][pushX][pushY] = book[fromX][fromY][boxX][boxY] + 1;
                    if (reach(pushX, pushY)) {
                        System.out.println(book[toX][toY][pushX][pushY] - 1);
                        return;
                    }
                    q.offer(new State(toX, toY, pushX, pushY));
                } else if (book[toX][toY][boxX][boxY] == 0) { //人动了，箱子没动
                    book[toX][toY][boxX][boxY] = book[fromX][fromY][boxX][boxY] + 1;
                    q.offer(new State(toX, toY, boxX, boxY));
                }
            }
        }
        System.out.println("-1");
    }

    private static boolean reach(int boxX, int boxY) {
        return map[boxX][boxY] == '@';
    }

    private static boolean valid(int toX, int toY) {
        return toX >= 0 && toX < N && toY >= 0 && toY < M && map[toX][toY] != '#';
    }
}
