package leetcodeContest.contest508;

public class MaxSubArrSumAfterMultiplier {
    public static void main(String[] args) {
        int[] nums = {1, -2, 3, 4, -5};
        int k = 2;
        System.out.println(maxSubarraySum(nums, k));
    }

    static long maxSubarraySum(int[] nums, int k) {
        return Math.max(helper(nums, k, true), helper(nums, k, false));
    }

    private static long helper(int[] nums, int k, boolean flag) {
        long maxi = (long) -1e16;
        long sum0 = (long) -1e16;
        long sum1 = (long) -1e16;
        long sum2 = (long) -1e16;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            long modVal = 0;
            if (flag) {
                modVal = x * k;

            } else modVal = x / k;
            long nextS = Math.max((long) x, sum0 + x);
            long nextS1 = Math.max(modVal, Math.max(sum0 + modVal, sum1 + modVal));
            long nextS2 = Math.max(sum1 + x, sum2 + x);

            sum0 = nextS;
            sum1 = nextS1;
            sum2 = nextS2;
            long currMax = Math.max(sum0, Math.max(sum1, sum2));
            if (currMax > maxi)
                maxi = currMax;
        }
        return maxi;
    }
}
