package striverAToZ.strings.mediumStringProbs;

public class MaxNestingDepthOfParenthesis {

    /// Question 2
    ///
    /// Problem Statement: Given a valid parentheses string s, return the nesting depth of s. The nesting depth is the maximum number of nested parentheses.
    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        int result = maxDepth(s);
        System.out.println("Max Depth: " + result);
    }

    /// Time Complexity: O(n), where n is the length of the string.
    ///
    /// Space Complexity: O(1), as only constant extra space is used.
    static int maxDepth(String s) {
        int p = 0;
        int ans = 0;
        for (char ch : s.toCharArray()) {
            // Increase depth on open parenthesis
            if (ch == '(') p++;
                // Decrease depth on close parenthesis
            else if (ch == ')') p--;
            // Update maximum depth encountered
            ans = Math.max(ans, p);
        }
        return ans;
    }
}
