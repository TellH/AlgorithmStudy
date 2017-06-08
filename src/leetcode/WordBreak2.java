package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tlh on 2017/6/6.
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s ="catsanddog",
 * dict =["cat", "cats", "and", "sand", "dog"].
 * A solution is["cats and dog", "cat sand dog"].
 */
public class WordBreak2 {
    private ArrayList<String> result;

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        HashSet<String> set = new HashSet<>(dict);
        dfs(s, set, new ArrayList<>(), 0);
        return result;
    }

    private void dfs(String s, Set<String> dict, ArrayList<String> path, int start) {
        if (start >= s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    sb.append(path.get(i));
                } else {
                    sb.append(path.get(i)).append(" ");
                }
            }
            result.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String temp = s.substring(start, i + 1);
            if (!dict.contains(temp)) continue;
            path.add(temp);
            dfs(s, dict, path, i + 1);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        System.out.println(new WordBreak2().wordBreak("catsanddog", set));
    }
}
