package Search.BreadthFirstSearch;

import java.util.Scanner;

/**
 * Created by tlh on 2017/5/7.
 * 给定一个地图，1表示陆地，0表示水。求地图中海岛的数量
 * 4 5
 * 11000
 * 11000
 * 00100
 * 00011
 */
public class Count {
    private static int n;
    private static int m;
    private static int[][] g;
    private static boolean[][] book;
    private static int[][] next = {{0, 1},//向右走
            {1, 0},//向下走
            {0, -1},//向左走
            {-1, 0}};//向上走


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        g = new int[n][m];
        book = new boolean[n][m];
        int num = 0;
        for (int i = 0; i < n; i++) {
            String next = in.next();
            for (int j = 0; j < m; j++) {
                g[i][j] = next.charAt(j) - '0';
                book[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] != 0 && !book[i][j]) {
                    num++;
                    book[i][j] = true;
                    dfs(i, j);
                }
            }
        }
        System.out.println(num);
    }

    private static void dfs(int i, int j) {
        for (int d = 0; d < next.length; d++) {
            int nextI = i + next[d][0];
            int nextJ = j + next[d][1];
            if (checkI(nextI) && checkJ(nextJ) && g[nextI][nextJ] == 1 && !book[nextI][nextJ]) {
                book[nextI][nextJ] = true;
                dfs(nextI, nextJ);
            }
        }
    }

    private static boolean checkI(int i) {
        return i >= 0 && i < n;
    }

    private static boolean checkJ(int j) {
        return j >= 0 && j < m;
    }
}
