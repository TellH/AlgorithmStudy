package SwordToOffer;

import java.util.HashMap;

/**
 * Created by tlh on 2017/3/17.
 */
public class MoreThanHalfNum {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0)
            throw new RuntimeException("Invalid Input.");
        int result = array[0];
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>(array.length);
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            Integer cnt = map.get(num);
            map.put(num, cnt != null ? cnt + 1 : 1);
            if (count == 0) {
                result = num;
                count++;
            } else if (result == num) {
                count++;
            } else {
                count--;
            }
        }
        if (map.get(result) > array.length / 2)
            return result;
        else return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MoreThanHalfNum().MoreThanHalfNum_Solution(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
}
