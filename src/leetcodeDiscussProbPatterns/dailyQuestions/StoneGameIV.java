package leetcodeDiscussProbPatterns.dailyQuestions;

public class StoneGameIV {

	public static void main(String[] args) {
		int n = 1;
		System.out.println(winnerSquareGame(n));
	}

	// Dynamic Programming (memoization) tc O(n sqrt(n)) sc O(N)
	static boolean winnerSquareGame(int n) {
		int[] dp = new int[n + 1];

		// -1 = not calculated
		// 0 = losing
		// 1 = winning
		java.util.Arrays.fill(dp, -1);

		return solve(n, dp);
	}

	private static boolean solve(int n, int[] dp) {

		// No stones -> current player loses
		if (n == 0)
			return false;

		if (dp[n] != -1)
			return dp[n] == 1;

		// Try removing every possible square
		for (int i = 1; i * i <= n; i++) {

			int square = i * i;

			// If opponent is in a losing state,
			// current player wins.
			if (!solve(n - square, dp)) {
				dp[n] = 1;
				return true;
			}
		}

		// No winning move exists
		dp[n] = 0;
		return false;
	}

	// tabulation
	static boolean winnerSquareGameI(int n) {

		boolean[] dp = new boolean[n + 1];

		// dp[0] = false
		// No stones -> current player loses

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j * j <= i; j++) {

				int square = j * j;

				// If we can move to a losing state,
				// current state is winning.
				if (!dp[i - square]) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[n];
	}
}
