package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinDiffBetweenHighestAndLowestOfKScores {
    public static void main(String[] args) {
        int[] nums = {9, 4, 1, 7};
        int k = 2;
        System.out.println(minimumDifference(nums, k));
    }

    static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        if (k == 1)
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < nums.length; i++)
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);


        return ans;
    }
}
