package others;

/**
 * 求数组中的第k个数
 */
public class GetKthInArray {
    private int getKth(int[] a, int start, int end, int k) {
        if (start >= end) return a[start];
        int base = a[start];
        int i = start, j = end;
        while (true) {
            while (i < j && a[j] >= base) j--;
            while (i < j && a[i] <= base) i++;
            if (i != j) {
                swap(a, i, j);
            } else {
                break;
            }
        }
        swap(a, i, start);
        int leftLen = i - start + 1;
        if (leftLen == k) return a[i];
        else if (leftLen < k) return getKth(a, i + 1, end, k - leftLen);
        else return getKth(a, start, i - 1, k);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 5, 6, 44, 3};
        System.out.println(new GetKthInArray().getKth(a, 0, a.length - 1, 6));
    }

}
