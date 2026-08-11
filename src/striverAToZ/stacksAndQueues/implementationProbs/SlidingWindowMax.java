package striverAToZ.stacksAndQueues.implementationProbs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMax {

    ///  Question 1
    /// Problem Statement: Given an array of integers arr, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window..

    public static void main(String[] args) {
        int[] arr = {4, 0, -1, 3, 5, 3, 6, 8};
        int k = 3;

        List<Integer> ans = maxSlidingWindow(arr, k);

        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    // brute force

    /// Time Complexity: O(n * k) Each of the (n - k + 1) windows is scanned completely to find its maximum. In worst-case, each window of size k requires O(k) operations.
    ///
    /// Space Complexity: O(1) We are only using output list which does not count as extra space in space complexity analysis. No additional data structures used.
    static List<Integer> maxSlidingWindow(int[] nums, int k) {
        // Result list to store maximums
        List<Integer> result = new ArrayList<>();

        // Loop through the array till the window can slide
        for (int i = 0; i <= nums.length - k; i++) {
            // Initialize maxVal with the first element in the window
            int maxVal = nums[i];

            // Traverse the current window
            for (int j = i; j < i + k; j++) {
                // Update maxVal if a bigger number is found
                maxVal = Math.max(maxVal, nums[j]);
            }

            // Add the max value of this window to the result
            result.add(maxVal);
        }

        // Return final result
        return result;
    }

    // optimal

    /// Time Complexity: O(n) Each element is pushed and popped from the deque at most once, so overall traversal is linear.
    ///
    /// Space Complexity: O(k) Deque stores at most k elements at any time, one for each index in the window.
    static List<Integer> maxSlidingWindowI(int[] nums, int k) {
        // Deque to store indices of useful elements
        Deque<Integer> dq = new LinkedList<>();

        // List to store result
        List<Integer> result = new ArrayList<>();

        // Loop through each element
        for (int i = 0; i < nums.length; i++) {
            // Remove indices out of current window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller values from the back of deque
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(i);

            // Add to result once the first window is formed
            if (i >= k - 1) {
                result.add(nums[dq.peekFirst()]);
            }
        }

        // Return result list
        return result;
    }
}