package leetcodeDiscussProbPatterns.dailyQuestions;

public class BestTimeToBuyAndSellStockV {
    public static void main(String[] args) {
        int[] prices = {12, 16, 19, 19, 8, 1, 19, 13, 9};
        int k = 3;
        System.out.println(maximumProfit(prices, k));
    }

    //    dynamic programming
    static long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][][] dp = new long[n][k + 1][3];

        for (int j = 1; j <= k; j++) {
            dp[0][j][1] = -prices[0];
            dp[0][j][2] = prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(
                        dp[i - 1][j][0],
                        Math.max(
                                dp[i - 1][j][1] + prices[i],
                                dp[i - 1][j][2] - prices[i]
                        )
                );
                dp[i][j][1] = Math.max(
                        dp[i - 1][j][1],
                        dp[i - 1][j - 1][0] - prices[i]
                );
                dp[i][j][2] = Math.max(
                        dp[i - 1][j][2],
                        dp[i - 1][j - 1][0] + prices[i]
                );
            }
        }
        return dp[n - 1][k][0];
    }

    /*
     * We observe that the optimal state on day i depends only on the optimal
     * state on day i−1, and is unrelated to the optimal states on earlier days. At
     * this point, we can use a rolling array, retaining only the optimal state of the
     *  previous day, thereby reducing the space complexity to O(k). During actual
     *  traversal, we can calculate sequentially in the order of j from large to small,
     *  without the need to create temporary variables.
     * */

//    slight optimized

    static long maximumProfitI(int[] prices, int k) {
        int n = prices.length;
        long[][] dp = new long[k + 1][3];
        // initialize the state on day 0
        for (int j = 1; j <= k; j++) {
            dp[j][1] = -prices[0];
            dp[j][2] = prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = k; j > 0; j--) {
                dp[j][0] = Math.max(
                        dp[j][0],
                        Math.max(dp[j][1] + prices[i], dp[j][2] - prices[i])
                );
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
                dp[j][2] = Math.max(dp[j][2], dp[j - 1][0] + prices[i]);
            }
        }

        return dp[k][0];
    }

//     from gpt

    static long maximumProfitII(int[] prices, int k) {
        long NEG = Long.MIN_VALUE / 4;

        long[] flat = new long[k + 1];
        long[] longPos = new long[k + 1];
        long[] shortPos = new long[k + 1];

        // initialization
        for (int i = 0; i <= k; i++) {
            flat[i] = 0;
            longPos[i] = NEG;
            shortPos[i] = NEG;
        }

        for (int price : prices) {
            long[] newFlat = flat.clone();
            long[] newLong = longPos.clone();
            long[] newShort = shortPos.clone();

            for (int t = 0; t <= k; t++) {
                // open positions
                newLong[t] = Math.max(newLong[t], flat[t] - price);
                newShort[t] = Math.max(newShort[t], flat[t] + price);

                // close positions
                if (t + 1 <= k) {
                    newFlat[t + 1] = Math.max(newFlat[t + 1], longPos[t] + price);
                    newFlat[t + 1] = Math.max(newFlat[t + 1], shortPos[t] - price);
                }
            }

            flat = newFlat;
            longPos = newLong;
            shortPos = newShort;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, flat[t]);
        }
        return ans;
    }
}

