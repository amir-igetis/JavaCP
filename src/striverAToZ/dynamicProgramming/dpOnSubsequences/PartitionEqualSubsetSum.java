package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
    // Example array
        int[] arr = {2, 3, 3, 3, 4, 5};

        // Output result
        if (canPartition(arr))
            System.out.println("The Array can be partitioned into two equal subsets");
        else
            System.out.println("The Array cannot be partitioned into two equal subsets");

    }

    ///  memoization
    /// Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the target sum i.e. Half of the sum of all elements of the array.
    /// Space Complexity: O(N*K) + O(N), we use a memo table to avoid recomputation. Extra auxiliary stack space is used for recursion.
    // Main function to check if array can be partitioned into two equal subsets
    static boolean canPartition(int[] arr) {
        // Find array size
        int n = arr.length;

        // Calculate total sum
        int totSum = 0;
        for (int num : arr)
            totSum += num;

        // If sum is odd, partition not possible
        if (totSum % 2 == 1)
            return false;

        // Target sum for each subset
        int k = totSum / 2;

        // DP table initialization
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Check possibility using recursion + memoization
        return subsetSumUtil(n - 1, k, arr, dp);
    }

    private static boolean subsetSumUtil(int ind, int target, int[] arr, int[][] dp) {
        // Base case: target sum reached
        if (target == 0)
            return true;

        // Base case: first element check
        if (ind == 0)
            return arr[0] == target;

        // If already computed, return stored result
        if (dp[ind][target] != -1)
            return dp[ind][target] == 1;

        // Choice 1: exclude current element
        boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);

        // Choice 2: include current element if possible
        boolean taken = false;
        if (arr[ind] <= target)
            taken = subsetSumUtil(ind - 1, target - arr[ind], arr, dp);

        // Store result in dp table
        dp[ind][target] = (notTaken || taken) ? 1 : 0;
        return notTaken || taken;
    }

    ///  tabulation
    /// Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the target sum i.e. Half of the sum of all elements of the array.
    /// Space Complexity: O(N*K) , we use a 2D DP array to avoid recomputation.
    static boolean canPartitionI(int[] arr) {
        int n = arr.length;

        // Step 1: Calculate total sum
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        // Step 2: If total sum is odd, partition is impossible
        if (totalSum % 2 != 0) return false;

        // Step 3: Target sum for each subset
        int targetSum = totalSum / 2;

        // Step 4: Create DP table and initialize
        boolean[][] dp = new boolean[n][targetSum + 1];

        // Step 5: Base case: sum 0 is always possible
        for (int i = 0; i < n; i++) dp[i][0] = true;

        // Step 6: Initialize first row
        if (arr[0] <= targetSum) dp[0][arr[0]] = true;

        // Step 7: Fill DP table
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= targetSum; target++) {
                boolean notTaken = dp[index - 1][target];
                boolean taken = false;
                if (arr[index] <= target) {
                    taken = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = notTaken || taken;
            }
        }

        // Step 8: Return result
        return dp[n - 1][targetSum];
    }

    /// space optimized
    /// Time Complexity: O(N*K), there are total N*K states, where N is the length of array and K is the target sum i.e. Half of the sum of all elements of the array.
    /// Space Complexity: O(N), we use two 1D arrays to store value of previous row and current row.
    static boolean canPartitionII(int[] arr) {
        int n = arr.length;

        // Calculate total sum of the array
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        // If the sum is odd, partition into equal subsets is impossible
        if (totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;

        // prev[sum] indicates if a subset with 'sum' is possible so far
        boolean[] prev = new boolean[target + 1];
        prev[0] = true; // sum = 0 is always possible

        // Initialize with the first element
        if (arr[0] <= target)
            prev[arr[0]] = true;

        // Iterate over all remaining elements
        for (int i = 1; i < n; i++) {
            // cur[sum] will store possibilities for current element
            boolean[] cur = new boolean[target + 1];
            cur[0] = true; // sum 0 always possible

            for (int sum = 1; sum <= target; sum++) {
                // Option 1: Do not take current element
                boolean notTaken = prev[sum];

                // Option 2: Take current element if it fits
                boolean taken = false;
                if (arr[i] <= sum)
                    taken = prev[sum - arr[i]];

                // Current sum possible if either option is true
                cur[sum] = notTaken || taken;
            }

            // Move to the next element
            prev = cur;
        }

        // Final answer is whether target sum is possible
        return prev[target];
    }
}
