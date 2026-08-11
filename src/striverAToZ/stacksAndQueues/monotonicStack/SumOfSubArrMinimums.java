package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Stack;

public class SumOfSubArrMinimums {

    /// Question 6
    /// Problem Statement: Given an array of integers arr of size n, calculate the sum of the minimum value in each (contiguous) subarray of arr. Since the result may be large, return the answer modulo 10⁹ +7.
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5};

        int ans = sumSubarrayMins(arr);

        // Print the result
        System.out.println("The sum of minimum value in each subarray is: " + ans);

    }

    // brute force

    /// Time Complexity: O(N²), since we are using two nested loops.
    ///
    /// Space Complexity: O(1), as we are not using any extra space except for the input array and a few variables.
    static int sumSubarrayMins(int[] arr) {
        // Size of the array
        int n = arr.length;

        // Modulo value to prevent integer overflow
        int mod = (int) 1e9 + 7;

        // Variable to store the total sum
        int sum = 0;

        // Traverse each starting index of subarrays
        for (int i = 0; i < n; i++) {
            // Initialize the minimum as the current element
            int mini = arr[i];

            // Traverse all subarrays starting at index i
            for (int j = i; j < n; j++) {
                // Update the minimum in the current subarray
                mini = Math.min(mini, arr[j]);

                // Add the current minimum to the total sum
                sum = (sum + mini) % mod;
            }
        }

        // Return the total computed sum
        return sum;
    }

    /// Time Complexity: O(N), since finding the indices of next smaller elements and previous smaller elements take O(2*N) time each and calculating the sum of subarrays minimum takes O(N) time.
    ///
    /// Space Complexity: O(N), since finding the indices of the next smaller elements and previous smaller elements takes O(N) space each due to stack space and storing the indices of the next smaller elements and previous smaller elements takes O(N) space each.

    // Function to find indices of Next Smaller Element (NSE)
    private static int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are greater or equal to current
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack is empty, NSE doesn't exist → set to n
            ans[i] = !st.isEmpty() ? st.peek() : n;

            // Push current index to stack
            st.push(i);
        }

        // Return NSE indices
        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Element (PSEE)
    private static int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements greater than current
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack is empty, PSEE doesn't exist → set to -1
            ans[i] = !st.isEmpty() ? st.peek() : -1;

            // Push current index to stack
            st.push(i);
        }

        // Return PSEE indices
        return ans;
    }

    // Function to compute the sum of minimums in all subarrays
    static int sumSubarrayMinsI(int[] arr) {
        int n = arr.length;

        // Get NSE and PSEE indices
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);

        // Modulo for large results
        int mod = (int) 1e9 + 7;
        int sum = 0;

        // Traverse each element to compute its contribution
        for (int i = 0; i < n; i++) {
            // Count of elements to the left including current
            int left = i - psee[i];

            // Count of elements to the right including current
            int right = nse[i] - i;

            // Total subarrays where arr[i] is the minimum
            long freq = left * right * 1L;

            // Contribution = frequency * value
            int val = (int) ((freq * arr[i]) % mod);

            // Add contribution to sum
            sum = (sum + val) % mod;
        }

        // Return the final sum
        return sum;
    }

}
