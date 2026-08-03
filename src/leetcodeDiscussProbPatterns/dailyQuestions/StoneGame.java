package leetcodeDiscussProbPatterns.dailyQuestions;

public class StoneGame {
	public static void main(String[] args) {
		int[] piles = { 5, 3, 4, 5 };
		System.out.println(stoneGame(piles));
	}

	/// Time Complexity: O(N^2), where N is the number of piles.
	/// Space Complexity: O(N^2), the space used storing the intermediate results of
	/// each subgame.

	// Dynamic Programming
	static boolean stoneGame(int[] piles) {
		int n = piles.length;

		// dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
		int[][] dp = new int[n + 2][n + 2];
		for (int size = 1; size <= n; ++size)
			for (int i = 0; i + size <= n; ++i) {
				int j = i + size - 1;
				int parity = (j + i + n) % 2; // j - i - N; but +x = -x (mod 2)
				if (parity == 1)
					dp[i + 1][j + 1] = Math.max(piles[i] + dp[i + 2][j + 1], piles[j] + dp[i + 1][j]);
				else
					dp[i + 1][j + 1] = Math.min(-piles[i] + dp[i + 2][j + 1], -piles[j] + dp[i + 1][j]);
			}

		return dp[1][n] > 0;
	}

	/// tc and sc O(1)

	// mathematical
	static boolean stoneGameI(int[] piles) {
		return true;
	}

}
