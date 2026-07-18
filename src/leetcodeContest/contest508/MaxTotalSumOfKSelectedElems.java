package leetcodeContest.contest508;

import java.util.Arrays;

public class MaxTotalSumOfKSelectedElems {
    public static void main(String[] args) {
        int[] nums = {6, 1, 2, 9};
        int k = 3, mul = 2;
        System.out.println(maxSum(nums, k, mul));
    }

    static long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        long[] topK = new long[k];
        long[] multi = new long[k];

        for (int i = 0; i < k; i++) {
            topK[i] = nums[nums.length - 1 - i];
            multi[i] = Math.max(1L, (long) mul - i);
        }

        Arrays.sort(topK);
        Arrays.sort(multi);
        long sum = 0;
        for (int i = 0; i < k; i++)
            sum += topK[i] * multi[i];

        return sum;
    }
}
