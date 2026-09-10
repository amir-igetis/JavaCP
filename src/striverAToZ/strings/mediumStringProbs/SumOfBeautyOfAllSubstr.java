package striverAToZ.strings.mediumStringProbs;

import java.util.HashMap;
import java.util.Map;

public class SumOfBeautyOfAllSubstr {

    ///  Question 7
    ///
    /// Problem Statement: The beauty of a string is defined as the difference between the frequency of the most frequent character and the least frequent character (excluding characters that do not appear) in that string.
    ///
    /// Given a string s, return the sum of beauty values of all possible substrings of s.
    public static void main(String[] args) {
        String s = "xyx";
        System.out.println("Beauty Sum: " + beautySum(s));

    }

    /// Time Complexity:
    ///
    /// Outer loop: O(n) (for each starting index)
    /// Inner loop: O(n) (for each ending index)
    /// Computing max and min for frequencies: O(26) in the worst case (since only lowercase letters), O(n^2 * 26) ≈ O(n^2) because 26 is constant.
    ///
    /// Space Complexity:
    /// Frequency map uses at most 26 characters → O(26) = O(1).
    /// No extra data structures apart from that.
    static int beautySum(String s) {
        int n = s.length();
        int sum = 0;

        // Loop through all substrings
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> freq = new HashMap<>();

            for (int j = i; j < n; j++) {
                // Increase character frequency
                freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);

                int maxi = Integer.MIN_VALUE;
                int mini = Integer.MAX_VALUE;

                // Calculate max and min frequency
                for (int val : freq.values()) {
                    mini = Math.min(mini, val);
                    maxi = Math.max(maxi, val);
                }

                // Add to sum
                sum += (maxi - mini);
            }
        }

        return sum;
    }
}

