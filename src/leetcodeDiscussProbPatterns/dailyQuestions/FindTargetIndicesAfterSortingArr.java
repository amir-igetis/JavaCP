package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndicesAfterSortingArr {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 2, 3};
        int target = 2;
        List<Integer> ans = targetIndices(nums, target);
        List<Integer> ansI = targetIndicesI(nums, target);

        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    // sort and traverse

    /// Let n be the length of nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Sorting takes O(nlogn) time, and traversing the array to record the target indices takes O(n) time.
    ///
    /// Space complexity: O(logn) or O(n).
    ///
    /// Sorting algorithms typically require O(logn) auxiliary stack space (e.g., quicksort in C++ and Java) or up to O(n) space (e.g., Timsort in Python).
    static List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                res.add(i);
            }
        }
        return res;

    }

    // count directly

    /// Let n be the length of nums.
    ///
    /// Time complexity: O(n).
    ///
    /// Traversing the array to count elements and constructing the answer array take O(n) time.
    ///
    /// Space complexity: O(1).
    ///
    /// The output array is not included in the space complexity.
    static List<Integer> targetIndicesI(int[] nums, int target) {
        int cnt1 = 0; // Number of elements less than target
        int cnt2 = 0; // Number of elements equal to target

        for (int num : nums) {
            if (num < target) {
                cnt1++;
            } else if (num == target) {
                cnt2++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = cnt1; i < cnt1 + cnt2; i++) {
            res.add(i);
        }
        return res;
    }
}
