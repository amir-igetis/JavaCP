package striverAToZ.stacksAndQueues.prefixInfixProb;

import java.util.Stack;

public class PostfixToPrefix {

    /// Problem Statement: You are given a valid postfix expression as a string, where:
    /// Operands are single lowercase English letters ('a' to 'z')
    /// Operators are binary: '+', '-', '*', '/'
    /// The expression contains no spaces and is guaranteed to be valid.
    ///
    /// Write a function to convert the postfix expression into a prefix expression, also as a string without spaces.

    public static void main(String[] args) {
        String postfix = "ABC/-AK/L-*";
        System.out.println("Prefix Expression: " +
                postfixToPrefix(postfix));

    }

    /// Time Complexity: O(n), as we traverse the expression only once.
    ///
    /// Space Complexity: O(n) for the stack to store operands and intermediate results.

    static String postfixToPrefix(String postfix) {
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

                // Form the new prefix expression and push back to stack
                s.push(c + op1 + op2);
            }
        }

        // The final element in the stack is the result
        return s.peek();
    }
}

