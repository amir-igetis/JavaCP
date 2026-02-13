package LeetcodeInterview.stack;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
//
        String s = "2-1 + 2";
        System.out.println(calculate(s));
    }

    static int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int num = 0, res = 0, sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch))
                num = num * 10 + (ch - '0');

            else if (ch == '+' || ch == '-') {
                res += sign * num;
                num = 0;
                sign = (ch == '+') ? 1 : -1;
            } else if (ch == '(') {
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                res += sign * num;
                num = 0;
                res *= st.pop();
                res += st.pop();
            }
        }
        res += sign * num;
        return res;
    }
}
