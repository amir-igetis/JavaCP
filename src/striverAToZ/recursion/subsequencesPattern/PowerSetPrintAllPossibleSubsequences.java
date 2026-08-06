package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.List;

public class PowerSetPrintAllPossibleSubsequences {

    /// Problem Description: Given a string, find all the possible subsequences of the string.

    public static void main(String[] args) {
        // Input string
        String s = "abc";


        // Get all subsequences
        List<String> subsequences = getSubsequences(s);

        // Print all subsequences
        for (String subseq : subsequences) {
            System.out.println("\"" + subseq + "\"");
        }
    }

    /// Time Complexity: O(n * 2^n), for each subsequence, we may check up to n bits to decide inclusion.
    /// Space Complexity: O(n * 2^n), space used to store all possible subsequences.
    // approach 1
    static List<String> getSubsequences(String s) {
        // Length of input string
        int n = s.length();

        // Total subsequences = 2^n
        int total = 1 << n;

        // List to store all subsequences
        List<String> subsequences = new ArrayList<>();

        // Iterate over all bit masks from 0 to 2^n - 1
        for (int mask = 0; mask < total; mask++) {
            // Temporary subsequence builder
            StringBuilder subseq = new StringBuilder();

            // Check each bit position in mask
            for (int i = 0; i < n; i++) {
                // If i-th bit of mask is set, include s.charAt(i)
                if ((mask & (1 << i)) != 0) {
                    subseq.append(s.charAt(i));
                }
            }

            // Store the formed subsequence as string
            subsequences.add(subseq.toString());
        }

        // Return all generated subsequences
        return subsequences;
    }

    /// Time Complexity: O(n * 2^n), for each subsequence, we construct and print the entire subsequence.
    /// Space Complexity: O(n * 2^n), space used to store all possible subsequences.
    // approach 2
    static List<String> getSubsequencesI(String s) {
        // List to store all subsequences
        List<String> result = new ArrayList<>();

        // StringBuilder to store current subsequence
        StringBuilder current = new StringBuilder();

        // Start recursion from index 0
        helper(s, 0, current, result);

        // Return list of subsequences
        return result;
    }

    private static void helper(String s, int index, StringBuilder current, List<String> result) {
        // Base case: if index reaches string length, add current subsequence to result
        if (index == s.length()) {
            result.add(current.toString());
            return;
        }

        // Exclude current character and recurse
        helper(s, index + 1, current, result);

        // Include current character and recurse
        current.append(s.charAt(index));
        helper(s, index + 1, current, result);

        // Backtrack by removing last character
        current.deleteCharAt(current.length() - 1);
    }
}
