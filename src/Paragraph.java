import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tlh on 2017/3/30.
 */
public class Paragraph {
    static Pattern pattern = Pattern.compile("[a-zA-Z]+");
    static List<Set<String>> words = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        in.nextLine();
        String[] p = new String[N];
        for (int i = 0; i < N; i++) {
            String s = in.nextLine();
            addWordz(s);
            p[i] = s;
        }
        for (int i = 0; i < M; i++) {
            Matcher matcher = pattern.matcher(in.nextLine());
            HashSet<String> set = new HashSet<>();
            String word;
            int[] sameCount = new int[N];
            while (matcher.find()) {
                word = matcher.group();
                if (set.contains(word))
                    continue;
                set.add(word);
                for (int k = 0; k < N; k++) {
                    if (words.get(k).contains(word)) sameCount[k]++;
                }
            }
            System.out.println(p[findMaxIndex(sameCount)]);
        }
    }

    private static int findMaxIndex(int[] sameCount) {
        int max = -1;
        int result = 0;
        for (int i = 0; i < sameCount.length; i++) {
            if (max < sameCount[i]) {
                max = sameCount[i];
                result = i;
            }
        }
        return result;
    }

    private static void addWordz(String s) {
        HashSet<String> wordz = new HashSet<>();
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            wordz.add(matcher.group());
        }
        words.add(wordz);
    }
}
