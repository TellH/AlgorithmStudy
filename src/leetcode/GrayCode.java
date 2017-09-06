package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return[0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 */
public class GrayCode {

    public ArrayList<Integer> grayCode0(int n) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int num = 1 << n;
        for (int i = 0; i < num; ++i) {
            arr.add(i >> 1 ^ i);
        }
        return arr;
    }

    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        if (n <= 0) {
            res.add(0);
            return res;
        }
        ArrayList<LinkedList<Integer>> codes = new ArrayList<>();
        code(n, codes);
        convertToInteger(res, codes);
        return res;
    }

    private void convertToInteger(ArrayList<Integer> res, ArrayList<LinkedList<Integer>> binCodes) {
        for (LinkedList<Integer> binCode : binCodes) {
            int size = binCode.size();
            int num = 0;
            for (int i = 0; i < binCode.size(); i++) {
                num += Math.pow(2, size - i - 1) * binCode.get(i);
            }
            res.add(num);
        }
    }

    private void code(int n, ArrayList<LinkedList<Integer>> codes) {
        // 一开始序列是0，1
        LinkedList<Integer> zero = new LinkedList<>();
        zero.add(0);
        codes.add(zero);
        LinkedList<Integer> one = new LinkedList<>();
        one.add(1);
        codes.add(one);
        for (int i = 2; i <= n; i++) {
            int size = codes.size();
            // 添加逆序列
            for (int j = codes.size() - 1; j >= 0; j--) {
                codes.add(new LinkedList<>(codes.get(j)));
            }
            // 正序列 前插0
            for (int j = 0; j < size; j++) {
                codes.get(j).add(0, 0);
            }
            // 逆序列 前插1
            for (int j = size; j < codes.size(); j++) {
                codes.get(j).add(0, 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(2));
    }
}
