package striverAToZ.slidingWindowTwoPointer.mediumProbs;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfNiceSubarrays {

    /// Question 6
    /// Problem Statement: Given an array nums and an integer k. An array is called nice if and only if it contains k odd numbers. Find the number of nice subarrays in the given array nums. A subarray is continuous part of the array.
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        System.out.println(numberOfSubarrays(nums, k));

    }

    // brute

    /// Time Complexity: O(N2) ,We use two nested loops to check all possible subarrays. For each subarray, we count the number of odd elements. The outer loop runs from index 0 to N-1, and the inner loop also runs up to N in the worst case. So total iterations can be approximately N * N = O(N2).
    ///
    /// Space Complexity: O(1), No extra space used.
    static int numberOfSubarrays(int[] nums, int k) {
        // Initialize counter for total nice subarrays
        int count = 0;

        // Loop over all starting indices
        for (int start = 0; start < nums.length; start++) {
            // Track number of odd elements in current subarray
            int oddCount = 0;

            // Loop over ending indices starting from 'start'
            for (int end = start; end < nums.length; end++) {
                // Check if current number is odd
                if (nums[end] % 2 != 0)
                    oddCount++;

                // If odd count exceeds k, break (not nice)
                if (oddCount > k)
                    break;

                // If odd count is exactly k, count this subarray
                if (oddCount == k)
                    count++;
            }
        }

        // Return total nice subarrays
        return count;
    }

    // better

    /// Time Complexity:O(N) ,We traverse the array once and each operation (map lookup, insertion, and update) takes constant time. So the total time complexity is linear in terms of the number of elements.
    ///
    /// Space Complexity:O(N) ,We use a hashmap to store the frequency of prefix odd counts. In the worst case, all prefixes have different odd counts, leading to O(n) extra space.
    static int numberOfSubarraysI(int[] nums, int k) {

        // Frequency map to track count of odd-number sums
        Map<Integer, Integer> freq = new HashMap<>();

        // Initial state: zero odd numbers has occurred once
        freq.put(0, 1);

        // Running count of odd numbers in current prefix
        int oddCount = 0;

        // Total number of nice subarrays
        int result = 0;

        // Traverse the entire array
        for (int num : nums) {

            // Check if number is odd
            if (num % 2 == 1) oddCount++;

            // Check if there's a prefix with (oddCount - k)
            if (freq.containsKey(oddCount - k)) {
                result += freq.get(oddCount - k);
            }

            // Update frequency map with current oddCount
            freq.put(oddCount, freq.getOrDefault(oddCount, 0) + 1);
        }

        // Return total result
        return result;
    }

    // optimal

    /// Time Complexity:O(n) ,We scan the array two times using the sliding window helper. Each scan processes every element at most once, making it linear in size of input.
    ///
    /// Space Complexity:O(1) ,No additional space is used except a few integer variables for tracking window bounds and counts. So, constant space.
    private static int countAtMost(int[] nums, int k) {
        int left = 0, res = 0;

        // Traverse through the array
        for (int right = 0; right < nums.length; right++) {
            // If current number is odd, reduce k
            if (nums[right] % 2 != 0)
                k--;

            // Shrink the window until k is valid
            while (k < 0) {
                if (nums[left] % 2 != 0)
                    k++;
                left++;
            }

            // Add valid subarrays ending at right
            res += (right - left + 1);
        }

        // Return result
        return res;
    }

    // Function to return number of subarrays with exactly k odd numbers
    static int numberOfSubarraysII(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }
}