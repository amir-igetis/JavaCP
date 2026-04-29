package dpPrac.unbounedKnapsack;

public class RodCutting {
    public static void main(String[] args) {
        int[] len = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] price = {1, 5, 8, 9, 10, 17, 18, 20};
        System.out.println(rodCutting(len, price));
    }

    static int rodCutting(int[] len, int[] prices) {
        int n = len.length;
        int W = prices.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;
        for (int j = 0; j <= W; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (len[i - 1] <= j)
                    dp[i][j] = Math.max(prices[i - 1] + dp[i][j - len[i - 1]],
                            dp[i - 1][j]);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[n][W];
    }
}
