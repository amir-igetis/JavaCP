package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxAlteratingSumOfSquares {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(maxAlternatingSum(nums));
    }

    // greedy + sorting

    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Squaring each element takes O(n) time, sorting the array takes O(nlogn) time, and summing the elements takes O(n) time. Thus, the overall time complexity is dominated by sorting: O(nlogn).
    ///
    /// Note: If quickselect or std::nth_element is used to partition the elements around the median instead of fully sorting, an average time complexity of O(n) can be achieved.
    ///
    /// Space complexity: O(n) or O(logn).
    ///
    /// The space complexity depends on the programming language and sorting implementation:
    ///
    /// In Python, nums.sort() uses Timsort, which takes O(n) auxiliary space. In addition, slicing nums creates new lists consuming O(n) extra space.
    /// In C++, Java, and Go, in-place sorting algorithms typically require O(logn) auxiliary stack space for recursion.
    static long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        long ans = 0;
        for (int i = 0; i < n / 2; i++) {
            ans -= nums[i];
        }
        for (int i = n / 2; i < n; i++) {
            ans += nums[i];
        }
        return ans;

    }
}
