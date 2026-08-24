package striverAToZ.slidingWindowTwoPointer.mediumProbs;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarrayWithSum {

    /// Question 5
    /// Problem Statement: You are given a binary array nums (containing only 0s and 1s) and an integer goal. Return the number of non-empty subarrays of nums that sum to goal. A subarray is a contiguous part of the array.
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        // Output : 4
        System.out.println(numSubarraysWithSum(nums, goal));

    }

    // brute

    /// Time Complexity: O(n²), where n is the length of the array.We are using two nested loops to explore all possible subarrays. Each subarray takes O(1) time to compute the sum cumulatively, so overall O(n²) pairs are checked.
    ///
    /// Space Complexity: O(1), constant space .We only use integer variables to store counts and intermediate sums.
    static int numSubarraysWithSum(int[] nums, int goal) {
        // Variable to store the final count of valid subarrays
        int count = 0;

        // Outer loop to fix the starting index of subarray
        for (int start = 0; start < nums.length; start++) {
            // Variable to store sum of current subarray
            int sum = 0;

            // Inner loop to fix the ending index of subarray
            for (int end = start; end < nums.length; end++) {
                // Add the current element to sum
                sum += nums[end];

                // If subarray sum equals goal, increment count
                if (sum == goal) {
                    count++;
                }
            }
        }

        // Return the total count of valid subarrays
        return count;
    }

    // better

    /// Time Complexity: O(n), where n is the length of the input array . Each element is visited exactly once during the single-pass traversal.
    ///
    /// Space Complexity: O(n), where n is the length of the input array . In the worst case, all cumulative sums are distinct, so the hash map can store up to n unique keys. Thus, the space required grows linearly with the input size.
    static int numSubarraysWithSumI(int[] nums, int goal) {
        // Map to store prefix sum frequencies
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // Initialize count and sum
        int count = 0, sum = 0;

        // Add base case: prefix sum 0 appears once
        prefixSumCount.put(0, 1);

        // Iterate through array
        for (int num : nums) {
            // Add current number to sum
            sum += num;

            // If (sum - goal) exists, add its count to result
            if (prefixSumCount.containsKey(sum - goal)) {
                count += prefixSumCount.get(sum - goal);
            }

            // Update prefix sum count
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }

        // Return final count
        return count;
    }

    // optimal

    /// Time Complexity: O(n), where n is the size of the input array.This is because the algorithm uses the sliding window technique twice (in the two calls to atMost). Each `atMost` function runs in linear time , the left and right pointers only move forward, never backward, so the total number of operations is at most 2n.
    ///
    /// Space Complexity: O(1), constant extra space.Only a few integer variables are used .
    static int numSubarraysWithSumII(int[] nums, int goal) {
        // Return difference between atMost(goal) and atMost(goal - 1)
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    // Helper method to count subarrays with sum at most k
    private static int atMost(int[] nums, int k) {
        // No valid subarray for negative sum
        if (k < 0)
            return 0;

        int left = 0;
        int sum = 0;
        int count = 0;

        // Traverse array using right pointer
        for (int right = 0; right < nums.length; right++) {
            // Add current element to sum
            sum += nums[right];

            // Shrink window if sum exceeds k
            while (sum > k) {
                sum -= nums[left];
                left++;
            }

            // Add number of valid subarrays ending at right
            count += (right - left + 1);
        }

        return count;
    }

}
