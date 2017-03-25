package SwordToOffer;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.Scanner;

/**
 * Created by tlh on 2017/1/12.
 * 面试题3：
 * 二维数组中的查找
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 完成一个函数，输入这样要给二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 解法：
 * 每次跟数组的右上角那个数比较，如果给定的数比它小，排除数组的最后一列；如果给定的数比它大，排除数组的第一行；否则给定的数在数组中。
 * 如此循环，直到找到该数或排除玩数组的所有数为止。
 */
public class T3 {
    private int[][] a;
    private int minRow;
    private int maxCol;

    private T3(int[][] a) {
        this.a = a;
        minRow = 0;
        maxCol = a[0].length - 1;
    }

    private boolean run(int n) {
        while (minRow < a.length && maxCol >= 0) {
            int t = a[minRow][maxCol];
            if (t == n)
                return true;
            else if (n > t)
                minRow++;
            else maxCol--;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] a = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[i][j] = in.nextInt();
            }
        }
        T3 t3 = new T3(a);
        System.out.println(t3.run(in.nextInt()));
    }

    public static class Solution {
        public boolean Find(int target, int[][] array) {
            if (array == null || array.length == 0)
                return false;
            int row = 0;
            int col = array[0].length - 1;
            while (row < array.length && col >= 0) {
                if (target > array[row][col])
                    row++;
                else if (target < array[row][col])
                    col--;
                else return true;
            }
            return false;
        }
    }
}
