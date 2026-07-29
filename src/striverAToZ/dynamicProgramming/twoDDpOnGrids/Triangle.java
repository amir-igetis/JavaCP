package striverAToZ.dynamicProgramming.twoDDpOnGrids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(3, 6, 7));
        triangle.add(Arrays.asList(8, 9, 6, 10));
        System.out.println(minimumPathSum(triangle));

    }

    /// memoization
    /// Time Complexity: O(N*N), we fill our complete 2D DP table one by one.
    /// Space Complexity: O(N) + O(N*N), additional space for recursion stack and 2D DP table.

    // Function to start the process
    static int minimumPathSum(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(0, 0, triangle, n, dp);
    }

    // Recursive function with memoization
    private static int solve(int i, int j, List<List<Integer>> triangle, int n, int[][] dp) {
        // If value already computed
        if (dp[i][j] != -1)
            return dp[i][j];

        // If at bottom row
        if (i == n - 1)
            return triangle.get(i).get(j);

        // Compute both downward and diagonal moves
        int down = triangle.get(i).get(j) + solve(i + 1, j, triangle, n, dp);
        int diag = triangle.get(i).get(j) + solve(i + 1, j + 1, triangle, n, dp);

        // Store and return min path sum
        return dp[i][j] = Math.min(down, diag);
    }

    /// tabulation
    /// Time Complexity: O(N*N), entire triangular grid is visited atleast once.
    /// Space Complexity: O(N*N), space used for 2D DP array.
    static int minimumPathSumI(List<List<Integer>> triangle, int n) {
        // Create dp array
        int[][] dp = new int[n][n];

        // Fill last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        // Fill rest of dp from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Take min of down and diagonal
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diag = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }

        // Return top element
        return dp[0][0];
    }

    /// space optimized
    /// Time Complexity: O(N*N), every element of triangular grid is visited atleast once.
    /// Space Complexity: O(N), we only use one array for storing rows.
    static int minimumPathSumII(List<List<Integer>> triangle, int n) {

        // Create an array to store the next row
        int[] front = new int[n];

        // Create an array to store the current row
        int[] cur = new int[n];

        // Initialize front with last row of triangle
        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n - 1).get(j);
        }

        // Traverse rows from bottom to top
        for (int i = n - 2; i >= 0; i--) {

            // Traverse elements in current row
            for (int j = i; j >= 0; j--) {

                // Calculate path going down
                int down = triangle.get(i).get(j) + front[j];

                // Calculate path going diagonal
                int diagonal = triangle.get(i).get(j) + front[j + 1];

                // Store minimum in current row
                cur[j] = Math.min(down, diagonal);
            }

            // Update front row with current row
            front = cur.clone();
        }

        // Return top element (minimum path sum)
        return front[0];
    }

}

