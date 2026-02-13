package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.List;

public class AdjacentIncreasingSubarrDetectionI {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int k = 3;
        System.out.println(hasIncreasingSubarrays(nums, k));
    }

    static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for (int i = 0; i + 2 * k <= n; i++) {
            if (isIncreasing(nums, i, i + k - 1) && isIncreasing(nums, i + k, i + 2 * k - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (nums.get(i) <= nums.get(i - 1))
                return false;
        }
        return true;
    }
}
