import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tlh on 2017/3/1.
 */
public class CalculateExpression {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String exp = in.next();
        int at = 0;
        calculate(exp, at);
    }

    private static int calculate(String exp, int at) {
        Stack<Integer> result = new Stack<>();
        boolean isNum = false;
        boolean hasMinus = false;
        while (true) {
            if (at > exp.length() - 1 || exp.charAt(at) == ')')
                return sumUp(result);
            char ch = exp.charAt(at);
            if (ch > '0' && ch < '9') {
                int val = ch - '0';
                if (isNum) {
                    result.push(result.pop() * 10 + val);
                } else {
                    if (hasMinus) {
                        val = val * -1;
                        hasMinus = false;
                    }
                    result.push(val);
                }
                isNum = true;
            } else if (ch == '+') {
                isNum = false;
                hasMinus = false;
            } else if (ch == '-') {
                isNum = false;
                hasMinus = true;
            } else if (ch == '*') {
                isNum = false;

            } else if (ch == '/') {
                isNum = false;

            } else if (ch == '(') {
                isNum = false;
            } else if (ch == ')') {
                isNum = false;
            }
        }
    }

    private static int sumUp(Stack<Integer> result) {
        return 0;
    }
}
