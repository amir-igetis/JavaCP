package LeetcodeInterview.multidimensionalDP;

import java.util.Arrays;

public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1'}};
        System.out.println(maximalSquare(matrix));
    }


    //    recursion
//    int maxSide = 0;

    static int maximalSquareI(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] maxSide = {0};  // wrapper array (mutable reference)
        solve(matrix, 0, 0, m, n, maxSide);
        return maxSide[0] * maxSide[0];
    }

    private static int solve(char[][] matrix, int i, int j, int m, int n, int[] maxSide) {
        if (i >= m || j >= n) return 0;

        int right = solve(matrix, i, j + 1, m, n, maxSide);
        int down = solve(matrix, i + 1, j, m, n, maxSide);
        int diagonal = solve(matrix, i + 1, j + 1, m, n, maxSide);

        int curr = 0;
        if (matrix[i][j] == '1') {
            curr = 1 + Math.min(right, Math.min(down, diagonal));
            maxSide[0] = Math.max(maxSide[0], curr);  // update wrapper
        }
        return curr;
    }

    //    recursion using memo
    static int maximalSquareII(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];   // memoization table
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int[] maxSide = {0}; // wrapper reference
        solve(matrix, 0, 0, m, n, dp, maxSide);
        return maxSide[0] * maxSide[0];
    }

    private static int solve(char[][] matrix, int i, int j, int m, int n,
                             int[][] dp, int[] maxSide) {
        if (i >= m || j >= n) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int right = solve(matrix, i, j + 1, m, n, dp, maxSide);
        int down = solve(matrix, i + 1, j, m, n, dp, maxSide);
        int diagonal = solve(matrix, i + 1, j + 1, m, n, dp, maxSide);

        int curr = 0;
        if (matrix[i][j] == '1') {
            curr = 1 + Math.min(right, Math.min(down, diagonal));
            maxSide[0] = Math.max(maxSide[0], curr); // update global max
        }

        return dp[i][j] = curr;
    }

    //    Top down DP
    static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int maxSide = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1; // first row/col just copy
                    } else {
                        dp[i][j] = 1 + Math.min(
                                Math.min(dp[i - 1][j], dp[i][j - 1]),
                                dp[i - 1][j - 1]
                        );
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
