package striverAToZ.strings.basicAndEasyProbs;

public class RotateString {

    /// Question 6
    ///
    /// Problem Statement: Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
    /// A shift on s consists of moving the leftmost character of s to the rightmost position. For example, if s = "abcde", then it will be "bcdea" after one shift.
    public static void main(String[] args) {

        String s1 = "rotation";
        String goal1 = "tionrota";
        System.out.println(rotateString(s1, goal1));

    }

    // brute

    /// Time Complexity: O(N^2) since generating N rotations and each comparison takes O(N) time.
    ///
    /// Space Complexity: O(N) for the space needed to store each rotated string.
    static boolean rotateString(String s, String goal) {
        // Strings must be same length to be rotations of each other
        if (s.length() != goal.length()) {
            return false;
        }

        // Try all possible rotations of s
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            if (rotated.equals(goal)) {
                // Return true if a match is found
                return true;
            }
        }

        return false;
    }

    // optimal

    /// Time Complexity: O(N), because checking for a substring in s + s is linear in time.
    ///
    /// Space Complexity: O(N) for the space needed to store the concatenated string s + s.
    static boolean rotateStringI(String s, String goal) {
        // Check if lengths of both strings are unequal
        if (s.length() != goal.length()) {
            // Return false if lengths don't match
            return false;
        }
        // Concatenate the string with itself
        String doubledS = s + s;
        // Check if the goal is a substring of the concatenated string
        return doubledS.contains(goal);
    }
}