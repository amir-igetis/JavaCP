package striverAToZ.dynamicProgramming.oneDDp;

import java.util.Arrays;
import java.util.List;

public class FrogJumpWithKDistance {

    /// Problem Statement:
    ///
    /// A frog wants to climb a staircase with n steps.
    /// Given an integer array heights, where heights(i) contains the height of the ith step,
    /// and an integer k. To jump from the ith step to the jth step, the frog requires abs(heights(i) - heights(j))
    /// energy, where abs() denotes the absolute difference. The frog can jump from the ith step to any step in the range (i+1, i + k), provided it exists.
    /// Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th step.

    public static void main(String[] args) {
        // Heights of stones
        List<Integer> height = Arrays.asList(30, 10, 60, 10, 60, 50);
        int n = height.size();
        int k = 2;

        // Output the minimum cost
        System.out.println(frogJumpWithKI(n, height, k));
    }


    /// memoization tc O(n*k) sc o(n)
    // Function to get minimum cost to reach end
    static int frogJumpWithK(int n, List<Integer> height, int k) {
        // DP array initialized to -1
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        // Start recursion from last index
        return solveUtil(n - 1, height, dp, k);
    }

    // Function to find the minimum cost to reach index 'ind' using at most 'k' jumps
    private static int solveUtil(int ind, List<Integer> height, int[] dp, int k) {
        // Base case
        if (ind == 0) return 0;

        // Return already computed result
        if (dp[ind] != -1) return dp[ind];

        // Initialize minimum steps as large value
        int mmSteps = Integer.MAX_VALUE;

        // Try all possible jumps from 1 to k
        for (int j = 1; j <= k; j++) {
            // Ensure jump does not go out of bounds
            if (ind - j >= 0) {
                // Cost of taking the jump
                int jump = solveUtil(ind - j, height, dp, k) + Math.abs(height.get(ind) - height.get(ind - j));
                // Store the minimum cost
                mmSteps = Math.min(jump, mmSteps);
            }
        }
        // Save the result in dp array
        return dp[ind] = mmSteps;
    }


    /// tabulation tc O(n*k) sc o(n)
    // Function to solve the problem
    static int frogJumpWithKI(int n, List<Integer> height, int k) {
        // Initialize DP array with -1
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solveUtilI(n, height, dp, k);
    }

    // Function to compute the minimum cost to reach the end using at most 'k' jumps
    private static int solveUtilI(int n, List<Integer> height, int[] dp, int k) {
        // Base case: cost to reach first stone is 0
        dp[0] = 0;

        // Iterate over each stone
        for (int i = 1; i < n; i++) {
            // Initialize minimum cost for this stone as large value
            int mmSteps = Integer.MAX_VALUE;

            // Try all possible jump lengths from 1 to k
            for (int j = 1; j <= k; j++) {
                // Ensure jump doesn't go out of bounds
                if (i - j >= 0) {
                    // Cost of jumping from (i - j) to i
                    int jump = dp[i - j] + Math.abs(height.get(i) - height.get(i - j));
                    // Keep track of the minimum cost
                    mmSteps = Math.min(mmSteps, jump);
                }
            }

            // Store computed minimum cost
            dp[i] = mmSteps;
        }

        // Last element of dp stores the answer
        return dp[n - 1];
    }
}