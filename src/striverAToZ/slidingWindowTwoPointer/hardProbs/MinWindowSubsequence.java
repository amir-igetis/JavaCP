package striverAToZ.slidingWindowTwoPointer.hardProbs;

public class MinWindowSubsequence {

    /// Question 4
    ///
    public static void main(String[] args) {
        String str1 = "geeksforgeeks";
        String str2 = "eksrg";
        System.out.println(minWindow(str1, str2));
    }

    // not the solution, solve the problem. Also solve leetcode 727
    static String minWindow(String str1, String str2) {
        int s = str1.length();
        int t = str2.length();
        int[][] dp = new int[s + 1][t + 1];
        // vector<vector<int>> dp(s, vector<int>(t, -1));
        for (int i = 0; i < s; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = i;
            } else {
                if (i != 0) {
                    dp[i][0] = dp[i - 1][0];
                }
            }
        }
        for (int i = 1; i < s; i++) {
            for (int j = 1; j < t; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int begin = -1, length = Integer.MAX_VALUE;
        for (int i = 0; i < s; i++) {
            int index = dp[i][t - 1];
            if (index != -1) {
                int curLength = i - index + 1;
                if (curLength < length) {
                    begin = index;
                    length = curLength;
                }
            }
        }
        if (begin == -1)
            return "";
        return str1.substring(begin, length);
    }
}
