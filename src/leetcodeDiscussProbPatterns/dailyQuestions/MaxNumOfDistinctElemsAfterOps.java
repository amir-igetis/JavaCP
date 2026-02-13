package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxNumOfDistinctElemsAfterOps {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 4};
        int k = 2;
        System.out.println(maxDistinctElements(nums, k));
    }

    static int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int nextAvailable = Integer.MIN_VALUE;
        int distinct = 0;
        for (int num : nums) {
            int start = num - k;
            int end = num + k;
            if (nextAvailable < start)
                nextAvailable = start;

            if (nextAvailable <= end) {
                distinct++;
                nextAvailable++;
            }
        }
        return distinct;
    }
}
