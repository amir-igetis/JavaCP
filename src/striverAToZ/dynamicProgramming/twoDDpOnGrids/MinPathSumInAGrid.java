package striverAToZ.dynamicProgramming.twoDDpOnGrids;

import java.util.Arrays;

public class MinPathSumInAGrid {
    public static void main(String[] args) {
        int[][] grid = {
                {5, 9, 6},
                {11, 5, 2}
        };
        System.out.println("Minimum sum path: "
                + minPathSum(grid));
    }


    /// memoization
    /// Time Complexity: O(N*M), at max, there will be N*M calls of recursion..
    /// Space Complexity: O(M+N) + O(N*M), additional space for recursion stack and memo table.
// Main function that initializes dp and calls helper
    static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // Create dp table initialized with -1
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start from bottom-right corner
        return minPath(n - 1, m - 1, grid, dp);
    }

    private static int minPath(int i, int j,
                               int[][] grid, int[][] dp) {

        // If we are at (0,0), return that cell's value
        if (i == 0 && j == 0)
            return grid[0][0];

        // If out of bounds, return large number
        if (i < 0 || j < 0)
            return (int) 1e9;

        // If already computed, return from dp
        if (dp[i][j] != -1)
            return dp[i][j];

        // Compute path by going up
        int up = grid[i][j] +
                minPath(i - 1, j, grid, dp);

        // Compute path by going left
        int left = grid[i][j] +
                minPath(i, j - 1, grid, dp);

        // Store the minimum in dp and return
        return dp[i][j] = Math.min(up, left);
    }

    /// tabulation
    ///  Time Complexity: O(N*M), entire grid is visited atleast once.
    /// Space Complexity: O(N*M), space used for DP array.
    static int minPathSumI(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Create DP table
        int[][] dp = new int[n][m];

        // Fill the table
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // First cell initialization
                if (i == 0 && j == 0)
                    dp[i][j] = matrix[i][j];
                else {
                    // Calculate from top
                    int up = matrix[i][j];
                    if (i > 0) up += dp[i - 1][j];
                    else up += (int) 1e9;

                    // Calculate from left
                    int left = matrix[i][j];
                    if (j > 0) left += dp[i][j - 1];
                    else left += (int) 1e9;

                    // Take minimum
                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        // Return result
        return dp[n - 1][m - 1];
    }

    /// space optimized
    /// Time Complexity: O(N*M), every element of grid is visited atleast once.
    /// Space Complexity: O(N), we only use one array for storing rows.
    static int minSumPathII(int n, int m, int[][] matrix) {

        // Create 1D array for previous row
        int[] prev = new int[m];

        // Loop through each row
        for (int i = 0; i < n; i++) {

            // Create temp array for current row
            int[] temp = new int[m];

            // Loop through each column
            for (int j = 0; j < m; j++) {

                // If at the start cell
                if (i == 0 && j == 0) {
                    temp[j] = matrix[i][j];
                } else {

                    // Take up direction if valid
                    int up = matrix[i][j];
                    if (i > 0)
                        up += prev[j];
                    else
                        up += (int) 1e9;

                    // Take left direction if valid
                    int left = matrix[i][j];
                    if (j > 0)
                        left += temp[j - 1];
                    else
                        left += (int) 1e9;

                    // Take minimum of both directions
                    temp[j] = Math.min(up, left);
                }
            }

            // Move current row to previous
            prev = temp;
        }

        // Return result at destination
        return prev[m - 1];
    }
}

