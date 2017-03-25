package SwordToOffer;

/**
 * Created by tlh on 2017/3/11.
 */
public class PowerMath {
    public static void main(String[] args) {
        System.out.println(Power(2, -1));
    }

    public static double Power(double base, int exponent) {
        if (exponent < 0 && base == 0)
            throw new RuntimeException("Invalid input");
        if (base == 0)
            return 0;
        double result;
        if (exponent < 0) {
            result = 1.0 / PowerWithUnSign(base, -exponent);
        } else {
            result = PowerWithUnSign(base, exponent);
        }
        return result;
    }

    public static double PowerWithUnSign(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double result = PowerWithUnSign(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1)
            result *= base;
        return result;
    }

    public static double PowerFast(double base, int exponent) {
        if (exponent < 0 && base == 0)
            throw new RuntimeException("Invalid input");
        int exp = Math.abs(exponent);
        int result = 1;
        while (exp != 0) {
            if ((exp & 1) == 1)
                result *= base;
            base *= base;
            exp >>= 1;
        }
        return exponent < 0 ? 1.0 / result : result;
    }
}
