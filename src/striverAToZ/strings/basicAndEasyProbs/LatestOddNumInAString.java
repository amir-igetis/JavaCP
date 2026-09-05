package striverAToZ.strings.basicAndEasyProbs;

public class LatestOddNumInAString {

    ///  Question 3
    /// Problem Statement: Given a string s, representing a large integer, the task is to return the largest-valued odd integer (as a string) that is a substring of the given string s.
    /// The number returned should not have leading zero's. But the given input string may have leading zero.
    public static void main(String[] args) {
        String num = "504";
        String result = largeOddNum(num);
        System.out.println("Largest odd number: " + result);

    }

    /// Time Complexity: O(N), since the loop runs once through the string of length N.
    ///
    /// Space Complexity: O(1), as we are using only a constant amount of extra space
    static String largeOddNum(String s) {
        int ind = -1;

        // Find the last odd digit in the string
        int i;
        for (i = s.length() - 1; i >= 0; i--) {
            if ((s.charAt(i) - '0') % 2 == 1) {
                ind = i;
                break;
            }
        }

        // Return empty string if no odd digit was found
        if (ind == -1) return "";

        // Skip leading zeroes up to the odd digit
        i = 0;
        while (i <= ind && s.charAt(i) == '0') i++;

        // Return substring from first non-zero to odd digit
        return s.substring(i, ind + 1);
    }
}