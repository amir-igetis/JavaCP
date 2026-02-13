package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {{'1' }};
        System.out.println(maximalRectangleI(matrix));
    }

    static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;

        int ans = 0;
        int[] hist = new int[matrix[0].length];

        for (char[] row : matrix) {
            for (int i = 0; i < row.length; ++i)
                hist[i] = row[i] == '0' ? 0 : hist[i] + 1;
            ans = Math.max(ans, largestRectangleArea(hist));
        }

        return ans;
    }

    private static int largestRectangleArea(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= heights.length; ++i) {
            while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] > heights[i])) {
                final int h = heights[stack.pop()];
                final int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, h * w);
            }
            stack.push(i);
        }

        return ans;
    }


    // using stack
    static int maximalRectangleI(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1')
                    heights[j]++;
                else heights[j] = 0;
            }

            maxArea = Math.max(maxArea, helper(heights));
        }
        return maxArea;
    }

    private static int helper(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxArea = 0, n = heights.length;
        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];
            while (!st.isEmpty() && currHeight < heights[st.peek()]) {
                int height = heights[st.peek()];
                st.pop();
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            st.push(i);
        }
        return maxArea;
    }
}
