package others;

import java.util.Random;

/**
 * Created by tlh on 2017/4/29.
 * 产生一个长度为100的数组，为数组中的每一项随机填充1-100之间的数并且保证不重复
 */
public class RandomSetValueToArray {
    public int[] getArray() {
        int[] result = new int[100];
        int[] book = new int[100];
        for (int i = 0; i < book.length; i++) {
            book[i] = i + 1;
        }
        int end = book.length - 1;
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < result.length; i++) {
            int index = r.nextInt(end + 1); //exclude end+1
            result[i] = book[index];
            swap(book, index, end--);
        }
        return result;
    }

    public int[] getArray(int a, int b) {
        int[] result = new int[b - a + 1];
        int[] book = new int[b - a + 1];
        for (int i = 0, j = a; i < book.length; i++) {
            book[i] = j++;
        }
        int end = book.length - 1;
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < result.length; i++) {
            int index = r.nextInt(end + 1); //exclude end+1
            result[i] = book[index];
            swap(book, index, end--);
        }
        return result;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] array = new RandomSetValueToArray().getArray(1, 10);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
