package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInABinaryMaze {
    public static void main(String[] args) {
        int[] source = {0, 1};
        int[] destination = {2, 2};

        // Define the grid
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };
        System.out.println(shortestPath(grid, source, destination));
    }

    // tc O(4 * N * M) sc O(N * M)
    static int shortestPath(int[][] grid, int[] source, int[] destination) {
        // Edge Case: if the source is the same as the destination
        if (source[0] == destination[0] && source[1] == destination[1])
            return 0;

        // Get the grid size
        int n = grid.length;
        int m = grid[0].length;

        // Initialize the distance matrix, marking all cells as unvisited initially
        int[][] dist = new int[n][m];
        for (
                int i = 0;
                i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[source[0]][source[1]] = 0;

        // Create a queue for BFS to store {distance, {row, column}} pairs
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, source[0], source[1]});  // Push source cell with distance 0

        // Define the possible directions (up, right, down, left)
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // Start BFS traversal
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int dis = current[0];
            int r = current[1];
            int c = current[2];

            // Check all 4 adjacent directions
            for (int i = 0; i < 4; i++) {
                int newr = r + dr[i];
                int newc = c + dc[i];

                // Check if the new cell is within bounds and is a valid cell (i.e., 1)
                if (newr >= 0 && newr < n && newc >= 0 && newc < m && grid[newr][newc] == 1 && dis + 1 < dist[newr][newc]) {
                    dist[newr][newc] = dis + 1;  // Update the distance

                    // If destination is reached, return the distance
                    if (newr == destination[0] && newc == destination[1])
                        return dis + 1;

                    // Add the new cell to the queue for further processing
                    q.offer(new int[]{dis + 1, newr, newc});
                }
            }
        }

        // If no path is found from source to destination
        return -1;
    }
}
