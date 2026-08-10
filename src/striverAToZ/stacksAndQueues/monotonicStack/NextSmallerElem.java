package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElem {

    /// Problem Statement: Given an array of integers arr, your task is to find the Next Smaller Element (NSE) for every element in the array.
    /// The Next Smaller Element for an element x is defined as the first element to the right of x that is smaller than x.
    /// If there is no smaller element to the right, then the NSE is -1.

    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};

        int[] ans = nextSmallerElement(arr);

        // Print the result
        System.out.print("The next smaller elements are: ");
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    // brute

    /// Time Complexity: O(N^2), since for each of the N elements, we might need to look at up to N-1 elements ahead.
    ///
    /// Space Complexity: O(N), since we are using an output array of size N.

    static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;

        // Initialize the answer array with -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Traverse each element in the array
        for (int i = 0; i < n; i++) {
            int curr = arr[i];

            // Look ahead to find the next smaller element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < curr) {
                    // Store and break on finding the smaller element
                    ans[i] = arr[j];
                    break;
                }
            }
        }

        // Return the answer array
        return ans;
    }

    /// Time Complexity: O(N), since each element is pushed and popped at most once.
    ///
    /// Space Complexity: O(N), since stack may hold up to N elements in the worst case.

    // optimal
    static int[] nextSmallerElementI(int[] arr) {
        int n = arr.length;

        // Stack to keep potential next smaller elements
        Stack<Integer> st = new Stack<>();

        // Initialize result array with -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Pop elements from stack that are not smaller
            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }

            // If stack not empty, top is the next smaller element
            if (!st.isEmpty()) {
                ans[i] = st.peek();
            }

            // Push current element to stack
            st.push(arr[i]);
        }

        // Return result
        return ans;
    }
}
