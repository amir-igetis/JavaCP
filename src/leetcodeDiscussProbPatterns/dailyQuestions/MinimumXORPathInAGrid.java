package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinimumXORPathInAGrid {
    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {3, 4}};
        System.out.println(minCost(grid));
    }

    // DP

    /// Let m and n be the numbers of rows and columns in grid, respectively, and V be the number of possible XOR values.
    ///
    /// Time complexity: O(m⋅n⋅V).
    ///
    /// The outer two loops of the dynamic programming traversal take O(m⋅n) time. For each cell, we iterate over the V possible XOR values, taking O(V) time for the state transitions.
    ///
    /// Space complexity: O(n⋅V).
    ///
    /// There are O(m⋅n⋅V) states. With the rolling array optimization, dp only needs to maintain the states for one row, requiring O(n⋅V) space.
    static int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] dp = new boolean[n][1024];
        boolean[] next = new boolean[1024];

        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];
                Arrays.fill(next, false);

                for (int k = 0; k < 1024; k++) {
                    if (dp[j][k] || (j > 0 && dp[j - 1][k])) {
                        next[k ^ value] = true;
                    }
                }

                System.arraycopy(next, 0, dp[j], 0, 1024);
            }
        }

        for (int k = 0; k < 1024; k++) {
            if (dp[n - 1][k]) {
                return k;
            }
        }

        return -1;

    }
}
