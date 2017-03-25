package others;

/**
 * Created by tlh on 2017/1/30.
 */
public class KMP<T extends Comparable<T>> {
    private T[] pat;

    public KMP(T[] pat) {
        if (pat == null)
            throw new RuntimeException("No pattern array.");
        this.pat = pat;
    }

    public boolean contained(T[] test) {
        if (test == null)
            return false;
        int i = 0, j = 0;
        int[] next = getNext(pat);
        while (i < test.length && j < pat.length) {
            if (j == -1 || equals(test[i], pat[j])) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pat.length)
            return true;
        return false;
    }

    private boolean equals(T a, T b) {
        if (a == null && b == null)
            return true;
        if (a == null)
            return false;
        return a.compareTo(b) == 0;
    }

    private int[] getNext(T[] pat) {
        int length = pat.length;
        int[] next = new int[length];
        next[0] = -1;
        next[1] = 0;
        int k = 0;
        // pat[k]是前缀，pat[j - 1]是后缀
        for (int j = 2; j < length; j++) {
            while (k > 0 && !equals(pat[j - 1], pat[k])) k = next[k];
            k = next[j] = equals(pat[j - 1], pat[k]) ? k + 1 : 0;
        }
        return next;
    }

    public static void main(String[] args) {
        Character[] pattern = {'A', 'B', 'A', 'C', 'A', 'B', 'A', 'B', 'F'};
        KMP<Character> kmp = new KMP<>(pattern);
//        int[] next = kmp.getNext(pattern);
        String test = "ABCDDDSSADGAFADABACDFADSABACABABFADDSADDDX";
        char[] chars = test.toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        System.out.println(kmp.contained(characters));
    }
}
