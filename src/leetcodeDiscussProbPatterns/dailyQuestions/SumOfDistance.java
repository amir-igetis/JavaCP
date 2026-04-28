package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class SumOfDistance {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1, 2};
        System.out.println(Arrays.toString(nums));
    }

    // grouping and prefix sum tc & sc O(n)
    static long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums[i], k ->
                    new ArrayList<>()).add(i);
        }
        long[] res = new long[n];
        for (List<Integer> group : mp.values()) {
            long total = 0;
            for (int idx : group)
                total += idx;

            long prefixTotal = 0;
            int size = group.size();
            for (int i = 0; i < size; i++) {
                int idx = group.get(i);
                res[idx] = total - prefixTotal * 2 + (long) idx * (2 * i - size);
                prefixTotal += idx;
            }
        }
        return res;

    }
}
