import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tlh on 2017/3/21.
 * <p>
 */
public class Test {
    private static Integer n;
    private static int[] ps;
    private static int[] index;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = Integer.valueOf(in.nextLine());
        ps = new int[n];
        index = new int[n];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = in.nextInt();
            index[i] = i;
        }
        int corr = (int) Math.ceil(0.6f * (double) n);
        double result = 0;
        for (int i = corr; i <= n; i++) {
            result += pick(i);
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00000");
        System.out.println(df.format(result));
    }

    private static double pick(int i) {
        List<List<Integer>> lists = new Combination(i, n, index).get();
        double result = 0;
        for (List<Integer> list : lists) {
            double temp = 1;
            for (int j = 0; j < ps.length; j++) {
                if (list.contains(j)) {
                    temp *= ps[j] * 0.01;
                } else {
                    temp *= (100 - ps[j]) * 0.01;
                }
            }
            result += temp;
        }
        return result;
    }

//    public static double C(int m, int n) {
//        return factorLoop(n) / (factorLoop(m) * factorLoop(n - m));
//    }

    public static double factorLoop(int n) {
        if (n == 0) {
            return 1;
        }
        double result = 1;
        for (int i = n; i > 0; i--) {
            result *= i;
        }
        return result;
    }

    static class Combination {
        private int n;
        private int m;
        private int[] src;
        private int[] zerones;

        public Combination(int m, int n, int[] src) {
            this.m = m;
            this.n = n;
            this.src = src;
            zerones = new int[n];
            for (int i = 0; i < m; i++) {
                zerones[i] = 1;
            }
        }

        public List<List<Integer>> get() {
            List<List<Integer>> result = new ArrayList<>();
            while (true) {
                result.add(getCombination());
                if (!findOneZero())
                    break;
            }
            return result;
        }

        private List<Integer> getCombination() {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (zerones[i] == 1)
                    result.add(src[i]);
            }
            return result;
        }

        private boolean findOneZero() {
            int i;
            for (i = 0; i < n - 1; i++) {
                if (zerones[i] == 1 && zerones[i + 1] == 0) {
                    zerones[i] = 0;
                    zerones[i + 1] = 1;
                    int ones = 0;
                    for (int j = 0; j < i; j++) {
                        if (zerones[j] == 1) ones++;
                    }
                    for (int j = 0; j < i; j++) {
                        if (j < ones)
                            zerones[j] = 1;
                        else zerones[j] = 0;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
