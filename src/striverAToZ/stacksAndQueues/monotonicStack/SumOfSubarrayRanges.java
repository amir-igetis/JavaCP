package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Stack;

public class SumOfSubarrayRanges {

    /// Problem Statement: Given an integer array nums, determine the range of a subarray, defined as the difference between the largest and smallest elements within the subarray. Calculate and return the sum of all subarray ranges of nums.
    ///
    /// A subarray is defined as a contiguous, non-empty sequence of elements within the array.
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        long ans = subArrayRanges(arr);

        // Print the result
        System.out.println("The sum of subarray ranges is: " + ans);

    }

    // brute

    /// Time Complexity: O(N²), since we are using two nested loops.
    ///
    /// Space Complexity: O(1), since we are using only a couple of variables.
    static long subArrayRanges(int[] arr) {
        // Size of array
        int n = arr.length;

        // Variable to store the final sum
        long sum = 0;

        // Traverse each starting index of subarrays
        for (int i = 0; i < n; i++) {

            // Initialize smallest and largest for current subarray
            int smallest = arr[i];
            int largest = arr[i];

            // Traverse subarrays starting from i
            for (int j = i; j < n; j++) {
                // Update smallest element seen so far
                smallest = Math.min(smallest, arr[j]);

                // Update largest element seen so far
                largest = Math.max(largest, arr[j]);

                // Add the current range (max - min) to the total sum
                sum += (largest - smallest);
            }
        }

        // Return the computed total sum
        return sum;
    }

    // optimal

    /// Time Complexity: O(N), since calculating the sum of subarray maximums takes O(N) time and calculating the sum of subarray minimums takes O(N) time.
    ///
    /// Space Complexity: O(N), since calculating the sum of subarray maximums requires O(N) space and calculating the sum of subarray minimums requires O(N) space.
    private static int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Next Greater Elements
    private static int[] findNGE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Elements
    private static int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Previous Greater or Equal Elements
    private static int[] findPGEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }
        return ans;
    }

    // Function to compute sum of subarray minimums
    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            long freq = 1L * left * right;
            sum += freq * arr[i];
        }
        return sum;
    }

    // Function to compute sum of subarray maximums
    private static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] nge = findNGE(arr);
        int[] pgee = findPGEE(arr);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int left = i - pgee[i];
            int right = nge[i] - i;
            long freq = 1L * left * right;
            sum += freq * arr[i];
        }
        return sum;
    }

    // Function to compute total sum of subarray ranges (max - min)
    static long subArrayRangesI(int[] arr) {
        return sumSubarrayMaxs(arr) - sumSubarrayMins(arr);
    }

}
