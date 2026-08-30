package leetcodeContest.weekly517;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOpsToFormSubsetSumI {
    public static void main(String[] args) {
        int[] nums = { 5, 6, 10 };
        int sum = 4;
        int[] nums2 = { 10, 2 };
        int sum2 = 13;
        int[] nums3 = { 6, 3 };
        int sum3 = 8;
        System.out.println(minOperations(nums3, sum3));
        System.out.println(minOperations(nums, sum));
        System.out.println(minOperations(nums2, sum2));
    }

    static int minOperations(int[] nums, int sum) {

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i : nums) {
            List<int[]> temp = new ArrayList<>();
            temp.add(new int[] { i, 0 });
            int val = i * 2;
            int ops = 1;
            while (val <= sum) {
                temp.add(new int[] { val, ops });
                val *= 2;
                ops++;
            }
            val = i / 2;
            ops = 1;
            while (val > 0) {
                temp.add(new int[] { val, ops });
                val /= 2;
                ops++;
            }

            for (int j = sum; j >= 0; j--) {
                int minCost = dp[j];
                for (int[] pair : temp) {
                    int pairVal = pair[0];
                    int pairCosty = pair[1];
                    if (j >= pairVal && dp[j - pairVal] != Integer.MAX_VALUE)
                        minCost = Math.min(minCost, dp[j - pairVal] + pairCosty);

                }
                dp[j] = minCost;
            }
        }
        if (dp[sum] == Integer.MAX_VALUE)
            return -1;
        else
            return dp[sum];
        // return dp[sum] == Integer.MAX_VALUE ? -1 : dp[sum];
    }
}
