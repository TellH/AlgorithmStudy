package leetcode;

/**
 * Created by tlh on 2017/6/2.
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 用ones记录到当前计算的变量为止，二进制1出现“1次”（mod 3 之后的 1）的数位。
 * 用twos记录到当前计算的变量为止，二进制1出现“2次”（mod 3 之后的 2）的数位。
 * 当ones和twos中的某一位同时为1时表示二进制1出现3次，此时需要清零。即用二进制模拟三进制计算。
 * 最终ones记录的是最终结果。
 */
public class SingleNumber {
    public int singleNumber(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= (ones & A[i]);
            ones ^= A[i];
            int mask = ~(ones & twos);
            ones &= mask; // 对相应出现1的次数等于3的bit位清零
            twos &= mask; // 对相应出现1的次数等于3的bit位清零
        }
        return ones;
    }
}
