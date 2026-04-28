package dpPrac.zeroOneKnapsack;

public class TargetSum {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2, 3};
//        int sum = 1;

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(targetSum(nums, target));
    }

    static int targetSum(int[] nums, int target) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        if ((sum + target) % 2 != 0 || sum < Math.abs(target))
            return 0;

        int subsetSum = (sum + target) / 2;
        return targetSumHelper(nums, subsetSum);
    }

    private static int targetSumHelper(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }
}
