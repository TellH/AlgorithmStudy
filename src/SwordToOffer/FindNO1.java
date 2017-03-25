package SwordToOffer;

/**
 * Created by tlh on 2017/3/19.
 */
public class FindNO1 {
    public long NumberOf1Between1AndN_Solution(int num) {
        if (num <= 0)
            return 0;

        long count = 0;    //统计1出现的次数
        long current;    //当前位
        long base = 1; //当前位的基
        long remain = 0;   //当前位为1时，后面位剩余的数（即不完整的部分）中1出现的次数
        while (num != 0) {
            current = num % 10;
            num = num / 10;
            if (current > 1)
                count += (num + 1) * base;
            else if (current == 1)
                count += num * base + (remain + 1);
            else
                count += num * base;
            //下一位要用到的基和剩余不完整部分值
            remain += current * base;
            base *= 10;
        }
        return count;
    }
}
