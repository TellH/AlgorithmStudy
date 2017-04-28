package others;

/**
 * Created by tlh on 2017/4/28.
 */
public class GreatestCommonDivisor {
    // 大数求模是算法的瓶颈
    public int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    // 迭代次数过多
    public int gcd1(int x, int y) {
        if (x < y) return gcd1(y, x);
        return y == 0 ? x : gcd1(y, x - y);
    }

    public int gcd2(int x, int y) {
        if (x < y) return gcd2(y, x);
        if (y == 0) return x;
        if ((x & 1) == 0 && (y & 1) == 0) return 2 * gcd2(x >> 1, y >> 1);
        else if ((x & 1) != 0 && (y & 1) == 0) return gcd2(x, y >> 1);
        else if ((x & 1) != 0 && (y & 1) == 0) return gcd2(x >> 1, y);
        else return gcd2(y, x - y);
    }

    public static void main(String[] args) {
        System.out.println(new GreatestCommonDivisor().gcd2(42, 30));
    }
}
