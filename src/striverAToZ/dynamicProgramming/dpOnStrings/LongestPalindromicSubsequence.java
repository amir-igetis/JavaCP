package striverAToZ.dynamicProgramming.dpOnStrings;

public class LongestPalindromicSubsequence {

/// Problem Statement: Given a string, Find the longest palindromic subsequence length in given string.

/// A palindrome is a sequence that reads the same backwards as forward.
/// A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.	

	public static void main(String[] args) {
		// Example input string
		String s = "bbabcbcab";

		// Print the result
		System.out.println("The Length of Longest Palindromic Subsequence is " + 
		longestPalinSubseqI(s));

	}

/// Time Complexity: O(N * M), where N is the length of the string and M is the length of its reverse.

/// Space Complexity: O(N * M) for the DP array used to store the lengths of the longest common subsequences.

// tabulation

	/* Function to calculate the length of the Longest Palindromic Subsequence */
	static int longestPalinSubseq(String s) {
		// Reverse the original string
		String t = new StringBuilder(s).reverse().toString();

		// LPS is just the LCS between the string and its reverse
		return func(s, t);
	}

	private static int func(String s1, String s2) {
		// Length of first string
		int n = s1.length();
		// Length of second string
		int m = s2.length();

		// 2D DP array: dp[i][j] stores LCS length of s1[0..i-1] and s2[0..j-1]
		int[][] dp = new int[n + 1][m + 1];

		// Initialize first row to 0 (when one string is empty)
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 0;
		}
		// Initialize first column to 0 (when the other string is empty)
		for (int i = 0; i <= m; i++) {
			dp[0][i] = 0;
		}

		// Fill DP table
		for (int ind1 = 1; ind1 <= n; ind1++) {
			for (int ind2 = 1; ind2 <= m; ind2++) {
				// If current characters match, add 1 to the LCS of previous characters
				if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
					dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
				}
				// If they don't match, take the max by ignoring one character from either
				// string
				else {
					dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
				}
			}
		}

		// LCS length is stored in the bottom-right cell
		return dp[n][m];
	}

	/// Time Complexity: O(N * M), where N is the length of the string and M is the
	/// length of its reverse.

/// Space Complexity: O(M) for the two arrays used to store the lengths of the longest common subsequences, where M is the length of the second string.
// space optimized

	/*
	 * Function to calculate the length of the Longest Palindromic Subsequence
	 */
	static int longestPalinSubseqI(String s) {
		// Reverse the input string
		String t = new StringBuilder(s).reverse().toString();

		// LPS length is the LCS between s and its reverse
		return lcsI(s, t);
	}

	private static int lcsI(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();

		// prev[] stores results for the previous row
		int[] prev = new int[m + 1];
		// cur[] stores results for the current row
		int[] cur = new int[m + 1];

		// Loop through each character of s1
		for (int ind1 = 1; ind1 <= n; ind1++) {
			// Loop through each character of s2
			for (int ind2 = 1; ind2 <= m; ind2++) {
				// If characters match, extend the LCS length
				if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
					cur[ind2] = 1 + prev[ind2 - 1];
				// If not, take the maximum from top or left
				else
					cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
			}
			// Copy current row to previous row for next iteration
			System.arraycopy(cur, 0, prev, 0, m + 1);
		}

		// LCS length will be in prev[m]
		return prev[m];
	}

}
