package leetcodeDiscussProbPatterns.dailyQuestions;

public class StoneGameIII {
	public static void main(String[] args) {
		int[] stoneValue = { 1, 2, 3, 7 };
		System.out.println(stoneGameIII_II(stoneValue));
	}

	static String stoneGameIII(int[] stoneValue) {
		int n = stoneValue.length;

		Integer[] dp = new Integer[n];

		int diff = solve(0, stoneValue, dp);

		if (diff > 0)
			return "Alice";
		if (diff < 0)
			return "Bob";

		return "Tie";
	}

	private static int solve(int i, int[] stones, Integer[] dp) {

		if (i >= stones.length)
			return 0;

		if (dp[i] != null)
			return dp[i];

		int sum = 0;
		int best = Integer.MIN_VALUE;

		for (int k = 0; k < 3 && i + k < stones.length; k++) {

			sum += stones[i + k];

			best = Math.max(best, sum - solve(i + k + 1, stones, dp));
		}

		return dp[i] = best;

	}

	// bottom up DP
	static String stoneGameIII_I(int[] stoneValue) {

		int n = stoneValue.length;

		int[] dp = new int[n + 1];

		for (int i = n - 1; i >= 0; i--) {

			int sum = 0;
			dp[i] = Integer.MIN_VALUE;

			for (int k = 0; k < 3 && i + k < n; k++) {

				sum += stoneValue[i + k];

				dp[i] = Math.max(dp[i], sum - dp[i + k + 1]);
			}
		}

		if (dp[0] > 0)
			return "Alice";

		if (dp[0] < 0)
			return "Bob";

		return "Tie";
	}

	// space optimized DP
	static String stoneGameIII_II(int[] stoneValue) {

		int n = stoneValue.length;

		int[] dp = new int[4];

		for (int i = n - 1; i >= 0; i--) {

			int sum = 0;
			int best = Integer.MIN_VALUE;

			for (int k = 0; k < 3 && i + k < n; k++) {

				sum += stoneValue[i + k];

				best = Math.max(best, sum - dp[(i + k + 1) % 4]);
			}

			dp[i % 4] = best;
		}

		if (dp[0] > 0)
			return "Alice";

		if (dp[0] < 0)
			return "Bob";

		return "Tie";
	}
}
