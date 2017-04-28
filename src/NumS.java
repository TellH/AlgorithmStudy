import java.util.Scanner;

/**
 * Created by tlh on 2017/3/30.
 */
public class NumS {
    private static int n;
    private static int[] A;
    private static int[] B;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int q = in.nextInt();
        A = new int[n];
        B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            B[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            find(in.nextInt(), in.nextInt());
        }
    }

    private static void find(int x, int y) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] >= x && B[i] >= y)
                count++;
        }
        System.out.println(count);
    }
}
