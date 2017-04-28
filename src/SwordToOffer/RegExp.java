package SwordToOffer;

/**
 * Created by tlh on 2017/3/28.
 */
public class RegExp {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        if (i == str.length && j == pattern.length) return true;
        if (i < str.length && j >= pattern.length) return false;
        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
            if ((i < str.length && pattern[j] == '.')
                    || (i < str.length && j < pattern.length && str[i] == pattern[j]))
                return match(str, i + 1, pattern, j + 2) // 匹配一个，跳出
                        || match(str, i + 1, pattern, j) // 继续匹配
                        || match(str, i, pattern, j + 2); // 不匹配，跳出
            else return match(str, i, pattern, j + 2); // 无法匹配，跳出
        } else {
            if (i >= str.length || j >= pattern.length) return false;
            if (str[i] == pattern[j] || (i < str.length && pattern[j] == '.')) return match(str, i + 1, pattern, j + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new RegExp().match(new char[]{}, new char[]{'.', '*'}));
    }
}
