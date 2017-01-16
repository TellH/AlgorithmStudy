package SwordToOffer;

/**
 * Created by tlh on 2017/1/16.
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印s的所有可能的概率。
 * 用两个数组来保存骰子点数的每一个可能的总/数出现的次数。另一个数组的第n个数值等于前一个数组对应的第n-1，n-2，n-3，，，n-6之和。
 */
public class T43_DicesProbability {
    public static void printProbability(int n) {
        if (n < 1)
            return;
        int maxVal = 6;
        int[][] p = new int[2][maxVal * n + 1];
        int flag = 0;
        //一个骰子的情况
        for (int i = 1; i <= maxVal; i++)
            p[flag][i] = 1;
        //有i个骰子的情况，每次增加一个骰子
        for (int i = 2; i <= n; i++) {
            //将不可能出现的情况清零
            for (int k = 0; k < i; k++)
                p[1 - flag][k] = 0;
            //和的取值有可能是i~i*maxVal
            for (int j = i; j <= i * maxVal; j++) {
                p[1 - flag][j] = 0;
                //将前一个数组对应的第j-1，j-2，，，j-maxVal(如果有)个数值累加
                for (int k = 1; k <= j && k <= maxVal; k++)
                    p[1 - flag][j] += p[flag][j - k];
            }
            flag = 1 - flag;//切换数组
        }
        double total = Math.pow((double) maxVal, n);
        int sum = 0;
        for (int i = n; i <= maxVal * n; i++) {
            sum += p[flag][i];
            System.out.println(i + ": " + (double) p[flag][i] / total);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        printProbability(2);
    }
}
