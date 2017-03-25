package SwordToOffer;

/**
 * Created by tlh on 2017/3/20.
 */
public class GetNumberOfK {
    public int getNumberOfK(int[] array, int k) {
        int i = 0, j = array.length - 1, mid;
        int from = 0;
        while (i <= j) {
            mid = (i + j) / 2;
            if (array[mid] > k) {
                j = mid - 1;
            } else if (array[mid] < k) {
                i = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] != k) {
                    from = mid;
                    break;
                } else {
                    j = mid - 1;
                }
            }
        }
        int count = 0;
        while (from < array.length) {
            if (array[from++] == k) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new GetNumberOfK().getNumberOfK(new int[]{3, 3, 3, 3, 3, 4, 5, 6, 7, 55}, 4));
    }
}
