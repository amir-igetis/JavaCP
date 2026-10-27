package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class ApplyOpsOnArrToMaximizeSumOfSquares {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(2);
        nums.add(6);
        nums.add(5);
        nums.add(8);
        int k = 2;
        System.out.println(maxSum(nums, k));
    }

    // greedy

    /// Let n be the length of the array nums, M be its maximum element, and k be the number of elements to choose.
    ///
    /// Time complexity: O(nlogM).
    ///
    /// Traversing nums to count the 1 bits at each binary position takes O(nlogM) time. Constructing k elements and calculating the maximum sum of squares takes O(klogM) time. Since k≤n in this problem, the total time complexity is O(nlogM).
    ///
    /// Space complexity: O(logM).
    ///
    /// Storing the number of 1 bits at each binary position takes O(logM) space.
    static int maxSum(List<Integer> nums, int k) {
        final int MOD = 1000000007;
        int[] cnt = new int[30];

        // Count the number of 1 bits at each position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }

        long res = 0;
        for (int i = 0; i < k; i++) {
            long cur = 0;
            // Construct the current number from the most significant bit to the least significant bit
            for (int j = 29; j >= 0; j--) {
                if (cnt[j] > 0) {
                    cur |= 1L << j;
                    cnt[j]--;
                }
            }
            res = (res + cur * cur) % MOD;
        }

        return (int) res;
    }
}
