package SwordToOffer;

/**
 * Created by tlh on 2017/1/14.
 * 求字符串的所有组合
 * 输入n个字符，求长度为m的组合。
 * 分解成两个子问题，分别求n-1个字符串中长度为m的组合，以及求n-1个字符的长度为m-1的组合。
 * 用递归解决，当m==0时结束递归，并打印生成的组合。
 */
public class T28_Permutation {
    public static void combination(String s, int m) {
        if (s == null || s.isEmpty() || s.length() < m)
            throw new RuntimeException("Invalid Input!");
        for (int i = 1; i <= m; i++) {
            combination(s, 0, i, "");
        }
    }

    /**
     * @param s          取值样本字符串
     * @param startIndex 对应于样本字符串开始取值的起始下标
     * @param m          组合的长度
     * @param prefix     生成的组合
     */
    private static void combination(String s, int startIndex, int m, String prefix) {
        int n = s.length() - startIndex; //可以取值的样本字符串长度
        if (m == 0) { //递归结束
            System.out.println(prefix);
            return;
        }
        if (startIndex >= s.length() || n < m) //限制递归边界
            return;
        combination(s, startIndex + 1, m, prefix);//不包含字符s.charAt(startIndex)，从往后剩下的样本字符串中取m个值
        prefix += s.charAt(startIndex);//包含字符s.charAt(startIndex)
        combination(s, startIndex + 1, m - 1, prefix);//从往后剩下的样本字符串中取m-1个值
    }

    //当n == m时结束递归可以减少整个过程递归的次数
    private static void combination_(String s, int startIndex, int m, String prefix) {
        int n = s.length() - startIndex;
        if (m == 0) {
            System.out.println(prefix);
            return;
        }
        if (startIndex >= s.length() || n < m)
            return;
        if (n == m) {
            prefix += s.substring(startIndex, startIndex + m);
            System.out.println(prefix);
            return;
        }
        combination(s, startIndex + 1, m, prefix);
        prefix += s.charAt(startIndex);
        combination(s, startIndex + 1, m - 1, prefix);
    }

    public static void main(String[] args) {
        combination("abc", 3);
//        combination("abc", 0, 3, "");
    }
}
