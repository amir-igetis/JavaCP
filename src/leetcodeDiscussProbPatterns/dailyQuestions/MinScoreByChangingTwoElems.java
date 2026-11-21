package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinScoreByChangingTwoElems {
    public static void main(String[] args) {
        int[] nums = {1, 4, 7, 8, 5};
        System.out.println(minimizeSum(nums));
        System.out.println(minimizeSumI(nums));
    }

    // Sorting and Greedy

    /// Let n be the length of the input array nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Sorting the array takes O(nlogn) time.
    ///
    /// Space complexity: O(logn) or O(n).
    ///
    /// The auxiliary space depends on the sorting implementation of the language (e.g., O(logn) for recursive call stacks in C++ or Java, or O(n) for temporary arrays in Python's Timsort).
    static int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(
                Math.min(nums[n - 3] - nums[0], nums[n - 2] - nums[1]),
                nums[n - 1] - nums[2]
        );
    }

// One Pass

    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(n).
    ///
    /// We traverse the array once, and each update involves at most a constant number of comparisons and assignments.
    ///
    /// Space complexity: O(1).
    ///
    /// We only use six variables to maintain the three smallest and three largest elements.
    static int minimizeSumI(int[] nums) {
        int mn1 = Integer.MAX_VALUE,
                mn2 = Integer.MAX_VALUE,
                mn3 = Integer.MAX_VALUE;
        int mx1 = Integer.MIN_VALUE,
                mx2 = Integer.MIN_VALUE,
                mx3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < mn1) {
                mn3 = mn2;
                mn2 = mn1;
                mn1 = x;
            } else if (x < mn2) {
                mn3 = mn2;
                mn2 = x;
            } else if (x < mn3) {
                mn3 = x;
            }

            if (x > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) {
                mx3 = mx2;
                mx2 = x;
            } else if (x > mx3) {
                mx3 = x;
            }
        }

        return Math.min(mx1 - mn3, Math.min(mx2 - mn2, mx3 - mn1));
    }
}
