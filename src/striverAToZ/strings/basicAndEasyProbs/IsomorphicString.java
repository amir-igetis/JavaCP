package striverAToZ.strings.basicAndEasyProbs;

public class IsomorphicString {

    /// Question 5
    ///
    /// Problem Statement: Given two strings s and t, determine if they are isomorphic. Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    /// All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
    public static void main(String[] args) {
// Define input strings
        String s = "paper";
        String t = "title";

        // Check if strings are isomorphic
        if (isomorphicString(s, t)) {
            System.out.println("Strings are isomorphic.");
        } else {
            System.out.println("Strings are not isomorphic.");
        }
    }

    /// Time Complexity: O(N) where N is the length of the input strings, due to the single loop iterating through each character.
    ///
    /// Space Complexity: O(1) since the space used by the arrays is constant (256 fixed size) regardless of input size
    static boolean isomorphicString(String s, String t) {
        // Arrays to track last seen positions of characters in s and t
        int[] m1 = new int[256], m2 = new int[256];

        // Get length of the strings
        int n = s.length();

        // Loop through all characters in the strings
        for (int i = 0; i < n; ++i) {
            // Return false if mapping is inconsistent
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;

            // Update last seen index for both characters
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }

        // Return true if all character mappings are consistent
        return true;
    }
}