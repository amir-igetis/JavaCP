package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class FindTheSafestPathInAGrid {
    public static void main(String[] args) {
        List<List<Integer>> grid = List.of(
                List.of(1, 0, 0),
                List.of(0, 0, 0),
                List.of(0, 0, 1)
        );

        List<List<Integer>> gridI = Arrays.asList(
                Arrays.asList(1, 0, 0),
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 0, 1)
        );

        List<List<Integer>> gridII = List.of(
                new ArrayList<>(Arrays.asList(1, 0, 0)),
                new ArrayList<>(Arrays.asList(0, 0, 0)),
                new ArrayList<>(Arrays.asList(0, 0, 1))
        );

        System.out.println(maximumSafenessFactorI(grid));
    }

    // bfs + binary search tc O(n^2logn) sc o(n^2)

    // Directions for moving to neighboring cells: right, left, down, up
    static final int[][] dirI = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int maximumSafenessFactorI(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] mat = new int[n][n];
        Queue<int[]> multiSourceQueue = new LinkedList<>();

        // To make modifications and navigation easier, the grid is converted into a 2-d array.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    // Push thief coordinates to the queue
                    multiSourceQueue.add(new int[]{i, j});
                    // Mark thief cell with 0
                    mat[i][j] = 0;
                } else {
                    // Mark empty cell with -1
                    mat[i][j] = -1;
                }
            }
        }

        // Calculate safeness factor for each cell using BFS
        while (!multiSourceQueue.isEmpty()) {
            int size = multiSourceQueue.size();
            while (size-- > 0) {
                int[] curr = multiSourceQueue.poll();
                // Check neighboring cells
                for (int[] d : dirI) {
                    int di = curr[0] + d[0];
                    int dj = curr[1] + d[1];
                    int val = mat[curr[0]][curr[1]];
                    // Check if the neighboring cell is valid and unvisited
                    if (isValidCell(mat, di, dj) && mat[di][dj] == -1) {
                        // Update safeness factor and push to the queue
                        mat[di][dj] = val + 1;
                        multiSourceQueue.add(new int[]{di, dj});
                    }
                }
            }
        }

        // Binary search for maximum safeness factor
        int start = 0;
        int end = 0;
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Set end as the maximum safeness factor possible
                end = Math.max(end, mat[i][j]);
            }
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValidSafeness(mat, mid)) {
                // Store valid safeness and search for larger ones
                res = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    // Check if a path exists with given minimum safeness value
    private static boolean isValidSafeness(int[][] grid, int minSafeness) {
        int n = grid.length;

        // Check if the source and destination cells satisfy minimum safeness
        if (grid[0][0] < minSafeness || grid[n - 1][n - 1] < minSafeness) {
            return false;
        }

        Queue<int[]> traversalQueue = new LinkedList<>();
        traversalQueue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        // Breadth-first search to find a valid path
        while (!traversalQueue.isEmpty()) {
            int[] curr = traversalQueue.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1) {
                return true; // Valid path found
            }
            // Check neighboring cells
            for (int[] d : dir) {
                int di = curr[0] + d[0];
                int dj = curr[1] + d[1];
                // Check if the neighboring cell is valid, unvisited and satisfying minimum safeness
                if (isValidCell(grid, di, dj) && !visited[di][dj] && grid[di][dj] >= minSafeness) {
                    visited[di][dj] = true;
                    traversalQueue.add(new int[]{di, dj});
                }
            }
        }

        return false; // No valid path found
    }

    // Check if a given cell lies within the grid
    private static boolean isValidCellI(int[][] mat, int i, int j) {
        int n = mat.length;
        return i >= 0 && j >= 0 && i < n && j < n;
    }


    // bfs + binary search tc O(n^2logn) sc o(n^2)

    static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();
        int[][] mat = new int[n][n];
        Queue<int[]> multiSourceQueue = new LinkedList<>();

        // To make modifications and navigation easier, the grid is converted into a 2-d array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    // Push thief coordinates to the queue
                    multiSourceQueue.add(new int[]{i, j});
                    // Mark thief cell with 0
                    mat[i][j] = 0;
                } else {
                    // Mark empty cell with -1
                    mat[i][j] = -1;
                }
            }
        }

        // Calculate safeness factor for each cell using BFS
        while (!multiSourceQueue.isEmpty()) {
            int size = multiSourceQueue.size();
            while (size-- > 0) {
                int[] curr = multiSourceQueue.poll();
                // Check neighboring cells
                for (int[] d : dir) {
                    int di = curr[0] + d[0];
                    int dj = curr[1] + d[1];
                    int val = mat[curr[0]][curr[1]];
                    // Check if the neighboring cell is valid and unvisited
                    if (isValidCell(mat, di, dj) && mat[di][dj] == -1) {
                        // Update safeness factor and push to the queue
                        mat[di][dj] = val + 1;
                        multiSourceQueue.add(new int[]{di, dj});
                    }
                }
            }
        }

        // Priority queue to prioritize cells with higher safeness factor
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        // Push starting cell to the priority queue
        pq.add(new int[]{0, 0, mat[0][0]}); // [x-coordinate, y-coordinate, maximum_safeness_till_now]
        mat[0][0] = -1; // Mark the source cell as visited

        // BFS to find the path with maximum safeness factor
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            // If reached the destination, return safeness factor
            if (curr[0] == n - 1 && curr[1] == n - 1) {
                return curr[2];
            }
            // Explore neighboring cells
            for (int[] d : dir) {
                int di = d[0] + curr[0];
                int dj = d[1] + curr[1];
                if (isValidCell(mat, di, dj) && mat[di][dj] != -1) {
                    // Update safeness factor for the path and mark the cell as visited
                    pq.add(new int[]{di, dj, Math.min(curr[2], mat[di][dj])});
                    mat[di][dj] = -1;
                }
            }
        }

        return -1; // No valid path found
    }

    // Check if a given cell lies within the grid
    private static boolean isValidCell(int[][] mat, int i, int j) {
        int n = mat.length;
        return i >= 0 && j >= 0 && i < n && j < n;

    }

}
