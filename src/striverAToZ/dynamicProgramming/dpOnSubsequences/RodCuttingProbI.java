package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class RodCuttingProbI {

	/// Problem Statement: Given a rod of length N inches and an array price[] where
	/// price[i] denotes the value of a piece of rod of length i inches (1-based
	/// indexing). Determine the maximum value obtainable by cutting up the rod and
	/// selling the pieces. Make any number of cuts, or none at all, and sell the
	/// resulting pieces.

	public static void main(String[] args) {
		// Price list
		int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };

		// Total rod length
		int n = 8;

		// Output result
		int result = rodCutting(price, n);
		System.out.println("The maximum obtainable value is: " + result);

	}

	// memoization

	/// Time Complexity: O(n × n) Each subproblem (i, length) is computed once.
/// Space Complexity: O(n × n),We use a 2D DP table for memoization.

	static int rodCutting(int[] price, int n) {
		// Create DP table initialized to -1
		int[][] dp = new int[n][n + 1];

		// Fill dp with -1 to indicate uncomputed subproblems
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		// Start from last index (n - 1)
		return helper(n - 1, n, price, dp);
	}

	// Recursive helper function with memoization
	private static int helper(int i, int length, int[] price, int[][] dp) {
		// Base case: only one rod piece of length 1
		if (i == 0) {
			return length * price[0];
		}

		// Return precomputed value
		if (dp[i][length] != -1)
			return dp[i][length];

		// Option 1: do not take current rod length
		int notTake = helper(i - 1, length, price, dp);

		// Option 2: take current rod length (if it fits)
		int take = Integer.MIN_VALUE;
		int rodLength = i + 1;
		if (rodLength <= length) {
			take = price[i] + helper(i, length - rodLength, price, dp);
		}

		// Store max of both in dp
		return dp[i][length] = Math.max(take, notTake);
	}

	// tabulation

	/// Time Complexity: O(N * W),Because we have a nested loop iterating through
	/// all `n` items and all `W` capacities.
/// Space Complexity: O(N * W),We are using a 2D DP table of size `n` by `W+1` to store intermediate results, and we eliminated recursion stack space.
	static int rodCuttingI(int[] price, int n) {
		// Initialize DP table with dimensions [n][n + 1]
		int[][] dp = new int[n][n + 1];

		for (int length = 0; length <= n; length++) {
			dp[0][length] = price[0] * length;
		}

		// Fill the DP table
		for (int ind = 1; ind < n; ++ind) {
			for (int length = 1; length <= n; ++length) {

				// Case when the piece is not taken
				int notTaken = dp[ind - 1][length];

				// Case when the piece is taken
				int taken = Integer.MIN_VALUE;

				/*
				 * Length of the rod piece corresponding to the current index
				 */
				int rodLength = ind + 1;

				// Check if the piece can be taken
				if (rodLength <= length) {
					taken = price[ind] + dp[ind][length - rodLength];
				}

				/*
				 * Update dp[ind][length] with the maximum of including or not including the
				 * current piece
				 */
				dp[ind][length] = Math.max(notTaken, taken);
			}
		}

		// Return the result
		return dp[n - 1][n];
	}

// space optimized

/// Time Complexity: O(N * W), We iterate through N items, and for each item we process W weight capacities.
/// Space Complexity: O(W),We only use a 1D array of size W to store intermediate results, eliminating the need for 2D DP or recursion stack.
	static int rodCuttingII(int[] price, int n) {
		// Create two 1D arrays for space optimization
		int[] prev = new int[n + 1];
		int[] cur = new int[n + 1];

		// Base case: if only the first piece is available
		for (int length = 0; length <= n; length++) {
			prev[length] = price[0] * length;
		}

		// Fill DP rows for each piece length
		for (int ind = 1; ind < n; ind++) {
			for (int length = 1; length <= n; length++) {
				// Case 1: Do not take current piece
				int notTaken = prev[length];

				// Case 2: Take current piece if possible
				int taken = Integer.MIN_VALUE;
				int rodLength = ind + 1;
				if (rodLength <= length) {
					taken = price[ind] + cur[length - rodLength];
				}

				// Store maximum value
				cur[length] = Math.max(notTaken, taken);
			}
			// Update prev row with current row
			prev = cur.clone();
		}

		// Final answer is stored in prev[n]
		return prev[n];
	}

}
