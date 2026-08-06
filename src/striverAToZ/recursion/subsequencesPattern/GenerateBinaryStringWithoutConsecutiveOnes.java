package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStringWithoutConsecutiveOnes {

    /// Problem Statement: Given an integer n, return all binary strings of length n that do not contain consecutive 1s. Return the result in lexicographically increasing order.
    ///
    /// A binary string is a string consisting only of characters '0' and '1'.

    public static void main(String[] args) {
        // Input length n
        int n = 3;

        // List to store results
        List<String> result = new ArrayList<>();

        // Start recursion with empty string
        generate(n, "", result);

        // Print results
        for (String s : result) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /// Time Complexity: O(2^n), since each position has 2 choices.
    ///
    /// Space Complexity: O(n) per recursive path (due to call stack)

    static void generate(int n, String curr, List<String> result) {
        // Base case: if length is n, add to result
        if (curr.length() == n) {
            result.add(curr);
            return;
        }

        // Always try adding '0'
        generate(n, curr + "0", result);

        // Add '1' only if previous char is not '1'
        if (curr.isEmpty() || curr.charAt(curr.length() - 1) != '1') {
            generate(n, curr + "1", result);
        }
    }
}
