package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.List;

public class AdjacentIncreasingSubarrDetectionII {
    public static void main(String[] args) {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        System.out.println(maxIncreasingSubarrays(nums));
    }

    static int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        if (n < 2) return 0;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1))
                left[i] = left[i - 1] + 1;
            else
                left[i] = 1;
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1))
                right[i] = right[i + 1] + 1;
            else
                right[i] = 1;
        }

        int maxK = 0;
        for (int i = 0; i < n - 1; i++) {
            int k = Math.min(left[i], right[i + 1]);
            maxK = Math.max(maxK, k);
        }

        return maxK;
    }
}
