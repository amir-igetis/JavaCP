package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class LeftAndRightSumDiff {
    public static void main(String[] args) {
        int[] nums = {10, 4, 8, 3};
        System.out.println(Arrays.toString(leftRightDifference(nums)));
    }

    // prefix sum tc O(n) and sc O(1)
    static int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = leftSum;
            leftSum += nums[i];
        }
        int rightSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = Math.abs(ans[i] - rightSum);
            rightSum += nums[i];
        }
        return ans;
    }
}
