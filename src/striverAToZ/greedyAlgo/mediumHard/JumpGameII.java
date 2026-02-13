package striverAToZ.greedyAlgo.mediumHard;

import java.util.Arrays;

public class JumpGameII {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jumpII(nums));
    }

    // tc O(2 ^n) & sc O(n)
// brute force
    static int jump(int[] nums) {
        return minJumps(nums, 0);
    }

    private static int minJumps(int[] nums, int pos) {
        if (pos >= nums.length - 1)
            return 0;
        if (nums[pos] == 0)
            return Integer.MAX_VALUE;
        int minStep = Integer.MAX_VALUE;
        for (int jump = 1; jump <= nums[pos]; jump++) {
            int subRes = minJumps(nums, pos + jump);
            if (subRes != Integer.MAX_VALUE)
                minStep = Math.min(minStep, 1 + subRes);
        }
        return minStep;
    }

    // better approach tc O(n^2) & o(n)
    static int jumpI(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    // optimal O(n) & sc O(1)
    static int jumpII(int[] nums) {
        int jumps = 0, currEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currEnd) {
                jumps++;
                currEnd = farthest;
            }
        }
        return jumps;
    }
}
