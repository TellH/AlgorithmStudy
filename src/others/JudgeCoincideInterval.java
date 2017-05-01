package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tlh on 2017/5/1.
 * 区间重合判断
 * 给定一个源区间[x,y]和N个无序的目标区间。判断源区间[x,y]是不是在目标区间内？
 */
public class JudgeCoincideInterval {
    private static class Interval {
        int low;
        int high;

        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public boolean judge(Interval src, Interval[] target) {
        // 对目标区间排序
        Arrays.sort(target, (o1, o2) -> o1.low - o2.low);
        // 合并区间
        List<Interval> list = new ArrayList<>();
        Interval temp = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i].low <= temp.high) {
                temp.high = Math.max(temp.high, target[i].high);
            } else {
                list.add(temp);
                temp = target[i];
            }
        }
        list.add(temp);
        target = new Interval[list.size()];
        for (int i = 0; i < list.size(); i++) {
            target[i] = list.get(i);
        }
        int i1 = getIndex(src.low, target);
        int i2 = getIndex(src.high, target);
        if (i1 == -1 || i2 == -1) return false;
        return i1 == i2;
    }

    private int getIndex(int val, Interval[] target) {
        int i = 0, j = target.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (val >= target[mid].low && val <= target[mid].high) {
                return mid;
            } else if (val < target[mid].low) {
                j = mid - 1;
            } else if (val > target[mid].high) {
                i = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        boolean reuslt = new JudgeCoincideInterval().judge(new Interval(1, 6),
                new Interval[]{new Interval(2, 3), new Interval(1, 2), new Interval(3, 9), new Interval(13, 20)});
        System.out.println(reuslt);
    }
}
