package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingMultipleOfK {
	public static void main(String[] args) {
		int[] nums = { 8, 2, 3, 4, 6 };
		int k = 2;
		System.out.println(missingMultiple(nums, k));
	}

// Enumeration + Hash Table
/// Let n be the length of the array nums.
///
/// Time complexity: O(n).
/// 
/// Constructing the hash set takes O(n) time. We may need to enumerate at most n+1 multiples of k, and each lookup takes O(1) average time.
///
/// Space complexity: O(n)
///
/// The hash set stores at most n distinct elements.
	static int missingMultiple(int[] nums, int k) {
		Set<Integer> st = new HashSet<Integer>();
		for (int num : nums)
			st.add(num);

		int ans = k;
		while (st.contains(ans))
			ans += k;

		return ans;
	}
}
