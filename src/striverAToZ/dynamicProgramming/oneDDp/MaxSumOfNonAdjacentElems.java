package striverAToZ.dynamicProgramming.oneDDp;

import java.util.Arrays;

public class MaxSumOfNonAdjacentElems {

    /// Problem Statement: Given an array of N positive integers,
    /// we need to return the maximum sum of the subsequence such that no two elements of the subsequence are
    /// adjacent elements in the array.
    ///
    /// Note: A subsequence of an array is a list with elements of the array where some elements are deleted
    ///  (or not deleted at all) and the elements should be in the same order in the subsequence as in the array.

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};
        // Print result
        System.out.println(maximumNonAdjacentSum(arr));
    }


    /// memoization tc O(N) sc O(n + n)
    // Main function to compute result
    static int maximumNonAdjacentSum(int[] arr) {
        int n = arr.length;

        // Initialize DP array
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        // Start recursion
        return solve(arr, n - 1, dp);
    }

    // Recursive helper function with memoization
    private static int solve(int[] arr, int i, int[] dp) {
        // No element to pick
        if (i < 0) return 0;

        // Only one element
        if (i == 0) return arr[0];

        // Return memoized result
        if (dp[i] != -1) return dp[i];

        // Pick current and move 2 back
        int pick = arr[i] + solve(arr, i - 2, dp);

        // Don't pick current, move 1 back
        int notPick = solve(arr, i - 1, dp);

        // Memoize and return result
        return dp[i] = Math.max(pick, notPick);
    }

    ///  tabulation tc + sc O(n)
    // Function to return maximum sum of non-adjacent elements
    static int maximumNonAdjacentSumI(int[] arr) {
        // Get the size of array
        int n = arr.length;

        // If only one element, return it
        if (n == 1) return arr[0];

        // Initialize dp array
        int[] dp = new int[n];

        // Base case
        dp[0] = arr[0];

        // Compare first two elements
        dp[1] = Math.max(arr[0], arr[1]);

        // Iterate from index 2
        for (int i = 2; i < n; i++) {
            // Take current and dp[i-2] or just dp[i-1]
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        }

        // Return result from last index
        return dp[n - 1];
    }

    ///  space optimization tc O(n) + sc O(1)
    // Function to return maximum sum of non-adjacent elements
    static int maxSumII(int[] nums) {
        // Handle edge case for empty input
        if (nums.length == 0) return 0;

        // Initialize two tracking sums
        int prev2 = 0;
        int prev = nums[0];

        // Traverse through the array
        for (int i = 1; i < nums.length; i++) {
            // Include current by adding it to two steps back
            int include = nums[i] + prev2;

            // Exclude current by taking previous best
            int exclude = prev;

            // Choose max of include and exclude
            int curr = Math.max(include, exclude);

            // Update tracking variables
            prev2 = prev;
            prev = curr;
        }

        // Final result is stored in prev
        return prev;
    }
}