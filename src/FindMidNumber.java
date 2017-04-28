/**
 * Created by tlh on 2017/4/6.
 */
public class FindMidNumber {
    public static void main(String[] args) {
        System.out.println(new FindMidNumber().findMin(new int[]{23, 29, 20, 32}));
//        System.out.println(new FindMidNumber().findMin(new int[]{23, 21, 33, 25}));
    }

    public double findMin(int[] a) {
        quickSort(a, 0, a.length - 1);
        if ((a.length & 1) == 1) {
            return a[a.length / 2];
        } else {
            return (a[a.length / 2] + a[a.length / 2 - 1]) / 2.0;
        }
    }

    private void quickSort(int[] a, int left, int right) {
        if (right <= left)
            return;
        int base = a[left];
        int i = left, j = right;
        while (i != j) {
            while (a[j] >= base && j > i)
                j--;
            while (a[i] <= base && i < j)
                i++;
            if (i < j)
                swap(a, i, j);
        }
        swap(a, left, i);
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
