package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class SmallestMissingNonNegIntAfterOp {
    public static void main(String[] args) {
        int[] nums = {1, -10, 7, 13, 6, 8};
        int value = 5;
        System.out.println(findSmallestIntegerI(nums, value));
    }

    static int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            int mod = ((num % value) + value) % value;
            count.put(mod, count.getOrDefault(mod, 0) + 1);
        }

        int mex = 0;
        while (true) {
            int mod = mex % value;
            int c = count.getOrDefault(mod, 0);
            if (c == 0) break;
            count.put(mod, c - 1);
            mex++;
        }

        return mex;
    }

    // soln from leetcode
    static int findSmallestIntegerI(int[] nums, int value) {
        int n = nums.length, res = 0;
        int[] rem = new int[value];
        for (int x : nums) {
            int r = ((x % value) + value) % value;
            rem[r]++;

        }
        while (rem[res % value]-- > 0)
            res++;

        return res;
    }
}
