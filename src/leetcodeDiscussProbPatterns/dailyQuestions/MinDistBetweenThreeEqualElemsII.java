package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinDistBetweenThreeEqualElemsII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3};
        System.out.println(minimumDistance(nums));
    }

    static int minimumDistance(int[] nums) {

        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Map<Integer, Integer> occur = new HashMap<>();
        int ans = n + 1;

        for (int i = n - 1; i >= 0; i--) {
            if (occur.containsKey(nums[i])) {
                next[i] = occur.get(nums[i]);
            }
            occur.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            int secondPos = next[i];
            if (secondPos != -1) {
                int thirdPos = next[secondPos];
                if (thirdPos != -1) {
                    ans = Math.min(ans, thirdPos - i);
                }
            }
        }

        return ans == n + 1 ? -1 : ans * 2;

    }
}
