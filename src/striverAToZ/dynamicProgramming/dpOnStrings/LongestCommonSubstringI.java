package striverAToZ.dynamicProgramming.dpOnStrings;

public class LongestCommonSubstringI {

    /// Problem Statement: Given two strings str1 and str2, find the length of their
    /// longest common substring.
    ///
    /// A substring is a contiguous sequence of characters within a string.

    public static void main(String[] args) {
        String s1 = "abcjklp";
        String s2 = "acjkp";

        System.out.println("The Length of Longest Common Substring is " +
                longestCommonSubstrI(s1, s2));
    }

    /// Time Complexity: O(n * m), where n is the length of str1 and m is the length of str2. This is because we are filling a 2D DP table of size (n+1) x (m+1).
    ///
    /// Space Complexity: O(n * m), where n is the length of str1 and m is the length of str2. This is due to the storage of the DP table, which requires O(n * m) space.

    static int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // DP table: dp[i][j] stores length of common substring ending at str1[i-1] and
        // str2[j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Variable to store the maximum substring length found so far
        int ans = 0;

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If characters match, extend the substring
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]); // Update maximum length
                } else {
                    dp[i][j] = 0; // Reset length when characters don't match
                }
            }
        }
        return ans; // Return the longest length found
    }

    /// Time Complexity: O(n * m), where n is the length of str1 and m is the length
    /// of str2. This is because we are iterating through both strings and filling a
    /// 2D DP table.
    ///
    /// Space Complexity: O(m), where m is the length of str2. We are using two arrays of size m+1 to store the current and previous row values.
    // space optimized
    static int longestCommonSubstrI(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // Arrays to store previous and current row values
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        // Variable to store the maximum LCS length found
        int ans = 0;

        // Loop through both strings
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // If characters match, extend the common substring
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    int val = 1 + prev[j - 1];
                    cur[j] = val;

                    // Update maximum substring length found so far
                    ans = Math.max(ans, val);
                }
                // If characters don't match, reset to 0
                else {
                    cur[j] = 0;
                }
            }

            // Move current row to previous for next iteration
            System.arraycopy(cur, 0, prev, 0, m + 1);
        }

        // Return the maximum length found
        return ans;
    }

}