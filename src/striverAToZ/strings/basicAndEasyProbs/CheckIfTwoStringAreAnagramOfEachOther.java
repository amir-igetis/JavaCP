package striverAToZ.strings.basicAndEasyProbs;

public class CheckIfTwoStringAreAnagramOfEachOther {
    /// Question 7
    ///
    /// Problem Statement: Given two strings, check if two strings are anagrams of each other or not
    public static void main(String[] args) {
        String Str1 = "INTEGER";
        String Str2 = "TEGERNI";

        // Check if the strings are anagrams and output the result
        if (CheckAnagrams(Str1, Str2)) {
            System.out.println("True");  // Output "True" if they are anagrams
        } else {
            System.out.println("False");  // Output "False" if they aren't anagrams
        }
    }

    // brute

    /// Time Complexity: O(N log N), where N is the length of the strings. This is due to the sorting step performed on both strings.
    ///
    /// Space Complexity: O(1), as the sorting is done in-place and no extra space proportional to input size is used (excluding the input strings themselves).
    // Solution class to check if two strings are anagrams
    static boolean CheckAnagrams(String str1, String str2) {
        // Case 1: when both of the strings have different lengths
        if (str1.length() != str2.length()) {
            return false;  // Strings can't be anagrams if lengths are different
        }

        // Convert strings to char arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        java.util.Arrays.sort(charArray1);
        java.util.Arrays.sort(charArray2);

        // Case 2: check if every character of str1 and str2 matches with each other
        for (int i = 0; i < str1.length(); i++) {
            if (charArray1[i] != charArray2[i]) {
                return false;  // If any character doesn't match, they aren't anagrams
            }
        }
        return true;
    }

    // optimal

    /// Time Complexity: O(N), where N is the length of the strings. Each string is traversed once, and the frequency array is checked in constant time (26 iterations).
    ///
    /// Space Complexity: O(1), as a fixed-size array of 26 elements is used regardless of the input size.
    // Solution class to check if two strings are anagrams
    static boolean CheckAnagramsI(String str1, String str2) {
        // Case: when both of the strings have different lengths
        if (str1.length() != str2.length())
            return false;

        // Initialize a frequency array to store character counts
        int[] freq = new int[26];

        // Count frequency of each character in str1
        for (int i = 0; i < str1.length(); i++) {
            freq[str1.charAt(i) - 'A']++;  // Increment frequency for each character in str1
        }

        // Decrement frequency for each character in str2
        for (int i = 0; i < str2.length(); i++) {
            freq[str2.charAt(i) - 'A']--;  // Decrement frequency for each character in str2
        }

        // Check if all frequencies are zero, meaning both strings have the same characters
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0)  // If any frequency is non-zero, they are not anagrams
                return false;
        }

        return true;  // The strings are anagrams
    }

}