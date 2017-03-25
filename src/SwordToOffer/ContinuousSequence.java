package SwordToOffer;

import java.util.ArrayList;

/**
 * Created by tlh on 2017/3/21.
 */
public class ContinuousSequence {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int small = 1;
        int big = 2;
        int now = small + big;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        while (small < big) {
            if (now < sum) {
                big++;
                now += big;
            } else if (now > sum) {
                now -= small;
                small++;
            } else {
                for (int i = small; i <= big; i++) {
                    temp.add(i);
                }
                result.add(temp);
                temp = new ArrayList<>();
                now -= small;
                small++;
            }
        }
        return result;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || array.length == 0)
            return result;
        int small = 0;
        int big = array.length - 1;
        int now = array[small] + array[big];
        int multi = Integer.MAX_VALUE;
        while (small < big && small < array.length && big > 0) {
            if (now < sum) {
                small++;
            } else if (now > sum) {
                big--;
            } else {
                int temp = array[small] * array[big];
                if (temp < multi) {
                    multi = temp;
                    result.clear();
                    result.add(array[small]);
                    result.add(array[big]);
                }
                big--;
            }
            now = array[small] + array[big];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ContinuousSequence().FindNumbersWithSum(new int[]{1, 2, 3, 13, 15}, 15).toString());
    }
}
