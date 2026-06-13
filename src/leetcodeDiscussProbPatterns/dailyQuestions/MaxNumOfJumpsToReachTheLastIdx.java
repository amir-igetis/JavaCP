package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxNumOfJumpsToReachTheLastIdx {
    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 4, 1, 2};
        int target = 2;
        System.out.println(maximumJumpsI(nums, target));
    }

    //    memoization search tc O(n^2) & sc O(n)
    static int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);
        int ans = dfs(0, nums, target, memo);
        return ans < 0 ? -1 : ans;
    }

    private static int dfs(int i, int[] nums, int target, int[] memo) {
        int n = nums.length;
        if (i == n - 1)
            return 0;
        if (memo[i] != Integer.MIN_VALUE)
            return memo[i];
        int res = Integer.MIN_VALUE;
        for (int j = i + 1; j < n; j++) {
            if (Math.abs(nums[i] - nums[j]) <= target)
                res = Math.max(res, dfs(j, nums, target, memo) + 1);
        }
        return memo[i] = res;
    }

    //    Dynamic Programming tc O(n^2) & sc O(n)
    static int maximumJumpsI(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        return dp[n - 1] < 0 ? -1 : dp[n - 1];
    }
}
