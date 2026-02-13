package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class ValidTriangleNumber {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};
        System.out.println(triangleNumber(nums));
    }

    static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int k = n - 1; k >= 2; k--) {
            int start = 0, end = k - 1;
            while (start < end) {
                if (nums[start] + nums[end] > nums[k]) {
                    count += (end - start);
                    end--;
                } else start++;
            }
        }
        return count;
    }
}
