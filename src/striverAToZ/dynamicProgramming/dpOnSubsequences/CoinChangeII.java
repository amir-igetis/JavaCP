package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class CoinChangeII {

	/// Problem Statement: A thief wants to rob a store. He is carrying a bag of
	/// capacity W.
	/// The store has ‘n’ items of infinite supply. Its weight is given by the ‘wt’
	/// array and
	/// its value by the ‘val’ array. He can either include an item in its knapsack
	/// or exclude it but can’t
	/// partially have it as a fraction. We need to find the maximum value of items
	/// that the thief can steal.
	/// He can take a single item any number of times he wants and put it in his
	/// knapsack .

	public static void main(String[] args) {
		int[] wt = { 2, 4, 6 };
		int[] val = { 5, 11, 13 };
		int W = 10;
		int n = wt.length;
		System.out.println("The Maximum value of items the thief can steal is " + unboundedKnapsack(n, W, val, wt));

	}

/// Time Complexity: O(N*W),There are N*W states therefore at max ‘N*W’ new problems will be solved.
/// Space Complexity: O(N*W) + O(N),We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).̥

	// memorization
	// Main function to initialize DP and call utility
	static int unboundedKnapsack(int n, int W, int[] val, int[] wt) {
		int[][] dp = new int[n][W + 1];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		return knapsackUtil(wt, val, n - 1, W, dp);
	}

	private static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {
		// Base case: only first item available
		if (ind == 0) {
			return (W / wt[0]) * val[0];
		}

		// If already computed, return stored value
		if (dp[ind][W] != -1)
			return dp[ind][W];

		// Option 1: Do not take current item
		int notTaken = knapsackUtil(wt, val, ind - 1, W, dp);

		// Option 2: Take current item (if it fits)
		int taken = Integer.MIN_VALUE;
		if (wt[ind] <= W) {
			taken = val[ind] + knapsackUtil(wt, val, ind, W - wt[ind], dp);
		}

		// Store and return the best option
		return dp[ind][W] = Math.max(notTaken, taken);
	}

	// tabulation

	/// Time Complexity: O(N*W),There are two nested loops
/// Space Complexity: O(N*W),We are using an external array of size ‘N*W’. Stack Space is eliminated.
	static int unboundedKnapsackI(int n, int W, int[] val, int[] wt) {
		// Create DP table where dp[i][j] stores max value for i items and capacity j
		int[][] dp = new int[n][W + 1];

		// Base condition: fill first row using infinite supply of first item
		for (int i = wt[0]; i <= W; i++) {
			dp[0][i] = (i / wt[0]) * val[0];
		}

		// Loop through remaining items
		for (int ind = 1; ind < n; ind++) {
			// Loop through all capacities
			for (int cap = 0; cap <= W; cap++) {
				// Case 1: Not take current item
				int notTaken = dp[ind - 1][cap];

				// Case 2: Take current item
				int taken = Integer.MIN_VALUE;
				if (wt[ind] <= cap) {
					taken = val[ind] + dp[ind][cap - wt[ind]];
				}

				// Store the best value
				dp[ind][cap] = Math.max(notTaken, taken);
			}
		}

		// Return result
		return dp[n - 1][W];
	}
	// space optimized

/// Time Complexity: O(N*W),There are two nested loops.
/// Space Complexity: O(W), We are using an external array of size ‘W+1’ to store only one row.
	static int unboundedKnapsackII(int n, int W, int[] val, int[] wt) {
		// cur[cap] will store max value for capacity = cap
		int[] cur = new int[W + 1];

		// Base case: Fill for first item
		for (int i = wt[0]; i <= W; i++) {
			cur[i] = (i / wt[0]) * val[0];
		}

		// Process remaining items
		for (int ind = 1; ind < n; ind++) {
			for (int cap = 0; cap <= W; cap++) {
				// Option 1: Do not take current item
				int notTaken = cur[cap];

				// Option 2: Take current item (if fits)
				int taken = Integer.MIN_VALUE;
				if (wt[ind] <= cap) {
					taken = val[ind] + cur[cap - wt[ind]];
				}

				// Choose max of two options
				cur[cap] = Math.max(notTaken, taken);
			}
		}

		// Final result for capacity W
		return cur[W];
	}
}
