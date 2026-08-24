package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributeElementsIntoTwoArrayI {
	public static void main(String[] args) {
		int[] nums = { 2, 1, 3 };
		System.out.println(Arrays.toString(resultArray(nums)));
	}

// Two List Simulation
/// Let n be the length of the array nums.
/// 
/// Time complexity: O(n).
/// 
/// We traverse the array once, with each comparison and insertion taking O(1) time. Concatenating the two lists also takes O(n) time.
/// 
/// Space complexity: O(n).
/// 
/// The two lists together contain all n elements of the array.
	static int[] resultArray(int[] nums) {
		int n = nums.length;
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();
		arr1.add(nums[0]);
		arr2.add(nums[1]);
		for (int i = 2; i < n; i++) {
			if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
				arr1.add(nums[i]);
			} else {
				arr2.add(nums[i]);
			}
		}
		int[] res = new int[n];
		int idx = 0;
		for (int x : arr1) {
			res[idx++] = x;
		}
		for (int x : arr2) {
			res[idx++] = x;
		}
		return res;

	}

// Single Array with Two Pointers
/// Let n be the length of the array nums.
/// 
/// Time complexity: O(n).
/// 
/// We traverse the array in O(n) time and reverse part of the array in O(n) time. Therefore, the overall time complexity is O(n).
/// 
/// Space complexity: O(1).
/// 
/// Apart from the output array itself, we only use a constant number of additional variables, so the extra space complexity is O(1).
	static int[] resultArrayI(int[] nums) {
		int n = nums.length;
		int[] arr = new int[n];
		arr[0] = nums[0];
		arr[n - 1] = nums[1];
		int idx = 0, revIdx = n - 1;
		for (int i = 2; i < n; i++) {
			if (arr[idx] > arr[revIdx]) {
				arr[++idx] = nums[i];
			} else {
				arr[--revIdx] = nums[i];
			}
		}
		for (int l = revIdx, r = n - 1; l < r; l++, r--) {
			int tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
		}
		return arr;
	}

}
