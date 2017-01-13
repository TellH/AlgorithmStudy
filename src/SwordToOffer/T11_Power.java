package SwordToOffer;

import java.util.Scanner;

/**
 * Created by tlh on 2017/1/13.
 * 实现数值的整数次方
 * 实现函数double power(double base,int exponent)，求base的exponent次方。不得使用库函数，不考虑大数问题。
 */
public class T11_Power {
    public static double power(double base, int exponent) {
        //0的负数次幂没有意义
        if (equals(base, 0.0) && exponent < 0)
            throw new RuntimeException("0的负数次幂没有意义");
        if (base == 0)
            return 0;
        int absExponent = Math.abs(exponent);
        double result = powerWithUnsignedExponent(base, absExponent);
        if (exponent < 0)
            result = 1.0 / result;
        return result;
    }

    /**
     * 运用公式
     * n为偶数，a^n=a^(n/2)*a^(n/2)
     * n为奇数，a^n=a^((n-1)/2)*a^((n-1)/2)*a
     * 用递归的方式实现
     * @param base
     * @param absExponent
     * @return
     */
    private static double powerWithUnsignedExponent(double base, int absExponent) {
        if (absExponent == 0)
            return 1;
        else if (absExponent == 1)
            return base;
        double result = powerWithUnsignedExponent(base, absExponent >> 1);
        result *= result;
        if ((absExponent & 0x1) == 1)//如果是奇数
            result *= base;
        return result;
    }

    private static boolean equals(double a, double b) {
        return Math.abs(a - b) < 0.00000001;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(T11_Power.power(in.nextInt(), in.nextInt()));
        }
    }
}
