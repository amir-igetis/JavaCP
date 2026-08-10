package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class StoneGameII {
	public static void main(String[] args) {
		int[] piles = { 2, 7, 9, 4, 4 };
		System.out.println(stoneGameII(piles));
	}

	// tc O(n^3) sc O(n^2)
	static int stoneGameII(int[] piles) {
		// Store the suffix sum of all array elements.
		int[] suffixSum = Arrays.copyOf(piles, piles.length);

		for (int i = suffixSum.length - 2; i >= 0; i--) {
			suffixSum[i] += suffixSum[i + 1];
		}
		return maxStones(suffixSum, 1, 0, new int[piles.length][piles.length]);
	}

	private static int maxStones(int[] suffixSum, int maxTillNow, int currIndex, int[][] memo) {
		// If currIndex + 2*maxTillNow lies outside the array, pick all remaining
		// stones.
		if (currIndex + 2 * maxTillNow >= suffixSum.length) {
			return suffixSum[currIndex];
		}
		if (memo[currIndex][maxTillNow] > 0)
			return memo[currIndex][maxTillNow];
		int res = Integer.MAX_VALUE;
		// Find the minimum value res for the next move possible.
		for (int i = 1; i <= 2 * maxTillNow; i++) {
			res = Math.min(res, maxStones(suffixSum, Math.max(i, maxTillNow), currIndex + i, memo));
		}
		// Memoize the difference of suffixSum[p] and res. This denotes the maximum
		// stones that can be picked.
		memo[currIndex][maxTillNow] = suffixSum[currIndex] - res;
		return memo[currIndex][maxTillNow];
	}

	// tabulation
	static int stoneGameII_I(int[] piles) {
		int length = piles.length;
		int[][] dp = new int[length + 1][length + 1];

		// Store suffix sum for all possible suffix
		int[] suffixSum = new int[length + 1];
		for (int i = length - 1; i >= 0; i--) {
			suffixSum[i] = suffixSum[i + 1] + piles[i];
		}

		// Initialize the dp array.
		for (int i = 0; i <= length; i++) {
			dp[i][length] = suffixSum[i];
		}

		// Start from the last index to store the future state first.
		for (int index = length - 1; index >= 0; index--) {
			for (int maxTillNow = length - 1; maxTillNow >= 1; maxTillNow--) {
				for (int X = 1; X <= 2 * maxTillNow && index + X <= length; X++) {
					dp[index][maxTillNow] = Math.max(dp[index][maxTillNow],
							suffixSum[index] - dp[index + X][Math.max(maxTillNow, X)]);
				}
			}
		}
		return dp[0][1];
	}
}
