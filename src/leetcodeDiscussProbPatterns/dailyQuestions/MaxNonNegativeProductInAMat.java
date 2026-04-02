package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxNonNegativeProductInAMat {
    public static void main(String[] args) {
        int[][] grid = {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        System.out.println(maxProductPath(grid));
    }

    static int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int MOD = (int) 1e9 + 7;

        long[][] mx = new long[m][n];
        long[][] mn = new long[m][n];

        mx[0][0] = mn[0][0] = grid[0][0];

        // initialize first column
        for (int i = 1; i < m; i++) {
            mx[i][0] = mx[i - 1][0] * grid[i][0];
            mn[i][0] = mx[i][0];
        }

        // initialize first row
        for (int j = 1; j < n; j++) {
            mx[0][j] = mx[0][j - 1] * grid[0][j];
            mn[0][j] = mx[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (grid[i][j] < 0) {
                    mx[i][j] = Math.min(mn[i - 1][j], mn[i][j - 1]) * grid[i][j];
                    mn[i][j] = Math.max(mx[i - 1][j], mx[i][j - 1]) * grid[i][j];
                } else {
                    mx[i][j] = Math.max(mx[i - 1][j], mx[i][j - 1]) * grid[i][j];
                    mn[i][j] = Math.min(mn[i - 1][j], mn[i][j - 1]) * grid[i][j];
                }
            }
        }

        long result = mx[m - 1][n - 1];

        if (result < 0) return -1;

        return (int) (result % MOD);
    }
}
