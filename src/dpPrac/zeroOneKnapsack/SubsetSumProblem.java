package dpPrac.zeroOneKnapsack;

import java.util.Arrays;

public class SubsetSumProblem {
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        System.out.println(isSubsetSum(arr, sum));
    }

    // using recursion

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n = arr.length;
        return knapsackFunc(arr, sum, n);
    }

    private static Boolean knapsackFunc(int[] arr, int sum, int n) {
        if (sum == 0)
            return true;
        if (n == 0) return false;
        if (arr[n - 1] <= sum)
            return knapsackFunc(arr, sum - arr[n - 1], n - 1) ||
                    knapsackFunc(arr, sum, n - 1);
        else return knapsackFunc(arr, sum, n - 1);
    }

    // using memoization
    static Boolean isSubsetSumI(int arr[], int sum) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] i : dp)
            Arrays.fill(i, -1);
        return knapsackFunc(arr, sum, n, dp);
    }

    private static Boolean knapsackFunc(int[] arr, int sum, int n, int[][] dp) {
        if (sum == 0)
            return true;
        if (n == 0) return false;
        if (dp[n][sum] != -1)
            return dp[n][sum] == 1;

        boolean result;

        if (arr[n - 1] <= sum)
            result = knapsackFunc(arr, sum - arr[n - 1], n - 1, dp) ||
                    knapsackFunc(arr, sum, n - 1, dp);
        else result = knapsackFunc(arr, sum, n - 1, dp);

        dp[n][sum] = result ? 1 : 0;

        return result;
    }

    // another way to do memoization

    static Boolean isSubsetSumII(int arr[], int sum) {
        // code here
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (boolean[] i : dp)
            Arrays.fill(i, false);
        return knapsackFuncI(arr, sum, n, dp);
    }

    private static Boolean knapsackFuncI(int[] arr, int sum, int n, boolean[][] dp) {
        if (sum == 0)
            return true;
        if (n == 0) return false;
        if (dp[n][sum] != false)
            return dp[n][sum] == true;


        if (arr[n - 1] <= sum)
            return dp[n][sum] = knapsackFuncI(arr, sum - arr[n - 1], n - 1, dp) ||
                    knapsackFuncI(arr, sum, n - 1, dp);
        else return dp[n][sum] = knapsackFuncI(arr, sum, n - 1, dp);

    }

    // top down dp
    static Boolean isSubsetSumIII(int arr[], int sum) {
        // code here

        // the following is the bottom up code
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (boolean[] i : dp)
            Arrays.fill(i, false);
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        for (int j = 1; j <= sum; j++)
            dp[0][j] = false;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] ||
                            dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][sum];

    }


}
