package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindAllPossibleStableBinaryArr {
    public static void main(String[] args) {
        int zero = 1, one = 1, limit = 2;
        System.out.println(numberOfStableArrays(zero, one, limit));
    }

    // dp tc O(N ^ 3) & sc O(zero * one)
    static int numberOfStableArrays(int zero, int one, int limit) {
        final int mod = 1_000_000_007;
        int[][] dp0 = new int[zero + 1][one + 1]; // i 0s + j 1s ending with 0
        int[][] dp1 = new int[zero + 1][one + 1]; // i 0s + j 1s ending with 1

        // Base cases: only zeros or only ones => only one string if len <= min(limit, zero/one)
        for (int i = 1; i <= Math.min(zero, limit); ++i) dp0[i][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); ++j) dp1[0][j] = 1;

        // DP iterations
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                for (int k = 1; k <= Math.min(limit, i); ++k)
                    dp0[i][j] = (dp0[i][j] + dp1[i - k][j]) % mod;
                for (int k = 1; k <= Math.min(limit, j); ++k)
                    dp1[i][j] = (dp1[i][j] + dp0[i][j - k]) % mod;
            }
        }

        return (dp0[zero][one] + dp1[zero][one]) % mod;
    }

    // dp with window transition tc and sc O(zero * one)
    static int numberOfStableArraysI(int zero, int one, int limit) {
        final int mod = 1_000_000_007;
        int L = limit + 1;

        int[][] dp0 = new int[zero + 1][one + 1]; // i 0s + j 1s ending with 0
        int[][] dp1 = new int[zero + 1][one + 1]; // i 0s + j 1s ending with 1

        // Base cases: only zeros or only ones => only one string if len <= min(limit, zero/one)
        for (int i = 1; i <= Math.min(zero, limit); ++i) dp0[i][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); ++j) dp1[0][j] = 1;

        // DP iterations
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                dp0[i][j] = (dp0[i - 1][j] + dp1[i - 1][j] - (i >= L ? dp1[i - L][j] : 0)) % mod;
                dp1[i][j] = (dp1[i][j - 1] + dp0[i][j - 1] - (j >= L ? dp0[i][j - L] : 0)) % mod;

                // Fix negatives
                dp0[i][j] = (dp0[i][j] + mod) % mod;
                dp1[i][j] = (dp1[i][j] + mod) % mod;
            }
        }

        return (dp0[zero][one] + dp1[zero][one]) % mod;
    }
}
