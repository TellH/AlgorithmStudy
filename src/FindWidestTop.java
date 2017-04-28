import java.util.Scanner;

/**
 * Created by tlh on 2017/3/30.
 */
public class FindWidestTop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int maxLen = 0;
        int i = 0, j = 1;
        int maxI = -1, maxJ = -1;
//        boolean start = false;
        boolean isUp = true;
        for (; j < a.length; ) {
            if (a[j - 1] < a[j]) {
                if (isUp) {
                    j++;
                } else {
                    isUp = true;
                    if (maxLen < j - i) {
                        maxLen = j - i;
                        maxI = i;
                        maxJ = j - 1;
                    }
                    i = j - 1;
                }
//                start = true;
            } else {
//                if (!start) continue;
                if (isUp) {
                    isUp = false;
                }
                j++;
            }
        }
        System.out.println(maxI + " " + maxJ);
    }
}
