package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tlh on 2017/5/27.
 * 难度：hard
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 穷举！点集内的点两两组合，统计两个点之间的斜率
 * http://www.jiuzhang.com/solutions/max-points-on-a-line/
 */
public class MaxPointsOnALine {
    private class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
        int max = 1;
        Map<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            // 可能所有的点对都是同一个点。斜率可以当作为0
            map.put(0.0, 1);
            int dup = 0;// 相同点的数量
            for (int j = i + 1; j < points.length; j++) {
                // 相同的点
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    dup++;
                    continue;
                }
                // 计算斜率
                double key = points[i].x - points[j].x == 0 ? Integer.MAX_VALUE :
                        // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0 problem
                        (double) (points[i].y - points[j].y) / (double) (points[i].x - points[j].x) + 0.0;
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 2);
                }
            }
            // 统计所得到的斜率（直线）里，所含有点数的最大值
            for (Integer times : map.values()) {
                max = Math.max(max, times + dup);
            }
        }
        return max;
    }
}
