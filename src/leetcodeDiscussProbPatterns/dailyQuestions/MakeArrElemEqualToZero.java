package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MakeArrElemEqualToZero {
    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 0, 3};
        System.out.println(countValidSelections(nums));
    }

    static int countValidSelectionI(int[] nums) {
        int n = nums.length;
        int res = 0;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + nums[i - 1];
            right[n - i - 1] = right[n - i] + nums[n - i];
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) continue;
            if (left[i] == right[i]) res += 2;
            else if (Math.abs(left[i] - right[i]) == 1) res += 1;
        }

        return res;
    }

    //    optimized soln
    static int countValidSelectionsII(int[] nums) {
        int len = nums.length, count = 0, left = 0;
        int right = Arrays.stream(nums).sum();
        for (int i = 0; i < len; i++) {
            left += nums[i];
            right -= nums[i];
            if (nums[i] != 0) continue;
            if (left == right) count += 2;
            if (Math.abs(left - right) == 1) count++;
        }
        return count;
    }

    //    gpt could not solve it (easy question)
    static int countValidSelections(int[] nums) {
        int n = nums.length;
        int validCount = 0;

        for (int start = 0; start < n; start++) {
            if (nums[start] != 0)
                continue;

            if (canMakeAllZero(nums, start, -1))
                validCount++;
            if (canMakeAllZero(nums, start, +1))
                validCount++;
        }

        return validCount;
    }

    private static boolean canMakeAllZero(int[] original, int start, int direction) {
        int[] nums = original.clone();
        int n = nums.length;
        int curr = start;
        int dir = direction;

        while (curr >= 0 && curr < n) {
            if (nums[curr] == 0) {
                curr += dir;
            } else {
                nums[curr] -= 1;
                dir = -dir;
                curr += curr;
            }
        }

        for (int num : nums) {
            if (num != 0)
                return false;
        }
        return true;
    }
}
