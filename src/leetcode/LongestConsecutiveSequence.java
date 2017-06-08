package leetcode;

import java.util.HashSet;

/**
 * Created by tlh on 2017/6/6.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4]. Return its length:4.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        // 将所有数放入HashSet中
        for (int num : nums) {
            set.add(num);
        }
        // 再一次遍历原数组
        for (int num : nums) {
            int pre = num - 1;
            int next = num + 1;
            int count = 1;
            if (!set.contains(num)) continue;
            // 分别向前和向后搜索
            while (set.contains(pre)) {
                set.remove(pre);
                pre--;
                count++;
            }
            while (set.contains(next)) {
                set.remove(next);
                next++;
                count++;
            }
            result = result > count ? result : count;
        }
        return result;
    }
}
