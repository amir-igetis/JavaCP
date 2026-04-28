package dpPrac.zeroOneKnapsack;

import java.util.Arrays;

public class EqualSumPartitionProb {
    public static void main(String[] args) {
        int[] arr = {1, 5, 11, 5};
        System.out.println(equalPartitionII(arr));
    }

    // recursion
    static boolean equalPartition(int arr[]) {
        // code here
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];
        if (sum % 2 != 0)
            return false;
        else return knapsackFunc(arr, n, sum / 2);
    }

    private static boolean knapsackFunc(int[] arr, int n, int half) {
        if (half == 0) return true;
        if (n == 0) return false;
        if (arr[n - 1] <= half)
            return knapsackFunc(arr, n - 1, half - arr[n - 1]) ||
                    knapsackFunc(arr, n - 1, half);
        else return knapsackFunc(arr, n - 1, half);
    }


    // memoization
    static boolean equalPartitionI(int arr[]) {
        // code here

        // memoization
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];


        boolean[][] dp = new boolean[n + 1][(sum / 2) + 1];
        for (boolean[] i : dp)
            Arrays.fill(i, false);

        if (sum % 2 != 0)
            return false;
        else return knapsackFunc(arr, n, sum / 2, dp);
    }

    private static boolean knapsackFunc(int[] arr, int n, int half, boolean[][] dp) {
        if (half == 0) return true;
        if (n == 0) return false;
        if (dp[n][half] != false)
            return dp[n][half];
        if (arr[n - 1] <= half)
            return dp[n][half] = knapsackFunc(arr, n - 1, half - arr[n - 1], dp) ||
                    knapsackFunc(arr, n - 1, half, dp);
        else return dp[n][half] = knapsackFunc(arr, n - 1, half, dp);
    }


    // bottom up
    static boolean equalPartitionII(int arr[]) {
        // code here

        // memoization
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];


        boolean[][] dp = new boolean[n + 1][(sum / 2) + 1];
        for (boolean[] i : dp)
            Arrays.fill(i, false);

        if (sum % 2 != 0)
            return false;

        int half = sum / 2;

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        for (int j = 0; j <= half; j++)
            dp[0][j] = false;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= half; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] ||
                            dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][half];
    }

}
