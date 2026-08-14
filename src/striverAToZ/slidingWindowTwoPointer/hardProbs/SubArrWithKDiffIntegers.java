package striverAToZ.slidingWindowTwoPointer.hardProbs;

import java.util.HashMap;
import java.util.Map;

public class SubArrWithKDiffIntegers {

    /// Question 2
    /// Problem Statement: You are given an integer array nums and an integer k. Return the number of good subarrays of nums.
    ///
    /// A good subarray is defined as a contiguous subarray of nums that contains exactly k distinct integers. A subarray is a contiguous part of the array.
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(subarraysWithKDistinct(nums, k));

    }

    // brute

    /// Time Complexity:O(N²*K) ,We check all possible subarrays by iterating over all start and end indices. For each subarray, we count the number of distinct integers using a HashSet or frequency map, which can take up to O(K) time per check. So overall it becomes O(N²*K) where N is the size of the array and K is the number of unique elements allowed.
    ///
    /// Space Complexity:O(K) ,For each subarray, we maintain a HashSet or HashMap to store the distinct elements in it. In the worst case, this set can grow to size K.
    static int subarraysWithKDistinct(int[] nums, int k) {
        // Store the total count of valid subarrays
        int count = 0;

        // Iterate through all possible starting points
        for (int i = 0; i < nums.length; i++) {

            // Use a hashmap to store frequency of elements
            Map<Integer, Integer> map = new HashMap<>();

            // Iterate through all possible end points
            for (int j = i; j < nums.length; j++) {

                // Update the frequency of the current number
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

                // If size equals k, it's a valid subarray
                if (map.size() == k)
                    count++;

                // If more than k distinct, break early
                if (map.size() > k)
                    break;
            }
        }

        // Return total valid subarrays
        return count;
    }

    // optimal

    /// Time Complexity:O(N) ,where n is the length of the array. Both calls to atMostK() are linear.
    ///
    /// Space Complexity:O(K) ,where k is the number of distinct elements in the current window. We use a hash map to store frequency counts, which in the worst case could grow to the number of unique elements in the array.
    private static int atMostK(int[] nums, int K) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;

        // Traverse the array with right pointer
        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            if (freq.get(nums[right]) == 1) {
                K--;
            }

            // Shrink the window if K becomes negative
            while (K < 0) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    K++;
                }
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    // Main function to return number of subarrays with exactly K distinct integers
    static int subarraysWithKDistinctI(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
}
