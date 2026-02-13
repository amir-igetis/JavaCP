package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSpecialTriplets {
    public static void main(String[] args) {
        int[] nums = {6, 3, 6};
        System.out.println(specialTripletsI(nums));
    }

    //    enumeration + counting
    static int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        Map<Integer, Integer> numCount = new HashMap<>();
        Map<Integer, Integer> numPartialCount = new HashMap<>();
        for (int v : nums) {
            numCount.put(v, numCount.getOrDefault(v, 0) + 1);
        }

        long ans = 0;
        for (int v : nums) {
            int target = v * 2;
            int lCount = numPartialCount.getOrDefault(target, 0);
            numPartialCount.put(v, numCount.getOrDefault(v, 0) + 1);

            int rCount = numCount.getOrDefault(target, 0);
            numPartialCount.getOrDefault(target, 0);
            ans = (ans + (long) lCount * rCount) % MOD;
        }

        return (int) ans;
    }

//    enumeration + binary search

    private static final int MOD = 1_000_000_007;

    static int specialTripletsI(int[] nums) {
        Map<Integer, List<Integer>> pos = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            pos.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int target = nums[i] * 2;
            List<Integer> targetPos = pos.get(target);
            if (
                    targetPos != null &&
                            targetPos.size() > 1 &&
                            targetPos.get(0) < i
            ) {
                int[] lr = upperBound(targetPos, i);
                int l = lr[0];
                int r = lr[1];
                if (nums[i] == 0) {
                    l--;
                }
                ans = (int) ((ans + (long) l * r) % MOD);
            }
        }
        return ans;
    }

    private static int[] upperBound(List<Integer> arr, int i) {
        int l = 0;
        int r = arr.size() - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (i >= arr.get(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return new int[] { l + 1, arr.size() - 1 - l };
    }

}
