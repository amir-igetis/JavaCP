package LeetcodeInterview.kadanesAlgo;

public class MaxSumCircularSubarr {
    public static void main(String[] args) {
//
        int[] nums = {1, -2, 3, -2};
        System.out.println(maxSubarraySumCircular(nums));
    }

    static int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int maxKadane = kadane(nums, true);
        int minKadane = kadane(nums, false);

        for (int num : nums)
            totalSum += num;
        if (totalSum == minKadane)
            return maxKadane;

        return Math.max(maxKadane, totalSum - minKadane);
    }

    private static int kadane(int[] nums, boolean findMax) {
        int currSum = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (findMax)
                currSum = Math.max(nums[i], currSum + nums[i]);
            else
                currSum = Math.min(nums[i], currSum + nums[i]);

            res = findMax ? Math.max(res, currSum) :
                    Math.min(res, currSum);
        }

        return res;
    }
}

