package striverAToZ.slidingWindowTwoPointer.mediumProbs;

public class NumOfSubstrContainingAllThreeChars {

    /// Question 7
    ///
    /// Problem Statement: Given a string s , consisting only of characters 'a' , 'b' , 'c'.Find the number of substrings that contain at least one occurrence of all these characters 'a' , 'b' , 'c'.
    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(numberOfSubstrings(s));

    }

    // brute

    /// Time Complexity: O(n²), where n is the length of the input string.We iterate through all possible starting indices from 0 to n−1, and for each starting index, we traverse the substring until we find a valid one (containing at least one 'a', 'b', and 'c'). In the worst case, the inner loop can run up to n times for each outer loop iteration, leading to a total of O(n²) operations.
    ///
    /// Space Complexity: O(1), constant space.We use a frequency map of fixed size (only for characters 'a', 'b', and 'c'). Regardless of input size, the space used remains constant. Hence, space complexity is O(1).
    static int numberOfSubstrings(String s) {
        // Variable to store final count
        int count = 0;
        // Length of the input string
        int n = s.length();

        // Outer loop to fix the start of the substring
        for (int i = 0; i < n; i++) {
            // Array to track the count of 'a', 'b', and 'c'
            int[] freq = new int[3];

            // Inner loop to fix the end of the substring
            for (int j = i; j < n; j++) {
                // Update frequency for current character
                freq[s.charAt(j) - 'a']++;

                // Check if all three characters are present
                if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                    // Add valid substring
                    count++;
                }
            }
        }

        return count;
    }

    // optimal

    /// Time Complexity:O(n) ,We traverse the string once with the right pointer and adjust the left pointer in a linear pass. Each character is processed at most twice (once by the right pointer and once by the left), resulting in linear time complexity.
    ///
    /// Space Complexity: O(1),We only use a constant-size frequency array for three characters ('a', 'b', 'c'), hence the space usage does not grow with input size.
    static int numberOfSubstringsI(String s) {
        // Frequency array for 'a', 'b', 'c'
        int[] freq = new int[3];

        // Left pointer for the sliding window
        int left = 0;

        // Result variable to store count of valid substrings
        int res = 0;

        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {
            // Increment frequency of current character
            freq[s.charAt(right) - 'a']++;

            // Shrink the window from the left while all characters are present
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                // Count substrings from current right to end
                res += (s.length() - right);

                // Move left pointer and update frequency
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return res;
    }
}
