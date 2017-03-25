package SwordToOffer;

/**
 * Created by tlh on 2017/3/13.
 */
public class SquenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
        if (start >= end)
            return true;
        int root = sequence[end];
        int i = start;
        while (sequence[i] < root) i++;
        return verityBigger(sequence, i, end - 1, root) && VerifySquenceOfBST(sequence, start, i - 1)
                && VerifySquenceOfBST(sequence, i, end - 1);
    }

    private boolean verityBigger(int[] sequence, int start, int end, int root) {
        for (int i = start; i <= end; i++) {
            if (sequence[i] < root)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SquenceOfBST sq = new SquenceOfBST();
        System.out.println(sq.VerifySquenceOfBST(new int[]{9, 6, 5, 12, 19, 15, 10}));;
    }
}
