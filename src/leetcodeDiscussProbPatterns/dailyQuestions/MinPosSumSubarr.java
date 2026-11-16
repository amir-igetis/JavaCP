package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class MinPosSumSubarr {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(java.util.Arrays.asList(3, -2, 1, 4));
        int l = 2;
        int r = 3;
        System.out.println(minimumSumSubarray(nums, l, r));
        System.out.println(minimumSumSubarrayI(nums, l, r));
        System.out.println(minimumSumSubarrayII(nums, l, r));
        System.out.println(minimumSumSubarrayIII(nums, l, r));
    }

    // Brute Force

    /// tc O(nr) sc O(1)
    static int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size(), best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < Math.min(i + r, n); j++) {  // nums[i..j], at most r long
                s += nums.get(j);
                if (j - i + 1 >= l && 0 < s && s < best)
                    best = s;
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    // One Window Per Length

    /// O(n⋅(r−l+1)) Time, O(1) Space.
    static int minimumSumSubarrayI(List<Integer> nums, int l, int r) {
        int n = nums.size(), best = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            int s = nums.subList(0, k - 1).stream().mapToInt(x -> x).sum();
            for (int i = k - 1; i < n; i++) {
                s += nums.get(i);  // the window nums[i - k + 1 .. i]
                if (0 < s && s < best)
                    best = s;
                s -= nums.get(i - k + 1);
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    /// With prefix sums, every subarray is one subtraction.
    static int minimumSumSubarrayII(List<Integer> nums, int l, int r) {
        int[] pre = new int[nums.size() + 1];  // pre[i] = sum(nums[:i])
        for (int i = 0; i < nums.size(); i++) pre[i + 1] = pre[i] + nums.get(i);
        return IntStream.rangeClosed(l, r)
                .flatMap(k -> IntStream.range(0, pre.length - k).map(i -> pre[i + k] - pre[i]))
                .filter(s -> s > 0)
                .min()
                .orElse(-1);
    }

    // Sorted Window of Starts

    /// O(nlogn) Time and O(n) Space.
    static int minimumSumSubarrayIII(List<Integer> nums, int l, int r) {
        int n = nums.size(), best = Integer.MAX_VALUE;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums.get(i);
        TreeMap<Integer, Integer> starts = new TreeMap<>();  // pre[i] -> how many starts that fit the end j have it
        for (int j = l; j <= n; j++) {
            starts.merge(pre[j - l], 1, Integer::sum);  // start j - l: length l
            if (j > r)
                starts.compute(pre[j - r - 1], (key, c) -> c == 1 ? null : c - 1);  // start j - r - 1: length r + 1, too long
            Integer below = starts.lowerKey(pre[j]);  // the largest pre[i] < pre[j]
            if (below != null)
                best = Math.min(best, pre[j] - below);
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}




















