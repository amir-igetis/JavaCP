package dpPrac.unbounedKnapsack;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        int W = 100;
        int[] wt = {1, 50}, val = {1, 30};
        System.out.println(unboundedKnapsack(wt, val, W));
    }

    static int unboundedKnapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;

        for (int j = 0; j <= W; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]],
                            dp[i - 1][j]);
                }
            }
        }

        return dp[n][W];
    }
}
