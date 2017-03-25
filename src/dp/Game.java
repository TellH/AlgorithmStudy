package dp;

/**
 * Created by tlh on 2017/3/22.
 * 纸牌博弈
 * 有一个整型数组A，代表数值不同的纸牌排成一条线。玩家a和玩家b依次拿走每张纸牌，
 * 规定玩家a先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家a和玩家b都绝顶聪明，他们总会采用最优策略。
 * 请返回最后获胜者的分数。
 * f[j][j]表示我作为先选的人，我在i，j这段选择时，我选择nums[i]还是nums[j]取决于在选完之后，我作为后选的人，
 * 在[i+1,j]和[i,j-1]这两段中能选得的最大值。
 * s[i][j]表示我作为后选的人，因为之前的人很聪明，我肯定选的是先前那个人选择的剩下的情况，即两段中最小的那种情况。
 */
public class Game {
    public int game(int[] src) {
        int len = src.length;
        int[][] f = new int[len][len];
        int[][] s = new int[len][len];
        for (int j = 0; j < len; j++) {
            f[j][j] = src[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(s[i][j - 1] + src[j], s[i + 1][j] + src[i]);
                s[i][j] = Math.min(f[i][j - 1], f[i + 1][j]);
            }
        }
        return Math.max(f[0][len - 1], s[0][len - 1]);
    }

    public static void main(String[] args) {
        System.out.println(new Game().game(new int[]{1, 2, 100, 4}));
    }
}
