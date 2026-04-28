package dpPrac.zeroOneKnapsack;

import java.util.Arrays;

public class CountSubsetWithTargetSum {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 1, 2};
        int k = 4;
        System.out.println(countSubsetII(arr, k));
    }

    // recursion
    static int countSubset(int[] arr, int k) {
        int n = arr.length;
        return knapsackFunc(arr, k, n);
    }

    private static int knapsackFunc(int[] arr, int k, int n) {
        if (k == 0)
            return 1;
        if (n == 0)
            return 0;
        if (arr[n - 1] <= k) {
            return knapsackFunc(arr, k - arr[n - 1], n - 1)
                    + knapsackFunc(arr, k, n - 1);
        } else {
            return knapsackFunc(arr, k, n - 1);
        }
    }

    // using memoization
    static int countSubsetII(int arr[], int k) {
        int n = arr.length;
        Integer[][] dp = new Integer[n + 1][k + 1];
        return solve(arr, k, n, dp);
    }

    private static int solve(int[] arr, int sum, int n, Integer[][] dp) {

        // base cases
        if (sum == 0) return 1;
        if (n == 0) return 0;

        if (dp[n][sum] != null)
            return dp[n][sum];

        if (arr[n - 1] <= sum)
            return dp[n][sum] =
                    solve(arr, sum - arr[n - 1], n - 1, dp) +
                            solve(arr, sum, n - 1, dp);
        else
            return dp[n][sum] =
                    solve(arr, sum, n - 1, dp);
    }


    // bottom down
    static int countSubsetI(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int j = 1; j <= k; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]]
                            + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][k];
    }
}
