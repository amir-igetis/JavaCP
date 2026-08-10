package striverAToZ.stacksAndQueues.prefixInfixProb;

import java.util.Stack;

public class PostfixToInfix {

    /// Problem Statement: Given a postfix expression (a string), convert it into an equivalent infix expression. The postfix expression is evaluated from left to right. The infix expression should have the proper parentheses to ensure correct operator precedence.
    ///
    /// Write a function to perform this conversion.

    public static void main(String[] args) {
        String postfix = "AB*C+";
        System.out.println("Infix Expression: " +
                postfixToInfix(postfix));
    }

    /// Time Complexity: O(n), a single pass over the postfix expression.
    ///
    /// Space Complexity: O(n), stack space for storing operands.

    static String postfixToInfix(String postfix) {
        Stack<String> s = new Stack<>();
        int n = postfix.length();

        // Traverse the postfix expression from left to right
        for (int i = 0; i < n; i++) {
            char c = postfix.charAt(i);

            // If the character is an operand, push it to the stack
            if (Character.isLetterOrDigit(c)) {
                s.push(String.valueOf(c));
            } else {
                // Pop two operands from the stack
                String op2 = s.pop();
                String op1 = s.pop();

                // Form the new infix expression and push back to stack
                s.push("(" + op1 + c + op2 + ")");
            }
        }

        // The final element in the stack is the result
        return s.peek();
    }
}
