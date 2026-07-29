package striverAToZ.dynamicProgramming.twoDDpOnGrids;

import java.util.Arrays;

public class GridUniquePathsII {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };


        System.out.println("Number of paths with obstacles: " + uniquePathsWithObstacles(maze));

    }

    /// memoization,
    /// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is because we are filling up a 2D DP array of size m x n, and each cell takes constant time to compute.
    ///
    /// Space Complexity: O((N-1)+(M-1)) + O(M*N) We are using a recursion stack space: O((N-1)+(M-1)), here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.

    /* Function to find all unique paths to reach
        matrix[m-1][n-1] from matrix[0][0] with obstacles */
    static int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize DP table to memoize results
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        // Return the total number of paths
        return func(m - 1, n - 1, matrix, dp);
    }

    private static int func(int i, int j, int[][] matrix, int[][] dp) {
        // Base cases
        if (i < 0 || j < 0 || matrix[i][j] == 1) return 0;
        else if (i == 0 && j == 0) return 1;

        // If the result is already computed, return it
        if (dp[i][j] != -1) return dp[i][j];

        /* Calculate the number of ways by
        moving up and left recursively. */
        int up = func(i - 1, j, matrix, dp);
        int left = func(i, j - 1, matrix, dp);

        // Return the total ways
        return dp[i][j] = up + left;
    }

    /// tabulation,
    /// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is because we are filling up a 2D DP array of size m x n, and each cell takes constant time to compute.
    ///
    /// Space Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is due to the use of a 2D DP array to store intermediate results.
    // Public method to find unique paths
    static int uniquePathsWithObstaclesI(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;

        // DP table to store results
        int[][] dp = new int[n][m];

        return funcI(m, n, matrix, dp);
    }

    private static int funcI(int m, int n, int[][] matrix, int[][] dp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Base conditions
                if (matrix[i][j] == 1) {
                    // If there's an obstacle, no paths can pass through it
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    // Starting point has exactly one path
                    dp[i][j] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;

                // Check if we can move up and left
                if (i > 0)
                    up = dp[i - 1][j];
                if (j > 0)
                    left = dp[i][j - 1];

                // Sum of paths from above and left
                dp[i][j] = up + left;
            }
        }

        // The answer is at the bottom-right cell
        return dp[n - 1][m - 1];
    }

    /// space optimized
    ///
    /// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is because we are filling up a 2D DP array of size m x n, and each cell takes constant time to compute.
    ///
    /// Space Complexity: O(n), where n is the number of columns in the matrix. This is due to the use of two 1D arrays to store intermediate results, instead of a full 2D DP array.
    /* Function to find all unique paths to reach
        matrix[m-1][n-1] from matrix[0][0] with obstacles*/
    static int uniquePathsWithObstaclesII(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Return the total number of paths
        return funcII(m, n, matrix);
    }

    // Function to solve the problem using tabulation
    private static int funcII(int m, int n, int[][] matrix) {

        int[] prev = new int[n];
        int[] curr = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Base conditions
                if (matrix[i][j] == 1) {
                    /* If there's an obstacle at the
                    cell, no paths can pass through it*/
                    curr[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    /* If we are at the starting
                    point, there is one path to it*/
                    curr[j] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;

                /* Check if we can move up and left
                (if not at the edge of the maze)*/
                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = curr[j - 1];

                /* Total number of paths to reach (i, j)
                is the sum of paths from above and left*/
                curr[j] = up + left;
            }

            prev = curr.clone();
        }

        /* The final result is stored in dp[m-1][n-1],
        which represents the destination*/
        return prev[n - 1];
    }
}
