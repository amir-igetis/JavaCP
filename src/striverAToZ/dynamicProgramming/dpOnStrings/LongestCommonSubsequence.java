package striverAToZ.dynamicProgramming.dpOnStrings;

public class LongestCommonSubsequence {

	/// Problem Description: Given two strings str1 and str2, print the longest
	/// common subsequence of the two strings.

/// A subsequence of a string is a list of characters of the string where zero or more characters are deleted and 
/// they should be in the same order in the subsequence as in the original string.

	public static void main(String[] args) {
		String s1 = "abcde";
		String s2 = "ace";

		System.out.println("LCS: " + longestCommonSubsequence(s1, s2));

	}

	/// Time Complexity: O(N*M) + O(N+M), we first build the entire DP table and
	/// then traverse it to reconstruct the LCS.
/// Space Complexity: O(N*M), space used to store DP table.
	static String longestCommonSubsequence(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();

		// Create DP table to store lengths of LCS for all substrings
		int[][] dp = new int[n + 1][m + 1];

		// Fill dp table bottom-up
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					// Characters match: increase length by 1
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					// Characters don't match: take max of left and top
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// Reconstruct LCS string from dp table
		StringBuilder lcs = new StringBuilder();
		int i = n, j = m;

		// Traverse dp table from bottom-right to top-left
		while (i > 0 && j > 0) {
			if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
				// Characters match, add to result and move diagonally
				lcs.append(text1.charAt(i - 1));
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				// Move up if top cell has greater value
				i--;
			} else {
				// Move left otherwise
				j--;
			}
		}

		// Reverse string since it was built backwards
		return lcs.reverse().toString();
	}

}
