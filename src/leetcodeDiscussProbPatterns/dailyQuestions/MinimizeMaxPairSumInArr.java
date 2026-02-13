package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinimizeMaxPairSumInArr {
    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 3};
        System.out.println(minPairSum(nums));
    }

    //    sorting tc O(NlogN) sc O(logN)
    static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            maxSum = Math.max(maxSum, nums[i] +
                    nums[nums.length - 1 - i]);
        }
        return maxSum;
    }
}
