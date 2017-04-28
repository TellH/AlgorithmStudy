/**
 * Created by tlh on 2017/4/6.
 */
public class GamePlanning {
    int[][] getGameTable(int k) {
        int n = (int) Math.pow(2, k);
        int[][] table = new int[n + 1][n + 1];
        n = 2;
        table[1][1] = 1;
        table[1][2] = 2;
        table[2][1] = 2;
        table[2][2] = 1;
        for (int t = 1; t < k; t++) {
            //用分治法来填充日程表
            int temp = n;
            n *= 2;
            //扩充左下角
            for (int i = temp + 1; i <= n; i++) {
                for (int j = 1; j <= temp; j++) {
                    table[i][j] = table[i - temp][j] + temp;
                }
            }
            //扩充右上角
            for (int i = 1; i <= temp; i++) {
                for (int j = temp + 1; j <= n; j++) {
                    table[i][j] = table[i][j - temp] + temp;
                }
            }
            //扩展右下角
            for (int i = temp + 1; i <= n; i++) {
                for (int j = temp + 1; j <= n; j++) {
                    table[i][j] = table[i - temp][j - temp];
                }
            }
        }
        return table;
    }

    public static void main(String[] args) {
        int[][] table = new GamePlanning().getGameTable(3);
        for (int i = 1; i < table.length; i++) {
            for (int j = 2; j < table.length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}
