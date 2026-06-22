package leetcodeContest.biWeekly185;

import java.util.Arrays;

public class CountGoodIntegersInARange {
    public static void main(String[] args) {
        // Example 1
        long l1 = 10;
        long r1 = 15;
        int k1 = 1;
        System.out.println("Good integers in range [" + l1 + ", " + r1 + "] with k=" + k1 + " : " +
                countGoodIntegers(l1, r1, k1));
        // Expected Output: 3

        // Example 2
        long l2 = 201;
        long r2 = 204;
        int k2 = 2;
        System.out.println("Good integers in range [" + l2 + ", " + r2 + "] with k=" + k2 + " : " +
                countGoodIntegers(l2, r2, k2));
        // Expected Output: 2

        System.out.println(countGoodIntegers(100000000000000L, 1000000000000000L, 9));
    }

    static long countGoodIntegers(long l, long r, int k) {
        // Find the count of good integers in range [0, r]
        // and subtract the count in range [0, l - 1]
        return countValid(String.valueOf(r), k) - countValid(String.valueOf(l - 1), k);
    }

    // Helper function to setup DP for a specific upper bound
    private static long countValid(String limitStr, int k) {
        int n = limitStr.length();

        // 4D DP Array to memoize overlapping subproblems
        // State: [index][prevDigit + 1][isTight][isLeadingZero]
        // prevDigit + 1 is used because prevDigit can be -1 (mapped to 0)
        long[][][][] dp = new long[n][11][2][2];

        for (long[][][] a : dp) {
            for (long[][] b : a) {
                for (long[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }

        // Start DFS: index 0, no prev digit (-1), tight is true (1), leading zero is true (1)
        return dfs(0, -1, 1, 1, limitStr, k, dp);
    }

    // Recursive Digit DP function
    private static long dfs(int idx, int prevDigit, int isTight, int isLeadingZero, String limitStr, int k, long[][][][] dp) {
        // Base Case: If we have placed all digits, we found 1 valid number
        if (idx == limitStr.length()) {
            return 1;
        }

        // Return memoized result if already computed
        if (dp[idx][prevDigit + 1][isTight][isLeadingZero] != -1) {
            return dp[idx][prevDigit + 1][isTight][isLeadingZero];
        }

        // Find the maximum digit we can place at the current position
        int upperBound = (isTight == 1) ? (limitStr.charAt(idx) - '0') : 9;
        long totalValid = 0;

        // Try placing all valid digits from 0 to upperBound
        for (int dig = 0; dig <= upperBound; dig++) {

            // Calculate next states
            int nextTight = (isTight == 1 && dig == upperBound) ? 1 : 0;
            int nextLeadingZero = (isLeadingZero == 1 && dig == 0) ? 1 : 0;

            if (isLeadingZero == 1) {
                // If we are still placing leading zeros, the actual number hasn't started yet.
                // We pass the new digit as prevDigit ONLY if it's not a leading zero.
                int nextPrev = (dig == 0) ? -1 : dig;
                totalValid += dfs(idx + 1, nextPrev, nextTight, nextLeadingZero, limitStr, k, dp);
            } else {
                // If the number has started, we MUST respect the absolute difference constraint
                if (Math.abs(dig - prevDigit) <= k) {
                    totalValid += dfs(idx + 1, dig, nextTight, nextLeadingZero, limitStr, k, dp);
                }
            }
        }

        // Store and return the computed answer for this state
        return dp[idx][prevDigit + 1][isTight][isLeadingZero] = totalValid;
    }

}
