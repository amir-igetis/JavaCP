package striverAToZ.slidingWindowTwoPointer.mediumProbs;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingChars {

    /// Problem Statement: Given a string, S. Find the length of the longest substring without repeating characters.
    public static void main(String[] args) {
        String input = "cadbzabcd";
        int length = longestNonRepeatingSubstring(input);

        System.out.println("Length of longest substring without repeating characters: " + length);

    }

    // brute force

    /// Time Complexity: O(n^2), where n is the length of the string. This is because we are using a nested loop to check all possible substrings, leading to a quadratic time complexity.
    ///
    /// Space Complexity: O(1), as we are using a fixed-size hash array of size 256 (for extended ASCII characters) and not using any additional data structures that grow with input size.
    static int longestNonRepeatingSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        // Iterate through all possible starting points
        for (int i = 0; i < n; i++) {
            int[] hash = new int[256]; // For extended ASCII
            Arrays.fill(hash, 0);

            for (int j = i; j < n; j++) {
                if (hash[s.charAt(j)] == 1) break; // Found a repeat
                hash[s.charAt(j)] = 1;

                int len = j - i + 1;
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    /// Time Complexity: O(n), where n is the length of the string. This is because we are using a single pass through the string with two pointers, leading to linear time complexity.
    ///
    /// Space Complexity: O(1), as we are using a fixed-size hash array of size 256 (for ASCII characters) and not using any additional data structures that grow with input size.
    static int longestNonRepeatingSubstringI(String s) {
        int n = s.length();

        // Assuming all ASCII characters
        int HashLen = 256;

        /* Hash table to store last
           occurrence of each character */
        int[] hash = new int[HashLen];

        /* Initialize hash table with
           -1 (indicating no occurrence) */
        Arrays.fill(hash, -1);

        int l = 0, r = 0, maxLen = 0;
        while (r < n) {
            /* If current character s.charAt(r)
               is already in the substring */
            if (hash[s.charAt(r)] >= l) {
                /* Move left pointer to the right
                   of the last occurrence of s.charAt(r) */
                l = Math.max(hash[s.charAt(r)] + 1, l);
            }

            // Calculate the current substring length
            int len = r - l + 1;

            // Update maximum length found so far
            maxLen = Math.max(len, maxLen);

            /* Store the index of the current
               character in the hash table */
            hash[s.charAt(r)] = r;

            // Move right pointer to next position
            r++;
        }

        // Return the maximum length found
        return maxLen;
    }

}

