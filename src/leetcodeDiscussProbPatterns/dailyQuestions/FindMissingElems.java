package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FindMissingElems {
	public static void main(String[] args) {
//		int[] nums = { 1, 4, 2, 5 };
		int[] nums = { 7, 8, 6, 9 };
		List<Integer> ans = findMissingElements(nums);
		for (Integer i : ans)
			System.out.println(i + " ");
		System.out.println();
	}

	static List<Integer> findMissingElements(int[] nums) {
		List<Integer> ans = new ArrayList<Integer>();
		int n = nums.length;
		int[] hash = new int[101];
		Arrays.fill(hash, 0);
		int max = 0;
		int min = 101;
		for (int i = 0; i < n; i++) {
			int node = nums[i];
			hash[node]++;
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
		}
		for (int i = min; i < max; i++)
			if (hash[i] == 0)
				ans.add(i);

		return ans;

	}

	static List<Integer> findMissingElementsI(int[] nums) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		HashSet<Integer> set = new HashSet<>();

		for (int num : nums) {
			set.add(num);
			min = Math.min(min, num);
			max = Math.max(max, num);
		}

		List<Integer> ans = new ArrayList<>();

		for (int i = min; i <= max; i++) {
			if (!set.contains(i)) {
				ans.add(i);
			}
		}

		return ans;
	}
}
