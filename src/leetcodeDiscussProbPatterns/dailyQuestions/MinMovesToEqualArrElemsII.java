package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinMovesToEqualArrElemsII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(minMoves2(nums));
        System.out.println(minMoves2I(nums));
    }

    // Sorting

    /// Time Complexity: O(N log N) due to the sorting step.
    ///
    /// Space Complexity: O(1) (or O(log N) depending on the sorting algorithm implementation under the hood of Arrays.sort).
    static int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int moves = 0;
        int left = 0;
        int right = nums.length - 1;

        // Accumulate the difference between the outer elements
        // as we move inward toward the median.
        while (left < right) {
            moves += nums[right] - nums[left];
            left++;
            right--;
        }

        return moves;
    }

    // Quickselect

    /// Time Complexity: O(N) on average to find the median, followed by an O(N) pass to calculate moves. The worst-case is O(N^2) if the array is already sorted and a poor pivot is chosen, though randomized pivoting can prevent this.
    ///
    /// Space Complexity: O(1) auxiliary space (or O(log N) call stack space due to recursion).
    static int minMoves2I(int[] nums) {
        int n = nums.length;
        // Find the exact median element
        int median = quickSelect(nums, 0, n - 1, n / 2);

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    // Quickselect algorithm to find the k-th smallest element
    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == k) {
            return nums[pivotIndex];
        } else if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right]; // Choosing the last element as pivot
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
