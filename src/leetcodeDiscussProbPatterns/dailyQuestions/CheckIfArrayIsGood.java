package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class CheckIfArrayIsGood {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        System.out.println(isGood(nums));
    }

    // sorting tc O(nlogn) & sc O(logn)
    static boolean isGood(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1)
                return false;
        }
        return nums[n] == n;
    }

    // frequency counting tc & sc O(n)
    static boolean isGoodI(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        for (int i : nums) {
            if (i >= n)
                return false;
            if (i < n - 1 && count[i] > 0)
                return false;
            if (i == n - 1 && count[i] > 1)
                return false;

            count[i]++;
        }
        return true;
    }
}
