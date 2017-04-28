package SwordToOffer;

/**
 * Created by tlh on 2017/3/26.
 */
public class LastRemaining {
    public int LastRemaining_Solution(int n, int m) {
        int count = 0;
        boolean[] childs = new boolean[n];
        int from = 0;
        while (count < n - 1) {
            int cnt = 0;
            for (int i = from; ; i = (i + 1) % n) {
                if (childs[i]) continue;
                cnt++;
                if (cnt == m) {
                    from = i;
                    childs[i] = true;
                    count++;
                    break;
                }
            }
        }
        for (int i = 0; i < childs.length; i++) {
            if (!childs[i]) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new LastRemaining().LastRemaining_Solution(10, 2));
    }
}
