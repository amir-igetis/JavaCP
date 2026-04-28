package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class MinAbsDistBetweenMirrorPairs {
    public static void main(String[] args) {
        int[] nums = {12, 21, 45, 33, 54};
        System.out.println(minMirrorPairDistance(nums));
    }

    // one time traversal tc O(n log c) and sc O(n)
    static int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> prev = new HashMap<>();
        int n = nums.length;
        int ans = n + 1;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (prev.containsKey(x)) {
                ans = Math.min(ans, i - prev.get(x));
            }
            prev.put(reverseNum(x), i);
        }

        return ans == n + 1 ? -1 : ans;
    }

    private static int reverseNum(int x) {
        int y = 0;
        while (x > 0) {
            y = y * 10 + (x % 10);
            x /= 10;
        }
        return y;

    }
}
