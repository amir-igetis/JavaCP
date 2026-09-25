package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTwoNonOverlappingSubArraysEachWithTargetSum {

    public static void main(String[] args) {
        int[] arr = {7, 3, 4, 7};
        int target = 7;
        System.out.println("First method " + minSumOfLengths(arr, target));
        System.out.println("second method optimized " + minSumOfLengthsI(arr, target));
        System.out.println("First method optimized " + minSumOfLengthsII(arr, target));
    }

    // Prefix Sum + Dynamic Programming + Hash Table

    /// Let n be the length of the array arr.
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse the array once, and hash table insertions and lookups take O(1) time on average.
    ///
    /// Space complexity: O(n).
    ///
    /// The hash table requires O(n) space.
    static int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, -1);
        int n = arr.length;
        int s = 0;
        int ans = n + 1;
        int minL = n;
        for (int i = 0; i < n; i++) {
            s += arr[i];
            if (pos.containsKey(s - target)) {
                int j = pos.get(s - target);
                int len = i - j;
                ans = Math.min(ans, len + (j == -1 ? n : arr[j]));
                minL = Math.min(minL, len);
            }
            arr[i] = minL;
            pos.put(s, i);
        }
        return ans == n + 1 ? -1 : ans;
    }

    // Sliding Window + Dynamic Programming

    /// Let n be the length of the array arr.
    ///
    /// Time complexity: O(n).
    ///
    /// Sliding window and dynamic programming are performed during one traversal of arr.
    ///
    /// Space complexity: O(n).
    ///
    /// The array dp requires O(n) space.
    static int minSumOfLengthsI(int[] arr, int target) {
        int n = arr.length,
                ans = n + 1,
                sum = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        for (int l = 0, r = 0; r < n; r++) {
            sum += arr[r];
            while (sum > target) sum -= arr[l++];
            dp[r + 1] = dp[r];
            if (sum == target) {
                ans = Math.min(ans, r - l + 1 + dp[l]);
                dp[r + 1] = Math.min(dp[r], r - l + 1);
            }
        }
        return ans == n + 1 ? -1 : ans;
    }

    // better first approach
    static int minSumOfLengthsII(int[] arr, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, -1);
        int n = arr.length;
        int sum = 0;
        int ans = n + 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            // Carry forward the best subarray found so far
            dp[i + 1] = dp[i];
            if (pos.containsKey(sum - target)) {

                int j = pos.get(sum - target);
                int len = i - j;

                // Previous subarray must finish before j
                if (dp[j + 1] < n) {
                    ans = Math.min(
                            ans,
                            len + dp[j + 1]
                    );
                }
                // Update best subarray ending at or before i
                dp[i + 1] = Math.min(
                        dp[i + 1],
                        len
                );
            }
            pos.put(sum, i);
        }
        return ans == n + 1 ? -1 : ans;
    }


    // my soln
    static int minSumOfLengthsIV(int[] arr, int target) {
        int n = arr.length;
        int ans = n + 1;
        int sum = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        int l = 0, r = 0;
        while (r < n) {
            sum += arr[r];
            while (sum > target) {
                sum -= arr[l++];
            }
            dp[r + 1] = dp[r];
            if (sum == target) {
                ans = Math.min(ans, r - l + 1 + dp[l]);
                dp[r + 1] = Math.min(dp[r], r - l + 1);
            }
            r++;
        }
        return (ans == n + 1) ? -1 : ans;
    }
}
