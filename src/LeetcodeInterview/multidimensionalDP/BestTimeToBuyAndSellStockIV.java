package LeetcodeInterview.multidimensionalDP;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV {
    public static void main(String[] args) {
        int k = 2;
        int[] prices = {2, 4, 2};
        System.out.println(maxProfit(k, prices));
    }

    //    recursion
    static int maxProfitI(int k, int[] prices) {
        return helper(0, 1, k, prices);
    }

    private static int helper(int ind, int buy, int cap, int[] prices) {
        if (ind == prices.length || cap == 0) return 0;

        if (buy == 1) {
            // Choice: buy or skip
            int take = -prices[ind] + helper(ind + 1, 0, cap, prices);
            int skip = helper(ind + 1, 1, cap, prices);
            return Math.max(take, skip);
        } else {
            // Choice: sell or skip
            int sell = prices[ind] + helper(ind + 1, 1, cap - 1, prices);
            int skip = helper(ind + 1, 0, cap, prices);
            return Math.max(sell, skip);
        }
    }

    //    recursion and memoization
    static int maxProfitII(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int b = 0; b < 2; b++) {
                Arrays.fill(dp[i][b], -1);
            }
        }
        return helper(0, 1, k, prices, dp);
    }

    private static int helper(int ind, int buy, int cap, int[] prices, int[][][] dp) {
        if (ind == prices.length || cap == 0) return 0;

        if (dp[ind][buy][cap] != -1) return dp[ind][buy][cap];

        int profit;
        if (buy == 1) {
            profit = Math.max(
                    -prices[ind] + helper(ind + 1, 0, cap, prices, dp), // buy
                    helper(ind + 1, 1, cap, prices, dp)                 // skip
            );
        } else {
            profit = Math.max(
                    prices[ind] + helper(ind + 1, 1, cap - 1, prices, dp), // sell
                    helper(ind + 1, 0, cap, prices, dp)                     // skip
            );
        }

        return dp[ind][buy][cap] = profit;
    }

    //    Top down DP
    static int maxProfit(int k, int[] prices) {
        int profit = 0;
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int maxV = Integer.MIN_VALUE;
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = dp[i][j - 1];
                if (dp[i - 1][j - 1] - prices[j - 1] > maxV)
                    maxV = dp[i - 1][j - 1] - prices[j - 1];

                dp[i][j] = Math.max(dp[i][j], maxV + prices[j]);
            }
        }

        return dp[k][prices.length - 1];
    }
}
