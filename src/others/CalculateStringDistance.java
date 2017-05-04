package others;

/**
 * Created by tlh on 2017/5/2.
 * 计算两个字符串的距离和相似度。可以通过对字符串修改一个字符，增加一个字符，删除一个字符的方式来操作字符串，
 * 这些操作所需要的次数定义为两个字符串的距离。
 */
public class CalculateStringDistance {
    public int getDistance(String strA, int pALeft, int pARight, String strB, int pBLeft, int pBRight) {
        if (pALeft > pARight) {
            if (pBLeft > pBRight) return 0;
            else return pBRight - pBLeft + 1;
        }
        if (pBLeft > pBRight) {
            if (pALeft > pARight) return 0;
            else return pARight - pALeft + 1;
        }
        if (strA.charAt(pALeft) == strB.charAt(pBLeft))
            return getDistance(strA, pALeft + 1, pARight, strB, pBLeft + 1, pBRight);  //
        else {
            int t1 = getDistance(strA, pALeft + 1, pARight, strB, pBLeft, pBRight);
            int t2 = getDistance(strA, pALeft, pARight, strB, pBLeft + 1, pBRight);
            int t3 = getDistance(strA, pALeft + 1, pARight, strB, pBLeft + 1, pBRight); // 有重复计算的子问题，可以将计算结果缓存到Map里面
            return min(t1, t2, t3) + 1;
        }
    }

    private int min(int t1, int t2, int t3) {
        int result = t1;
        if (result > t2) result = t2;
        if (result > t3) result = t3;
        return result;
    }
}
