package SwordToOffer;

/**
 * Created by tlh on 2017/3/11.
 */
public class Fibonacci {
    public int Fibonacci(int n) {
        int last1 = 0;
        int last2 = 1;
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int index = 2;
        while (true) {
            int temp = last2;
            last2 = last1 + last2;
            last1 = temp;
            if (index == n)
                return last2;
            index++;
        }
    }
}
