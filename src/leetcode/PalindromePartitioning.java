package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2017/6/5.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s ="aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0)
            return result;
        dfs(s, result, new ArrayList<>(), 0);
        return result;
    }

    private void dfs(String src, List<List<String>> result, ArrayList<String> path, int start) {
        if (start >= src.length()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.addAll(path);
            result.add(temp);
            return;
        }
        for (int i = start; i < src.length(); i++) {
            String temp = src.substring(start, i + 1);
            if (!isPalindrome(temp))
                continue;
            path.add(temp);
            dfs(src, result, path, i + 1);
            path.remove(temp);
        }
    }

    private boolean isPalindrome(String src) {
        if (src == null)
            return false;
        int i = 0, j = src.length() - 1;
        while (i < j) {
            if (src.charAt(i) != src.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("aab"));
    }
}
