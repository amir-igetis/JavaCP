package LeetcodeInterview.stack;

import java.util.Stack;

public class EvalReversePolishNotation {
    public static void main(String[] args) {
//
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens));
    }

    static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String i : tokens) {
            if (isOperator(i)) {
                int operand2 = st.pop();
                int operand1 = st.pop();
                int res = performOp(i, operand1, operand2);
                st.push(res);
            } else {
                int operand = Integer.parseInt(i);
                st.push(operand);
            }
        }
        return st.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/");
    }

    private static int performOp(String operator, int operand1, int operand2) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        } else if (operator.equals("*")) {
            return operand1 * operand2;
        } else {
            return operand1 / operand2;
        }
    }
}
