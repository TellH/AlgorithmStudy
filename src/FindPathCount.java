/**
 * Created by tlh on 2017/3/20.
 */
public class FindPathCount {
    int rows;
    int cols;
    int count;
    int[][] map;
    int[] start;

    public int countPath(int[][] map, int n, int m) {
        rows = n;
        cols = m;
        this.map = map;
        this.start = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                    break;
                }
            }
        }
        int[] dirX = new int[]{1, -1};
        int[] dirY = new int[]{1, -1};
        for (int i = 0; i < dirX.length; i++) {
            for (int j = 0; j < dirY.length; j++) {
                dfs(start, dirX[i], dirY[j]);
            }
        }
        return count;
    }

    private void dfs(int[] startPt, int dirX, int dirY) {
        int row = startPt[0];
        int col = startPt[1];
        if (row < 0 || row >= rows || col < 0 || col >= cols)
            return;
        if (map[row][col] == -1)
            return;
        if (map[row][col] == 2) {
            if (row == start[0] || col == start[1]) {
                count = 1;
                return;
            }
            count++;
            return;
        }
        dfs(new int[]{row + dirY, col}, dirX, dirY);
        dfs(new int[]{row, col + dirX}, dirX, dirY);
    }

    public static void main(String[] args) {
//        System.out.println(new FindPathCount().countPath(new int[][]{{0, 1, 0}, {2, 0, 0}}, 2, 3));
        System.out.println(new FindPathCount().countPath(new int[][]{{0}, {1}, {2}}, 3, 1));
    }
}
