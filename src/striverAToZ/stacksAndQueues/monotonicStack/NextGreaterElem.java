package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Stack;

public class NextGreaterElem {

    /// Problem Statement: Given an integer array A, return the next greater element for every element in A. The next greater element for an element x is the first element greater than x that we come across while traversing the array in a clockwise manner. If it doesn't exist, return -1 for this element.

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10};
        int[] ans = nextGreater(nums);

        for (int x : ans) {
            System.out.print(x + " ");
        }

        System.out.println();
    }

    /// Time Complexity: O(N), we traverse the entire array once and find next greater element in linear time.
    /// Space Complexity: O(N), additional space used for resultant array and stack.

    static int[] nextGreater(int[] nums) {
        // Stack to store elements
        Stack<Integer> st = new Stack<>();

        // Result array of same size
        int n = nums.length;
        int[] res = new int[n];

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Pop all smaller or equal elements
            while (!st.isEmpty() && st.peek() <= nums[i]) {
                st.pop();
            }

            // If stack is empty, no greater element
            if (st.isEmpty()) res[i] = -1;

                // Else top of stack is the answer
            else res[i] = st.peek();

            // Push current element
            st.push(nums[i]);
        }

        // Return the result
        return res;
    }
}
