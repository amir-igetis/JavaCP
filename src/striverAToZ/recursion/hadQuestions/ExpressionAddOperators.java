package striverAToZ.recursion.hadQuestions;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    /// Problem Statement: Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
    ///
    /// Note that operands in the returned expressions should not contain leading zeros.
    ///
    /// Note that a number can contain multiple digits.
    public static void main(String[] args) {
        String num = "123";
        int target = 6;
        List<String> result = addOperators(num, target);

        for (String expr : result) {
            System.out.print(expr + " ");
        }
    }


    /// Time Complexity: O(4^n), since in each recursive call, we can choose 4 possibilities for each substring (three operators: +, -, *, or no operator in the case of the first number), resulting in an exponential time complexity with a branching factor of 4.
    ///
    /// Space Complexity: O(n), since the space complexity is dominated by the recursion depth, which can go as deep as the length of the string. Additionally, we store the result expressions in a list, but this doesn't increase the space complexity beyond the recursion stack and the input size.
    static List<String> addOperators(String num, int target) {
        // To store the valid expressions
        List<String> result = new ArrayList<>();
        // Start DFS with empty expression
        dfs(num, target, 0, 0, 0, "", result);
        return result;
    }

    private static void dfs(String num, int target, int start, long current_value, long last_operand, String expression, List<String> result) {
        // Base case: If we've reached the end of the string
        if (start == num.length()) {
            // If the expression evaluates to the target, add it to result
            if (current_value == target) {
                result.add(expression);
            }
            return;
        }

        // Loop through all substrings starting from 'start' index
        for (int i = start; i < num.length(); i++) {
            // Skip leading zeros in numbers
            if (i > start && num.charAt(start) == '0') return;
            // Get the current number
            String current_num = num.substring(start, i + 1);
            long current_num_val = Long.parseLong(current_num);

            // If we are at the first number, just start the expression
            if (start == 0) {
                dfs(num, target, i + 1, current_num_val, current_num_val, current_num, result);
            } else {
                // Add the current number with '+'
                dfs(num, target, i + 1, current_value + current_num_val, current_num_val, expression + "+" + current_num, result);

                // Add the current number with '-'
                dfs(num, target, i + 1, current_value - current_num_val, -current_num_val, expression + "-" + current_num, result);

                // Add the current number with '*'
                dfs(num, target, i + 1, current_value - last_operand + last_operand * current_num_val, last_operand * current_num_val, expression + "*" + current_num, result);
            }
        }
    }
}