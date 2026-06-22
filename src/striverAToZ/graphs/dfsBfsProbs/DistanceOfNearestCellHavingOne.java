package striverAToZ.graphs.dfsBfsProbs;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCellHavingOne {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1}
        };
        int[][] ans = nearest(grid);

        for (int[] row : ans) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    static int[][] nearest(int[][] grid) {
        int n = grid.length;       // Number of rows
        int m = grid[0].length;    // Number of columns

        // Visited matrix to track visited cells
        int[][] vis = new int[n][m];
        // Distance matrix to store the result
        int[][] dist = new int[n][m];

        // Queue to perform BFS: stores coordinates and steps
        Queue<int[]> q = new LinkedList<>();

        // Initialize queue with all cells containing 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j, 0}); // {row, col, distance}
                    vis[i][j] = 1; // Mark as visited
                } else {
                    vis[i][j] = 0; // Mark unvisited
                }
            }
        }

        // Directions: Up, Right, Down, Left
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // BFS traversal
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];
            int steps = cell[2];

            dist[row][col] = steps; // Assign distance for the current cell

            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                // Check boundaries and unvisited cells
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0) {
                    vis[nrow][ncol] = 1; // Mark as visited
                    q.add(new int[]{nrow, ncol, steps + 1}); // Push into queue with incremented distance
                }
            }
        }
        return dist;
    }

}
