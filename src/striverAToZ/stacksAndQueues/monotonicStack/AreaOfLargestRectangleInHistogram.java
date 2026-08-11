package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Stack;

public class AreaOfLargestRectangleInHistogram {

    /// Question 10
    /// Problem Statement: Given an array of integers heights representing the histogram's bar height where the width of each bar is 1 return the area of the largest rectangle in histogram.
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3, 1};
        int n = arr.length;

        System.out.println("The largest area in the histogram is " + largestArea(arr, n));

    }

    // brute

    /// Time Complexity: O(N*N), since nested for loops are used
    /// Space Complexity: O(1). No extra space used
    static int largestArea(int[] arr, int n) {
        int maxArea = 0;

        // Loop through all possible start indices
        for (int i = 0; i < n; i++) {
            int minHeight = Integer.MAX_VALUE;

            // Loop through all possible end indices
            for (int j = i; j < n; j++) {
                // Update minimum height in current range
                minHeight = Math.min(minHeight, arr[j]);

                // Calculate area of current rectangle
                int area = minHeight * (j - i + 1);

                // Update maxArea
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    /// Time Complexity: O(N). Single loop at the end using O(N)
    /// Space Complexity: O(3N) where 3 is for the stack, left small array and a right small array
    // optimal 1
    static int largestRectangleAreaI(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int[] leftsmall = new int[n];
        int[] rightsmall = new int[n];

        // Compute NSL (Nearest Smaller to Left)
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            leftsmall[i] = st.isEmpty() ? 0 : st.peek() + 1;
            st.push(i);
        }

        // Clear the stack for reuse
        st.clear();

        // Compute NSR (Nearest Smaller to Right)
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            rightsmall[i] = st.isEmpty() ? n - 1 : st.peek() - 1;
            st.push(i);
        }

        // Compute max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = rightsmall[i] - leftsmall[i] + 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }

        return maxArea;
    }

    // optimal 2

    /// Time Complexity: O(N) + O(N). For loop used along with a while loop
    /// Space Complexity: O(N). Used for stack
    static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // When current bar is less than the bar on top of stack, calculate area
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= (i < n ? heights[i] : 0))) {
                int height = heights[stack.pop()];

                int width;
                if (stack.isEmpty()) {
                    width = i; // All previous were taller
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}