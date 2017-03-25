package SwordToOffer;

/**
 * Created by tlh on 2017/3/25.
 */
public class ReverseSentence {
    public String ReverseSentence_(String str) {
        if (str == null || str.isEmpty() || " ".equals(str))
            return str;
        String reverse = reverse(str);
        String[] split = reverse.split(" ");
        if (split.length <= 0)
            return str;
        return reverse(split);
    }

    private String reverse(String[] split) {
        int k;
        StringBuilder sb = new StringBuilder();
        for (k = 0; k < split.length - 1; k++) {
            split[k] = reverse(split[k]);
            sb.append(split[k]).append(" ");
        }
        sb.append(reverse(split[k]));
        return sb.toString();
    }

    private String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseSentence().ReverseSentence_("\" \""));
    }
}
