package striverAToZ.strings.basicAndEasyProbs;

import java.util.Arrays;

public class LongestCommonPrefix {

    /// Question 4
    ///
    /// Problem Statement: Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
    public static void main(String[] args) {
        String[] input = {"interview", "internet", "internal", "interval"};

        // Call method to get result
        String result = longestCommonPrefix(input);

        // Print the longest common prefix
        System.out.println("Longest Common Prefix: " + result);

    }

    /// Time Complexity: O(N * log N + M), where N is the number of strings and M is the minimum length of a string. The sorting operation takes O(N * log N) time, and the comparison of characters in the first and last strings takes O(M) time.
    ///
    /// Space Complexity: O(M), as the ans variable can store the length of the prefix which in the worst case will be O(M).
    static String longestCommonPrefix(String[] v) {
        // To store the result prefix
        StringBuilder ans = new StringBuilder();

        // Sort the array of strings
        Arrays.sort(v);

        // First string after sorting
        String first = v[0];

        // Last string after sorting
        String last = v[v.length - 1];

        // Compare characters of the first and last strings
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            // Stop if characters are different
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }

            // Add matching character to result
            ans.append(first.charAt(i));
        }

        // Return the final common prefix
        return ans.toString();
    }
}