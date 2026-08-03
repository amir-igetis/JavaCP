package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class MinCoins {

    ///  Problem Statement: Given an integer array of coins representing coins of different denominations and an
    /// integer amount representing a total amount of money. Return the fewest number of coins that are needed to
    /// make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    /// There are infinite numbers of coins of each type

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(coinChange(coins, amount));
    }

    /// Time Complexity: O(N*T), there are total of N*T states.
    /// Space Complexity: O(N*T) + O(N), additonal space used to for memo table and recursion stack.

    // memoization
    static int coinChange(int[] coins, int amount) {
        // Creating dp array initialized with -2
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);

        // Calling helper function
        return helper(coins, amount, dp);
    }

    // Helper recursive function
    private static int helper(int[] coins, int rem, int[] dp) {
        // If remaining amount is zero
        if (rem == 0) return 0;

        // If remaining amount is negative
        if (rem < 0) return -1;

        // If already computed
        if (dp[rem] != -2) return dp[rem];

        // Initialize minimum with large value
        int mini = Integer.MAX_VALUE;

        // Try every coin
        for (int coin : coins) {
            // Recursive call
            int res = helper(coins, rem - coin, dp);

            // If result is valid
            if (res >= 0 && res < mini)
                mini = 1 + res;
        }

        // Store result in dp
        dp[rem] = (mini == Integer.MAX_VALUE) ? -1 : mini;
        return dp[rem];
    }

    /// Time Complexity: O(N*T), there are total of N*T states.
    /// Space Complexity: O(N*T), additonal space used to for memo table.

    // tabulation
    static int coinChangeI(int[] coins, int amount) {
        // Creating dp array of size amount+1
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // Base case: dp[0] = 0
        dp[0] = 0;

        // Loop through all amounts from 1 to amount
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                // If coin can be used
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    // Update dp[i] with minimum coins
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If dp[amount] is still infinity, return -1
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /// Time Complexity: O(N*T), there are total of N*T states.
    /// Space Complexity: O(T), additonal space used to for storing rows.

    // space optimized
    static int minimumElementsII(int[] arr, int T) {
        int n = arr.length;

        // Create two arrays for space optimization
        int[] prev = new int[T + 1];
        int[] cur = new int[T + 1];

        // Initialize the base case for the first element
        for (int i = 0; i <= T; i++) {
            if (i % arr[0] == 0)
                prev[i] = i / arr[0];
            else
                prev[i] = (int) 1e9;
        }

        // Loop through the rest of the elements
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= T; target++) {

                // Option 1: Do not take current element
                int notTake = prev[target];

                // Option 2: Take current element if possible
                int take = (int) 1e9;
                if (arr[ind] <= target)
                    take = 1 + cur[target - arr[ind]];

                // Store the minimum of both choices
                cur[target] = Math.min(notTake, take);
            }

            // Update previous row for next iteration
            prev = cur.clone();
        }

        // Extract the final answer from DP
        int ans = prev[T];
        if (ans >= 1e9)
            return -1;
        return ans;
    }
}

