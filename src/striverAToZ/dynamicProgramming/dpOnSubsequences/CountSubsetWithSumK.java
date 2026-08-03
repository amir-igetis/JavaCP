package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class CountSubsetWithSumK {

    /// Problem Statement : Given an array arr of n integers and an integer K, count the number of subsets of
    /// the given array that have a sum equal to K.

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3};
        int target = 6;
        System.out.println(countSubsets(nums, target));
    }

    /// Time Complexity: O(N * K), each state defined by index and target is computed once.
    /// Space Complexity: O(N * K), extra space is used for the dp table and recursion stack.
    // memoization
    static int countSubsets(int[] nums, int target) {
        // Initialize dp table with -1 (uncomputed states)
        int[][] dp = new int[nums.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(nums.length - 1, target, nums, dp);
    }

    // Recursive helper with memoization
    private static int solve(int index, int target, int[] nums, int[][] dp) {
        // Base case: if target is 0, we found a valid subset
        if (target == 0) return 1;

        // Base case: if we are at index 0, check if nums[0] equals target
        if (index == 0) return (nums[0] == target ? 1 : 0);

        // If already computed, return from dp
        if (dp[index][target] != -1) return dp[index][target];

        // Case 1: Exclude current element
        int notTake = solve(index - 1, target, nums, dp);

        // Case 2: Include current element (if it is not greater than target)
        int take = 0;
        if (nums[index] <= target) {
            take = solve(index - 1, target - nums[index], nums, dp);
        }

        // Store result in dp and return
        return dp[index][target] = take + notTake;
    }
}
