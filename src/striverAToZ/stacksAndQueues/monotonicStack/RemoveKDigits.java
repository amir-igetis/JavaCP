package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Stack;

public class RemoveKDigits {

    /// Question 9
    /// Problem Statement: Given a string nums representing a non-negative integer, and an integer k, find the smallest possible integer after removing k digits from num.
    public static void main(String[] args) {
        String nums = "541892"; // Input number as string
        int k = 2; // Number of digits to remove
        String ans = removeKdigits(nums, k);

        // Print the result
        System.out.println("The smallest possible integer after removing k digits is: " + ans);

    }

    /// Time Complexity: O(N), since traversing the given string takes O(N) time, each element is pushed onto and popped from the stack at most once in worst-case taking o(N) time, removing the remaining digits (if k > 0) takes O(k) time which can go upto O(N) in worst-case and forming the result, trimming the zeros and reversing the digits takes O(N) time.
    ///
    /// Space Complexity: O(N), since we are using a stack to store the digits of the resulting number, in the worst case, the stack can contain all the digits of the input string.
    static String removeKdigits(String nums, int k) {
        // Stack to store digits
        Stack<Character> st = new Stack<>();

        // Traverse the given string
        for (int i = 0; i < nums.length(); i++) {
            char digit = nums.charAt(i); // Current digit

            // Pop last digits if a smaller digit is found and k > 0
            while (!st.isEmpty() && k > 0 && st.peek() > digit) {
                st.pop(); // Remove the last digit
                k--; // Decrement k by 1
            }

            // Push the current digit
            st.push(digit);
        }

        // If more digits can be removed
        while (k > 0) {
            st.pop(); // Pop the last added digits
            k--; // Decrement k by 1
        }

        // Handle edge case: if stack is empty
        if (st.isEmpty()) return "0";

        // StringBuilder to store the result
        StringBuilder res = new StringBuilder();

        // Add digits from stack to result
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        // Trim the leading zeros
        while (res.length() > 0 && res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }

        // Reverse the string to get the correct number
        res.reverse();

        // If result is empty, return "0"
        if (res.length() == 0) return "0";

        // Return the result as a string
        return res.toString();
    }
}