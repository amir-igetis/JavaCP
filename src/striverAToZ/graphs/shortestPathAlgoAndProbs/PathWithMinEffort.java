package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinEffort {
    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        System.out.println(minimumEffort(heights));
    }

    // tc  O(4 * N * M * log(N * M)) sc O(n * M)
    static int minimumEffort(int[][] heights) {

        // Create a priority queue to store the cells and their respective distance from the source
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int n = heights.length;  // Number of rows
        int m = heights[0].length;  // Number of columns

        // Create a distance matrix, initialized with a large value (unvisited)
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;  // Distance for the source cell (0, 0) is 0
        pq.add(new int[]{0, 0, 0});  // Push source cell to the priority queue

        // Define the possible directions (up, right, down, left)
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // Start the Dijkstra algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int diff = current[0];  // The current effort
            int row = current[1];
            int col = current[2];

            // If we reach the destination cell, return the current effort
            if (row == n - 1 && col == m - 1)
                return diff;

            // Check all 4 possible adjacent cells
            for (int i = 0; i < 4; i++) {
                int newr = row + dr[i];
                int newc = col + dc[i];

                // Check if the new cell is within bounds
                if (newr >= 0 && newc >= 0 && newr < n && newc < m) {
                    // Calculate the effort required to move to the new cell
                    int newEffort = Math.max(Math.abs(heights[row][col] - heights[newr][newc]), diff);

                    // If the calculated effort is less, update and push to the queue
                    if (newEffort < dist[newr][newc]) {
                        dist[newr][newc] = newEffort;
                        pq.add(new int[]{newEffort, newr, newc});
                    }
                }
            }
        }
        return 0;  // If unreachable (although it should not reach here)
    }
}
