package striverAToZ.stacksAndQueues.prefixInfixProb;

import java.util.Stack;

public class PrefixToPostfix {

    /// Problem Statement: You are given a valid prefix expression consisting of binary operators and single-character operands. Your task is to convert it into a valid postfix expression.
    ///
    /// Prefix (Polish) notation places the operator before operands.
    /// Postfix (Reverse Polish) notation places the operator after operands.

    public static void main(String[] args) {
        String prefix = "*-A/BC-/AKL";
        System.out.println("Postfix Expression: " +
                prefixToPostfix(prefix));

    }

    /// Time Complexity: O(n), single pass through the expression.
    ///
    /// Space Complexity: O(n), stack space for storing intermediate results.
    static String prefixToPostfix(String prefix) {
        Stack<String> s = new Stack<>();
        int n = prefix.length();

        // Traverse the prefix expression from right to left
        for (int i = n - 1; i >= 0; i--) {
            char c = prefix.charAt(i);

            // If the character is an operand, push it to the stack
            if (Character.isLetterOrDigit(c)) {
                s.push(String.valueOf(c));
            } else {
                // Pop two operands from the stack
                String op1 = s.pop();
                String op2 = s.pop();

                // Form the new postfix expression and push back to stack
                s.push(op1 + op2 + c);
            }
        }

        // The final element in the stack is the result
        return s.peek();
    }
}