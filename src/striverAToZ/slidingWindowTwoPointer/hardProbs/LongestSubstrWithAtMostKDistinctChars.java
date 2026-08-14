package striverAToZ.slidingWindowTwoPointer.hardProbs;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithAtMostKDistinctChars {

    /// Question 1
    /// Problem Statement: Given a string s and an integer k.Find the length of the longest substring with at most k distinct characters
    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;

        System.out.println(lengthOfLongestSubstringKDistinct(s, k));

    }

    // brute

    /// Time Complexity:O(n²) ,We are checking every possible substring which takes, and for each substring, we count distinct characters using a map/set which takes up to O(n) in the worst case. But since we break early when distinct characters exceed K, the inner loop doesn't always go to the end.Hence worst-case complexity remains O(n²).
    ///
    /// Space Complexity:O(k) ,We use a hash map to store character frequencies for each substring, and in the worst case, it stores at most k distinct characters.
    static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Store the maximum length of valid substring
        int maxLength = 0;

        // Try every possible starting index
        for (int i = 0; i < s.length(); i++) {
            // Use map to store character frequencies
            Map<Character, Integer> freq = new HashMap<>();

            // Try all possible substrings starting at i
            for (int j = i; j < s.length(); j++) {
                // Add or update frequency
                freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);

                // If distinct chars exceed k, stop expanding
                if (freq.size() > k) break;

                // Update maxLength
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        // Return final answer
        return maxLength;
    }

    // optimal

    /// Time Complexity:O(n) ,We iterate through the string once, and each character is added and removed from the map at most once. So the overall time complexity is linear.
    ///
    /// Space Complexity: O(k) ,We store at most k characters in the frequency map at any given time, so space used is proportional to k.
    static int lengthOfLongestSubstringKDistinctI(String s, int k) {
        // Edge case
        if (k == 0 || s.length() == 0) return 0;

        // Frequency map to track characters
        Map<Character, Integer> freq = new HashMap<>();

        // Initialize sliding window pointers
        int left = 0;
        int maxLen = 0;

        // Loop through string
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // Shrink window if more than k distinct chars
            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }

            // Update maxLen
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
