package striverAToZ.slidingWindowTwoPointer.mediumProbs;

public class LongestRepeatingCharacterReplacement {

    /// Question 4
    ///
    /// Problem Statement: Given an integer k and a string s, any character in the string can be selected and changed to any other uppercase English character. This operation can be performed up to k times. After completing these steps, return the length of the longest substring that contains the same letter.
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k));
        System.out.println(characterReplacementI(s, k));
        System.out.println(characterReplacementII(s, k));
        System.out.println(characterReplacementIII(s, k));

    }

    // brute

    /// Time Complexity:
    /// O(n² × 26), where n is the length of the input string.This is because for every possible substring (which takes O(n²) time), we compute the frequency of each character (which takes O(26) = O(1) time since there are only 26 uppercase English letters). So total time complexity becomes O(n² × 26), which simplifies to O(n²).
    ///
    /// Space Complexity:
    /// O(1), constant space.We use a fixed-size array of size 26 to store character frequencies for each substring. No additional space is used that grows with input size.
    static int characterReplacement(String s, int k) {

        // Variable to track the maximum valid substring length
        int maxLength = 0;

        // Outer loop to iterate through all starting indices
        for (int i = 0; i < s.length(); i++) {

            // Frequency array to store counts of each uppercase letter
            int[] freq = new int[26];

            // Variable to track the max frequency character in the current window
            int maxFreq = 0;

            // Inner loop to check substrings starting at i
            for (int j = i; j < s.length(); j++) {

                // Increase frequency of current character
                int currChar = freq[s.charAt(j) - 'A'];
                currChar++;

                // Update most frequent character count in window
                maxFreq = Math.max(maxFreq, currChar);

                // Current window size
                int windowSize = j - i + 1;

                // Calculate replacements needed to make all characters same
                int replacements = windowSize - maxFreq;

                // If replacements are within k, update maxLength
                if (replacements <= k) {
                    maxLength = Math.max(maxLength, windowSize);
                }
            }
        }

        return maxLength;
    }

    // better

    /// Time Complexity:
    /// O(N),We iterate through the entire string once using a sliding window. Each character is added and removed from the window at most once, resulting in linear time complexity relative to the length of the string (N).
    ///
    /// Space Complexity:
    /// O(26) ,We use a fixed-size frequency array or hashmap to store counts of uppercase English letters (which are 26 in total), so the space used remains constant regardless of the input size.
    static int characterReplacementI(String s, int k) {

        // Array to count frequency of characters in window
        int[] freq = new int[26];

        // Left pointer of sliding window
        int left = 0;

        // Tracks the highest frequency in the window
        int maxFreq = 0;

        // Stores result
        int maxLen = 0;

        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {

            // Increment count of current character
            int currChar = freq[s.charAt(right) - 'A'];
            currChar++;

            // Update max frequency in current window
            maxFreq = Math.max(maxFreq, currChar);

            // If number of changes exceeds k, shrink window
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update result with valid window length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // optimal

    /// Time Complexity:
    /// O(n), where n is the length of the string,each character is processed at most twice once by the right pointer, once by the left. All operations inside the loop run in constant time.
    ///
    /// Space Complexity:
    /// O(1), constant space .Only a fixed-size frequency array (26 letters) is used, regardless of input size.
    static int characterReplacementII(String s, int k) {
        // Frequency array for A-Z
        int[] freq = new int[26];

        // Left and right pointers of sliding window
        int left = 0, right = 0;

        // Tracks the count of the most frequent character in current window
        int maxCount = 0;

        // Stores the maximum length of valid window
        int maxLength = 0;

        // Iterate through the string with right pointer
        while (right < s.length()) {

            // Increment the frequency of current character
            int currChar = freq[s.charAt(right) - 'A'];
            currChar++;

            // Update maxCount with the max frequency seen so far
            maxCount = Math.max(maxCount, currChar);

            // If the current window needs more than k replacements, move left
            while ((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update the maximum window length
            maxLength = Math.max(maxLength, right - left + 1);

            // Move right pointer forward
            right++;
        }

        // Return the maximum valid window length
        return maxLength;
    }

    /// solution for
    ///
    /// [leetcode question](https://leetcode.com/problems/longest-repeating-character-replacement/description/)
    static int characterReplacementIII(String s, int k) {
        int[] freq = new int[26];

        int left = 0;
        int maxCount = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            // Increase frequency of current character
            freq[s.charAt(right) - 'A']++;

            // Highest frequency of any character in current window
            maxCount = Math.max(
                    maxCount,
                    freq[s.charAt(right) - 'A']
            );

            // Characters that need to be replaced
            while ((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update maximum valid window length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;

    }

}

