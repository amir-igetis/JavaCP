package leetcodeDiscussProbPatterns.dailyQuestions;

public class TwistedMirrorPathCount {
    public static void main(String[] args) {
        int[][] grid = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        System.out.println(uniquePaths(grid));
        System.out.println(uniquePathsI(grid));
    }

    // DP
    static int uniquePaths(int[][] grid) {
        final int MOD = 1000000007;
        int m = grid.length;
        int n = grid[0].length;

        // f[i][j][0]: enter (i, j) by moving down
        // f[i][j][1]: enter (i, j) by moving right
        int[][][] f = new int[m][n][2];
        f[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (grid[i - 1][j] == 0) {
                        f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1]) % MOD;
                    } else {
                        f[i][j][0] = f[i - 1][j][1];
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1] == 0) {
                        f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1]) % MOD;
                    } else {
                        f[i][j][1] = f[i][j - 1][0];
                    }
                }
            }
        }

        return (f[m - 1][n - 1][0] + f[m - 1][n - 1][1]) % MOD;
    }

    // Rolling Array

    /// Let m and n be the numbers of rows and columns in the grid, respectively.
    ///
    /// Time complexity: O(mn).
    ///
    /// There are m×n cells, each maintaining 2 arrival states. Every state transition involves a constant number of operations (O(1)), leading to an overall time complexity of O(mn).
    ///
    /// Space complexity: O(mn) for the standard 3D dynamic programming approach, or O(n) if using the rolling array optimization.
    ///
    /// The standard dynamic programming table f stores two states for each cell, requiring O(mn) auxiliary space. The rolling array optimization maintains only an n×2 array across row iterations, reducing auxiliary space to O(n)
    static int uniquePathsI(int[][] grid) {
        final int MOD = 1000000007;
        int m = grid.length;
        int n = grid[0].length;

        // f[j][0]: enter cell (i, j) by moving down
        // f[j][1]: enter cell (i, j) by moving right
        long[][] f = new long[n][2];
        f[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (grid[i - 1][j] == 0) {
                        f[j][0] = (f[j][0] + f[j][1]) % MOD;
                    } else {
                        f[j][0] = f[j][1];
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1] == 0) {
                        f[j][1] = (f[j - 1][0] + f[j - 1][1]) % MOD;
                    } else {
                        f[j][1] = f[j - 1][0];
                    }
                }
            }
        }

        return (int) ((f[n - 1][0] + f[n - 1][1]) % MOD);
    }

}
