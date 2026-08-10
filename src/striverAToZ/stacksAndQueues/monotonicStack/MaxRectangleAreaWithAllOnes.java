package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class MaxRectangleAreaWithAllOnes {

    /// Problem Statement: Given a m x n binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.


    public static void main(String[] args) {
        // Sample matrix input
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(maximalRectangle(matrix));

    }

    /// Time Complexity: O(N*(M+M)), where N = total no. of rows and M = total no. of columns.
    /// Space Complexity: O(M), used for storing heights in array and stack.
    private static int largestRectangleArea(int[] heights) {

        // Stack to store indices
        Stack<Integer> stack = new Stack<>();

        // Variable to store max area
        int maxArea = 0;

        // Append a sentinel height
        int[] newHeights = Arrays.copyOf(heights, heights.length + 1);

        // Loop over bars
        for (int i = 0; i < newHeights.length; i++) {

            // While current bar is less than stack top
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {

                // Pop and calculate area
                int height = newHeights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            // Push current index
            stack.push(i);
        }

        return maxArea;
    }

    // Function to find the maximal rectangle in binary matrix
    static int maximalRectangle(char[][] matrix) {

        // Return 0 if matrix is empty
        if (matrix.length == 0) return 0;

        // Column count
        int m = matrix[0].length;

        // Histogram array
        int[] height = new int[m];

        // Variable to track max area
        int maxArea = 0;

        // Iterate each row
        for (char[] row : matrix) {

            // Update histogram
            for (int i = 0; i < m; i++) {
                if (row[i] == '1') height[i]++;
                else height[i] = 0;
            }

            // Calculate max area
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }

        return maxArea;
    }
}
