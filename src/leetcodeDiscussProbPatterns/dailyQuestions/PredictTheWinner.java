package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;


public class PredictTheWinner {
    public static void main(String[] args) {
        int[] nums = {1, 5, 2};
        System.out.println(predictTheWinner(nums));
    }

    // recursion + memoization
    static boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0)
            return true;
        int[][] dp = new int[n][n];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return maxDiff(0, n - 1, nums, dp) >= 0;
    }

    private static int maxDiff(int i, int j, int[] nums, int[][] dp) {
        if (dp[i][j] != -1)
            return dp[i][j];
        if (i == j)
            return dp[i][j] = nums[i];
        return dp[i][j] = Math.max(nums[i] - maxDiff(i + 1, j, nums, dp), nums[j] - maxDiff(i, j - 1, nums, dp));

    }

    // bottom up dp, space optimized
    static boolean predictTheWinnerI(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0)
            return true;

        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i];
            for (int j = i + 1; j < n; j++)
                dp[j] = Math.max(dp[i] - dp[j], nums[j] - dp[j - 1]);
        }

        return dp[n - 1] >= 0;
    }
}
