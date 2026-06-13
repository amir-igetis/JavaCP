package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class RotateFunction {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 6};
        System.out.println(maxRotateFunction(nums));
    }

    static int maxRotateFunction(int[] nums) {
        int f = 0;
        int n = nums.length;
        int numSum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++)
            f += i * nums[i];

        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }

        return res;
    }
}
