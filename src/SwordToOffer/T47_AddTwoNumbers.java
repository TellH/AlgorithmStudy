package SwordToOffer;

/**
 * Created by tlh on 2017/1/16.
 */
public class T47_AddTwoNumbers {
    public static int add(int num1, int num2) {
        int sum, carry;
        do {
            sum = num1 ^ num2; //不考虑进位，各位分别相加
            carry = (num1 & num2) << 1; //求出进位
            //上面两步的结果相加
            num1 = sum;
            num2 = carry;
        } while (num2 != 0);//当进位为0时结束循环
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(add(11, 1));
    }
}
