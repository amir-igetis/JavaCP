package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class TransformedArr {
    public static void main(String[] args) {
//        int[] nums = {3, -2, 1, 1};
        int[] nums = {-1, 4, -1};

        System.out.println(Arrays.toString(constructTransformedArray(nums)));
    }

    static int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                res[i] = nums[i];
                continue;
            }
            int newIdx = (i + nums[i]) % n;
            if (newIdx < 0)
                newIdx += n;

            res[i] = nums[newIdx];
        }
        return res;
    }
}
