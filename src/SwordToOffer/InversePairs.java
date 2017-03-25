package SwordToOffer;

/**
 * Created by tlh on 2017/3/18.
 */
public class InversePairs {
    int[] bak;
    int count = 0;

    public int inversePairs(int[] array) {
        if (array==null)
            return 0;
        bak = new int[array.length];
        mergeSort(array, 0, array.length-1);
        return count % 1000000007;
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    private void merge(int[] array, int start, int mid, int end) {
        System.arraycopy(array, start, bak, start, end - start + 1);
        int i = start, j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                array[k] = bak[j++];
            } else if (j > end) {
                array[k] = bak[i++];
            } else if (bak[j] < bak[i]) {
                array[k] = bak[j++];
                count += mid - i + 1;
                count%=1000000007;
            } else {
                array[k] = bak[i++];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new InversePairs().inversePairs(new int[]{493330277}));
    }
}
