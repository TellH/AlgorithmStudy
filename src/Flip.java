/**
 * Created by tlh on 2017/3/20.
 */
public class Flip {
    int rows = 4;
    int cols = 4;

    public int[][] flipChess(int[][] A, int[][] f) {
        // write code here
        for (int i = 0; i < 3; i++) {
            int[] pt = f[i];
            pt[0]--;
            pt[1]--;
            flip(A, pt);
        }
        return A;
    }

    public void flip(int[][] A, int[] pt) {
        System.out.println(pt[0] + pt[1]);
        int i = pt[0];
        int j = pt[1];
        flip(A, i - 1, j - 1);
        flip(A, i - 1, j);
        flip(A, i - 1, j + 1);
        flip(A, i, j - 1);
        flip(A, i, j + 1);
        flip(A, i + 1, j - 1);
        flip(A, i + 1, j);
        flip(A, i + 1, j + 1);
    }

    public void flip(int[][] A, int i, int j) {
        if (i < 0 || i > cols || j < 0 || j > rows) return;
        A[i][j] = A[i][j] == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        new Flip().flipChess(new int[][]{{0, 1, 0, 0}, {1, 0, 1, 0}, {1, 1, 0, 0}, {1, 0, 0, 1}},
                new int[][]{{2, 3}, {4, 2}, {2, 3}});
    }
}
