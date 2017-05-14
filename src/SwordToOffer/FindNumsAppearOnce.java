package SwordToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2017/3/21.
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次
 */
public class FindNumsAppearOnce {
    public void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null)
            return;
        List<Integer> list1 = new ArrayList<>(array.length);
        List<Integer> list2 = new ArrayList<>(array.length);
        List<Integer> list3 = new ArrayList<>(array.length);
        for (int ele : array) {
            list3.add(ele);
        }
        int mask = 1;
        while (true) {
            for (Integer ele : list3) {
                int temp = ele;
                if (isBitOne(temp, mask))
                    list1.add(temp);
                else list2.add(temp);
            }
            int a = doOrElse(list1);
            int b = doOrElse(list2);
            if (a != 0 && b != 0) {
                num1[0] = a;
                num2[0] = b;
                break;
            } else {
                list3.clear();
                list3.addAll(a != 0 ? list1 : list2);
                mask <<= 1;
                list1.clear();
                list2.clear();
            }
        }
    }

    private boolean isBitOne(int t, int mask) {
        return (t & mask) == mask;
    }

    private int doOrElse(List<Integer> list) {
        int result = 0;
        for (Integer ele : list) {
            result ^= ele;
        }
        return result;
    }

    public static void main(String[] args) {
        new FindNumsAppearOnce().findNumsAppearOnce(new int[]{1, 2, 1, 2, 3, 5}, new int[]{0}, new int[]{0});
    }
}
