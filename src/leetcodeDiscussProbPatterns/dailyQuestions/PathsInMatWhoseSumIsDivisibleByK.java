package leetcodeDiscussProbPatterns.dailyQuestions;

public class PathsInMatWhoseSumIsDivisibleByK {
    public static void main(String[] args) {
        int[][] grid = {{5, 2, 4}, {3, 0, 5}, {0, 7, 2}};
        int k = 3;
        System.out.println(numberOfPaths(grid, k));
    }

    static int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        final int MOD = 1_000_000_007;

        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    int currWays = dp[i][j][r];
                    if (currWays == 0)
                        continue;

                    if (i + 1 < m) {
                        int newR = (r + grid[i + 1][j]) % k;
                        dp[i + 1][j][newR] = (dp[i + 1][j][newR] + currWays) % MOD;
                    }

                    if (j + 1 < n) {
                        int newR = (r + grid[i][j + 1]) % k;
                        dp[i][j + 1][newR] = (dp[i][j + 1][newR] + currWays) % MOD;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];


    }
}
