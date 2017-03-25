package SwordToOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by tlh on 2017/3/17.
 */
public class StringPermutation {
    ArrayList<String> result = new ArrayList<>();
    LinkedHashSet<String> result_ = new LinkedHashSet<>();
    int len = 0;
    String base = null;
    char[] temp = null;
    boolean[] book;

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.isEmpty())
            return result;
        len = str.length();
        base = str;
        temp = new char[len];
        book = new boolean[len];
        dfs(temp, 0);
        result.addAll(result_);
        return result;
    }

    public void dfs(char[] temp, int index) {
        if (index == len) {
            String element = new String(temp);
            result_.add(element);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!book[i]) {
                book[i] = true;
                temp[index] = base.charAt(i);
                dfs(temp, index + 1);
                book[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new StringPermutation().Permutation("aa").toString());
    }
}
