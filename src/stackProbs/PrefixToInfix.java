package stackProbs;

import java.util.Stack;

public class PrefixToInfix {
    public static void main(String[] args) {

    }

    static String preToIn(String exp) {
        Stack<String> st = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) {
                st.push(c + "");
            } else {
                String a = st.pop();
                String b = st.pop();
                st.push("(" + (a + "" + c + "" + b) + ")");
            }
        }
        return st.pop();
    }

    static boolean isOperator(char ch) {
        return switch (ch) {
            case '+', '-', '*', '/', '^' -> true;
            default -> false;
        };
    }

    static String prefixToInfix(String exp) {
        Stack<String> stack = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            }
            // If the character is an operator
            else if (isOperator(c)) {
                // Pop two operands from the stack
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String expression = "(" + operand1 + " " + c + " " + operand2 + ")";
                stack.push(expression);
            }
        }
        return stack.pop();
    }
}
