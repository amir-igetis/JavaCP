package leetcodeDiscussProbPatterns.dailyQuestions;

public class LongestSubsequenceWithNonZeroBitwiseXOR {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(longestSubsequence(nums));
	}

// classification Discussion
/// Let n be the length of the array nums.
///
/// Time complexity: O(n).
/// 
/// Space complexity: O(1).
	static int longestSubsequence(int[] nums) {
		int n = nums.length;
		int totalXor = 0;
		boolean allZero = true;

		for (int x : nums) {
			totalXor ^= x;
			if (x > 0) {
				allZero = false;
			}
		}
		if (totalXor > 0) {
			return n;
		}

		return allZero ? 0 : n - 1;
	}
}
