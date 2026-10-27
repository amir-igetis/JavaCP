package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class NumOfClosedIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        System.out.println(closedIsland(grid));
        System.out.println(closedIslandI(grid));
    }

    // Breadth First Search

    /// Here, m and n are the number of rows and columns in the given grid.
    ///
    /// Time complexity: O(m⋅n)
    ///
    /// Initializing the visit array takes O(m⋅n) time.
    /// We iterate over all the cells and find unvisited land cells to perform BFS traversal from those. This takes O(m⋅n) time.
    /// Each queue operation in the BFS algorithm takes O(1) time, and a single node can be pushed once, leading to O(m⋅n) operations for m⋅n nodes. We iterate over all the neighbors of each node that is popped out of the queue. So for every node, we would iterate four times to iterate over the neighbors, resulting in O(4⋅m⋅n)=O(m⋅n) operations total for all the nodes.
    /// Space complexity: O(m⋅n)
    ///
    /// The visit array takes O(m⋅n) space.
    /// The BFS queue takes O(m⋅n) space in the worst-case because each node is added once
    static int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visit[i][j] && bfs(i, j, m, n, grid, visit)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean bfs(int x, int y, int m, int n, int[][] grid, boolean[][] visit) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = true;
        boolean isClosed = true;

        int[] dirx = {0, 1, 0, -1};
        int[] diry = {-1, 0, 1, 0};

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            x = temp[0];
            y = temp[1];

            for (int i = 0; i < 4; i++) {
                int r = x + dirx[i];
                int c = y + diry[i];
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    // (x, y) is a boundary cell.
                    isClosed = false;
                } else if (grid[r][c] == 0 && !visit[r][c]) {
                    q.offer(new int[]{r, c});
                    visit[r][c] = true;
                }
            }
        }

        return isClosed;
    }

    // Depth First Search

    /// Here, m and n are the number of rows and columns in the given grid.
    ///
    /// Time complexity: O(m⋅n)
    ///
    /// Initializing the visit array takes O(m⋅n) time.
    /// We iterate over all the cells and find unvisited land cells to perform DFS traversal from those. This takes O(m⋅n) time.
    /// The dfs function visits each node once, leading to O(m⋅n) operations for m⋅n nodes. We iterate over all the neighbors of each node that is popped out of the queue. So for every node, we would iterate four times to iterate over the neighbors, resulting in O(4⋅m⋅n)=O(m⋅n) operations total for all the nodes.
    /// Space complexity: O(m⋅n)
    ///
    /// The visit array takes O(m⋅n) space.
    /// The recursion stack used by dfs can have no more than O(m⋅n) elements in the worst-case scenario. It would take up O(m⋅n) space in that case
    static int closedIslandI(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && !visit[i][j] && dfs(i, j, m, n, grid, visit)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean dfs(int x, int y, int m, int n, int[][] grid, boolean[][] visit) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        if (grid[x][y] == 1 || visit[x][y]) {
            return true;
        }

        visit[x][y] = true;
        boolean isClosed = true;
        int[] dirx = {0, 1, 0, -1};
        int[] diry = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {
            int r = x + dirx[i];
            int c = y + diry[i];
            if (!dfs(r, c, m, n, grid, visit)) {
                isClosed = false;
            }
        }

        return isClosed;
    }

}


