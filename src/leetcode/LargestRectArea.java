package leetcode;
/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * For example,
 * Given height =[2,1,5,6,2,3],
 * return10.
 *
 * 使用单调栈，维护一个递增的栈，遇到小于栈顶的元素时，合并并计算高度。
 * Debug一下，你就知道
 */

import java.util.Stack;

public class LargestRectArea {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curHeight = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curHeight <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        new LargestRectArea().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
    }
}
