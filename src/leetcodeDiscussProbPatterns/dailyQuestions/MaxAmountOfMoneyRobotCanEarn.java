package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxAmountOfMoneyRobotCanEarn {
    public static void main(String[] args) {
        int[][] coins = {{0, 1, -1}, {1, -2, 3}, {2, -3, 4}};
        System.out.println(maximumAmount(coins));
    }

    // Memoization Search tc & sc O(M * N)
    static int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] memo = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }

        return dfs(coins, memo, 0, 0, 2);
    }

    private static int dfs(int[][] coins, int[][][] memo, int i, int j, int k) {
        int m = coins.length;
        int n = coins[0].length;
        if (i >= m || j >= n) {
            return Integer.MIN_VALUE;
        }

        int x = coins[i][j];
        // arrive at the destination
        if (i == m - 1 && j == n - 1) {
            return k > 0 ? Math.max(0, x) : x;
        }

        if (memo[i][j][k] != Integer.MIN_VALUE) {
            return memo[i][j][k];
        }

        // not neutralize
        int res =
                Math.max(
                        dfs(coins, memo, i + 1, j, k),
                        dfs(coins, memo, i, j + 1, k)
                ) +
                        x;

        if (k > 0 && x < 0) {
            // neutralize
            res = Math.max(
                    res,
                    Math.max(
                            dfs(coins, memo, i + 1, j, k - 1),
                            dfs(coins, memo, i, j + 1, k - 1)
                    )
            );
        }

        memo[i][j][k] = res;
        return res;
    }

    // Dynamic Programming tc O(M * N) & sc O(M * N)
    static int maximumAmountI(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        int[][][] dp = new int[m][n][3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
            }
        }

        dp[0][0][0] = coins[0][0];
        for (int k = 1; k <= 2; k++) {
            dp[0][0][k] = Math.max(coins[0][0], 0);
        }

        for (int j = 1; j < n; j++) {
            dp[0][j][0] = dp[0][j - 1][0] + coins[0][j];
            for (int k = 1; k <= 2; k++) {
                dp[0][j][k] = Math.max(
                        dp[0][j - 1][k] + coins[0][j],
                        dp[0][j - 1][k - 1] + Math.max(coins[0][j], 0)
                );
            }
        }

        for (int i = 1; i < m; i++) {
            dp[i][0][0] = dp[i - 1][0][0] + coins[i][0];
            for (int k = 1; k <= 2; k++) {
                dp[i][0][k] = Math.max(
                        dp[i - 1][0][k] + coins[i][0],
                        dp[i - 1][0][k - 1] + Math.max(coins[i][0], 0)
                );
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j][0] =
                        Math.max(dp[i - 1][j][0], dp[i][j - 1][0]) + coins[i][j];

                for (int k = 1; k <= 2; k++) {
                    dp[i][j][k] = Math.max(
                            Math.max(dp[i - 1][j][k], dp[i][j - 1][k]) +
                                    coins[i][j],
                            Math.max(dp[i - 1][j][k - 1], dp[i][j - 1][k - 1])
                    );
                }
            }
        }

        return dp[m - 1][n - 1][2];
    }

    // Dynamic Programming tc O(M * N) & sc O(n)
    static int maximumAmountII(int[][] coins) {
        int n = coins[0].length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }

        for (int i = 0; i < 3; i++) {
            dp[1][i] = 0;
        }
        for (int[] row : coins) {
            for (int j = 1; j <= n; j++) {
                int x = row[j - 1];
                dp[j][2] = Math.max(
                        Math.max(dp[j - 1][2] + x, dp[j][2] + x),
                        Math.max(dp[j - 1][1], dp[j][1])
                );
                dp[j][1] = Math.max(
                        Math.max(dp[j - 1][1] + x, dp[j][1] + x),
                        Math.max(dp[j - 1][0], dp[j][0])
                );
                dp[j][0] = Math.max(dp[j - 1][0], dp[j][0]) + x;
            }
        }

        return dp[n][2];

    }

}
