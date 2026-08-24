package leetcodeDiscussProbPatterns.dailyQuestions;

public class StoneGameV {
	public static void main(String[] args) {

		int[] stoneValue = { 6, 2, 3, 4, 5, 5 };
		System.out.println(stoneGameV(stoneValue));
		System.out.println(stoneGameVI(stoneValue));

	}

	// DP
	/// Let n be the length of the array stoneValue.
///
/// Time complexity: O(n^3)
/// 
/// Space complexity: O(n^2)
/// The O(n^2) space is required to store all DP states.
	static int[][] f;

	static int stoneGameV(int[] stoneValue) {
		int n = stoneValue.length;
		f = new int[n][n];
		return dfs(stoneValue, 0, n - 1);
	}

	private static int dfs(int[] stoneValue, int left, int right) {
		if (left == right) {
			return 0;
		}
		if (f[left][right] != 0) {
			return f[left][right];
		}

		int sum = 0;
		for (int i = left; i <= right; ++i) {
			sum += stoneValue[i];
		}
		int suml = 0;
		for (int i = left; i < right; ++i) {
			suml += stoneValue[i];
			int sumr = sum - suml;
			if (suml < sumr) {
				f[left][right] = Math.max(f[left][right], dfs(stoneValue, left, i) + suml);
			} else if (suml > sumr) {
				f[left][right] = Math.max(f[left][right], dfs(stoneValue, i + 1, right) + sumr);
			} else {
				f[left][right] = Math.max(f[left][right],
						Math.max(dfs(stoneValue, left, i), dfs(stoneValue, i + 1, right)) + suml);
			}
		}
		return f[left][right];
	}

	// DP Optimized
	/// Let n be the length of the array stoneValue.
	///
	/// Time complexity: O(n^3)
	///
	/// Space complexity: O(n^2)
	/// The O(n^2) space is required to store the DP states and the auxiliary arrays
	// maxl and maxr.
	static int[][] fI;
	static int[][] maxl;
	static int[][] maxr;

	static int stoneGameVI(int[] stoneValue) {
		int n = stoneValue.length;
		fI = new int[n][n];
		maxl = new int[n][n];
		maxr = new int[n][n];
		for (int left = n - 1; left >= 0; --left) {
			maxl[left][left] = maxr[left][left] = stoneValue[left];
			int sum = stoneValue[left], suml = 0;
			for (int right = left + 1, i = left - 1; right < n; ++right) {
				sum += stoneValue[right];
				while (i + 1 < right && (suml + stoneValue[i + 1]) * 2 <= sum) {
					suml += stoneValue[i + 1];
					++i;
				}
				if (left <= i) {
					fI[left][right] = Math.max(fI[left][right], maxl[left][i]);
				}
				if (i + 1 < right) {
					fI[left][right] = Math.max(fI[left][right], maxr[i + 2][right]);
				}
				if (suml * 2 == sum) {
					fI[left][right] = Math.max(fI[left][right], maxr[i + 1][right]);
				}
				maxl[left][right] = Math.max(maxl[left][right - 1], sum + fI[left][right]);
				maxr[left][right] = Math.max(maxr[left + 1][right], sum + fI[left][right]);
			}
		}
		return fI[0][n - 1];
	}

}
