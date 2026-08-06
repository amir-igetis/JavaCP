package striverAToZ.dynamicProgramming.dpOnStrings;

public class MinInsertionsToMakeStringPalindrome {

	/// Problem Statement: Given a string s, find the minimum number of insertions
	/// needed to make it a palindrome. A palindrome is a sequence that reads the
	/// same backward as forward. You can insert characters at any position in the
	/// string.

	public static void main(String[] args) {
		// Input string
		String s = "abcaa";

		// Print result
		System.out.println("The Minimum insertions required to make string palindrome: " + 
		minInsertion(s));

	}

	/// Time Complexity: O(N*N), we fill the entire DP array of size N*N one by one.
/// Space Complexity: O(N*N), additional space used to store DP array.
	// tabulation
	private static int lcs(String s1, String s2) {

		// Get sizes of strings
		int n = s1.length();
		int m = s2.length();

		// Create dp array
		int[][] dp = new int[n + 1][m + 1];

		// Fill dp array
		for (int ind1 = 1; ind1 <= n; ind1++) {
			for (int ind2 = 1; ind2 <= m; ind2++) {

				// If characters match
				if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
					dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
				}

				// If they don't match
				else {
					dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
				}
			}
		}

		// Return bottom-right value
		return dp[n][m];
	}

	// Function to calculate LPS
	private static int longestPalindromeSubsequence(String s) {

		// Reverse string
		String t = new StringBuilder(s).reverse().toString();

		// LCS of s and reverse
		return lcs(s, t);
	}

	// Function to calculate minimum insertions
	static int minInsertion(String s) {

		// Get length
		int n = s.length();

		// Get LPS length
		int k = longestPalindromeSubsequence(s);

		// Return answer
		return n - k;
	}

	/// Time Complexity: O(N*N), we fill the entire DP array of size N*N one by one.
/// Space Complexity: O(N), we are using an external array of size ‘N+1’ to store only two rows.
// space optimized
	// Function to compute LCS using 2-row DP
	private static int lcsI(String s1, String s2) {

		// Get sizes
		int n = s1.length();
		int m = s2.length();

		// Initialize prev and cur rows
		int[] prev = new int[m + 1];
		int[] cur = new int[m + 1];

		// Fill rows
		for (int ind1 = 1; ind1 <= n; ind1++) {
			for (int ind2 = 1; ind2 <= m; ind2++) {

				// If characters match
				if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
					cur[ind2] = 1 + prev[ind2 - 1];

				// Else take max from prev row or left
				else
					cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
			}

			// Move cur to prev
			prev = cur.clone();
		}

		// Return result
		return prev[m];
	}

	// Function to compute LPS
	private static int longestPalindromeSubsequenceI(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		String t = sb.toString();
		return lcsI(s, t);
	}

	// Function to compute min insertions
	static int minInsertionI(String s) {
		int n = s.length();
		int k = longestPalindromeSubsequenceI(s);
		return n - k;
	}
}
