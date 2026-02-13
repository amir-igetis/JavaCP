package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinScoreTriangulationOfPolygon {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        System.out.println(minScoreTriangulationII(values));
    }

    static int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n - 1];
    }

    static int minScoreTriangulationI(int[] values) {
        return solve(values, 0, values.length - 1);
    }

    private static int solve(int[] v, int i, int j) {
        if (j - i < 2) return 0; // less than 3 vertices → no triangle
        int min = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = v[i] * v[j] * v[k] + solve(v, i, k) + solve(v, k, j);
            min = Math.min(min, cost);
        }
        return min;
    }

    static int minScoreTriangulationII(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        return solveI(values, 0, n - 1, dp);
    }

    private static int solveI(int[] v, int i, int j, int[][] dp) {
        if (j - i < 2) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = v[i] * v[j] * v[k] + solveI(v, i, k, dp) + solveI(v, k, j, dp);
            min = Math.min(min, cost);
        }
        return dp[i][j] = min;
    }
}
