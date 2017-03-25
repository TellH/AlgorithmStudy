package SwordToOffer;

/**
 * Created by tlh on 2017/3/25.
 */
public class IsContinuous {
    public boolean isContinuous(int[] numbers) {
        int min = 15, max = -1;
        boolean[] book = new boolean[14];
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (num < min)
                min = num;
            if (num > max)
                max = num;
            if (max - min > 5)
                return false;
            if (num != 0 && book[num])
                return false;
            else if (num != 0 && !book[num])
                book[num] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsContinuous().isContinuous(new int[]{1, 3, 2, 5, 4}));
    }
}
