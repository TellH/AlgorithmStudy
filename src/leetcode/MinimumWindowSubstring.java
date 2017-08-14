package leetcode;

import java.util.HashMap;

/**
 * Created by tlh on 2017/6/8.
 * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。
 * 样例
 * 给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 * 解题报告：
 * 两个指针，维护一个动态区间。右指针往后扫，记录匹配的次数，当匹配数等于目标字符串target的长度时，更新最小窗口，回缩左指针，直到不能回缩为止。
 */
public class MinimumWindowSubstring {
    public String minWindow(String source, String target) {
        if (source == null || target == null || target.length() == 0) return "";
        // map中的key是target的字符，value是各个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        // 初始化map
        for (int i = 0; i < target.length(); i++) {
            char key = target.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        int count = 0; // 窗口内出现target中的字符的次数
        int left = 0; // left指针从左开始
        int resLeft = 0, resRight = Integer.MAX_VALUE;
        // right指针从右开始，向右扫描
        for (int right = 0; right < source.length(); right++) {
            char ch = source.charAt(right);
            if (!map.containsKey(ch)) continue; // 跳过不是target内出现的字符
            if (map.get(ch) > 0) count++; // 对应字符的计数大于零
            map.put(ch, map.get(ch) - 1); // 对应字符的计数减一
            while (count == target.length()) { // 当前窗口满足条件
                // 更新最小窗口
                if (resRight - resLeft > right - left) {
                    resRight = right;
                    resLeft = left;
                }
                // 向右移动left指针
                char leftCh = source.charAt(left);
                if (map.containsKey(leftCh)) {
                    map.put(leftCh, map.get(leftCh) + 1);
                    if (map.get(leftCh) > 0) count--;
                }
                left++;
            }
        }
        // 没找到复合条件的字串
        if (resRight == Integer.MAX_VALUE) return "";
        return source.substring(resLeft, resRight + 1);
    }
}
