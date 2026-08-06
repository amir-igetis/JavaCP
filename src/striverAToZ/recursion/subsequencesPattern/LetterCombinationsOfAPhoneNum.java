package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNum {

    /// Problem Statement: Given a string consisting of digits from 2 to 9 (inclusive). Return all possible letter combinations that the number can represent.
    public static void main(String[] args) {
        String digits = "23"; // Input digits
        List<String> result = letterCombinations(digits); // Get combinations

        // Print the results
        for (String combination : result) {
            System.out.print(combination + " "); // Display each combination
        }
    }

    /// Time Complexity: O(4^N * N), where n is the length of the input digits. This is because each digit can map to up to 4 letters, and there are n digits.
    ///
    /// Space Complexity: O(N), where n is the length of the input digits. This is due to the recursion stack depth.

    private static final String[] map = new String[]
            {
                    "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
            };

    static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>(); // List to store results

        // Return empty list if digits string is empty
        if (digits.isEmpty()) return ans;

        // Initiate recursive function
        helper(digits, ans, 0, "");
        return ans; // Return the result
    }

    // Recursive helper function to generate combinations
    private static void helper(String digits, List<String> ans, int index, String current) {
        // Base case: if index reaches the end of digits
        if (index == digits.length()) {
            // Add the current combination to the answer
            ans.add(current);
            return;
        }

        // Get characters corresponding to the current digit
        String s = map[digits.charAt(index) - '0'];

        // Loop through the corresponding characters
        for (int i = 0; i < s.length(); i++) {
            // Recursively call function with the next index
            // Add current character to the string
            helper(digits, ans, index + 1, current + s.charAt(i));
        }
    }
}
