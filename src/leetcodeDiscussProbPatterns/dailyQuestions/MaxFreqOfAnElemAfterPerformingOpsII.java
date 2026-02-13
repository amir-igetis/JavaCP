package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxFreqOfAnElemAfterPerformingOpsII {
    public static void main(String[] args) {
        int[] nums = {1, 4, 5};
        int k = 1, numOperations = 2;
        System.out.println(maxFrequency(nums, k, numOperations));
    }

    static int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        int l = 0, r = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int L = Math.max(1, x - k);
            int R = Math.min(nums[n - 1], x + k);

            int f = 1;
            int j = i + 1;
            while (j < n && nums[j] == x) {
                f++;
                j++;
            }
            i = j - 1;
            while (l < n && nums[l] < L)
                l++;

            r = Math.max(r, i);
            while (r + 1 < n && nums[r + 1] <= R)
                r++;

            int count = r - l + 1;

            ans = Math.max(ans, f + Math.min(count - f, numOperations));
        }

        l = 0;
        for (r = 0; r < n; r++) {
            int x = nums[r];
            int L = Math.max(1, x - 2 * k);
            while (l < r && nums[l] < L)
                l++;

            ans = Math.max(ans, Math.min(r - l + 1, numOperations));
        }

        return ans;
    }
}
