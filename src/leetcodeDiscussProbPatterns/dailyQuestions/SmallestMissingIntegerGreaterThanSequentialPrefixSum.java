package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 2, 5 };
		System.out.println(missingInteger(nums));
		System.out.println(missingIntegerI(nums));
	}

// Simulation + Prefix Sum
/// Time complexity: O(n).
///
//// Finding the longest ordered prefix takes O(n) time, and finding the first value that does not appear in nums takes O(n) time in the worst case.
///
/// Space complexity: O(n).
///
/// The hash set requires O(n) auxiliary space.
	static int missingInteger(int[] nums) {
		int n = nums.length;
		Set<Integer> st = new HashSet<Integer>();
		for (int num : nums)
			st.add(num);

		int total = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i] == nums[i - 1] + 1)
				total += nums[i];
			else
				break;
		}

		while (st.contains(total))
			total += 1;

		return total;
	}

	// Simulation + Series Sum Formula
	/// Time complexity: O(n).
///
/// Finding the longest ordered prefix takes O(n) time, and finding the first value that does not appear in nums takes O(n) time in the worst case.
///
/// Space complexity: O(n).
///
/// The hash set requires O(n) auxiliary space.
	static int missingIntegerI(int[] nums) {
		int n = nums.length;
		Set<Integer> st = new HashSet<Integer>();
		for (int num : nums)
			st.add(num);
		int prefixLen = 1;
		for (int i = 1; i < n; i++) {
			if (nums[i] == nums[i - 1] + 1)
				prefixLen += 1;
			else
				break;
		}
		int total = ((nums[prefixLen - 1] + nums[0]) * prefixLen) / 2;
		while (st.contains(total))
			total += 1;

		return total;
	}
}
