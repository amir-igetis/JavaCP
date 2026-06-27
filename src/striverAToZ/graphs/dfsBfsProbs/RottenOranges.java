package striverAToZ.graphs.dfsBfsProbs;

import java.util.LinkedList;
import java.util.Queue;

// Question No. - 03
public class RottenOranges {
    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(orangesRottingI(grid));
    }

    //    tc O(n*n*4) sc O(n*n)
    static int orangesRotting(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int total = 0;
        int count = 0;
        Queue<int[]> rotten = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0)
                    total++;
                if (grid[i][j] == 2)
                    rotten.add(new int[]{i, j});
            }
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int days = 0;
        while (!rotten.isEmpty()) {
            int k = rotten.size();
            count += k;
            for (int i = 0; i < k; i++) {
                int[] pos = rotten.poll();
                int x = pos[0], y = pos[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] != 1)
                        continue;
                    grid[nx][ny] = 2;
                    rotten.add(new int[]{nx, ny});
                }
            }
            if (!rotten.isEmpty())
                days++;
        }
        return total == count ? days : -1;
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=rotten_oranges
    // soln for https://leetcode.com/problems/rotting-oranges/
    static int orangesRottingI(int[][] grid) {
        if (grid == null || grid.length == 0)
            return -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    rotAdjacent(grid, i, j, 2);
            }
        }

        int minutes = 2;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 1)
                    return -1;
                minutes = Math.max(minutes, cell);
            }
        }

        return minutes - 2;
    }

    private static void rotAdjacent(int[][] grid, int i, int j, int minutes) {
        if (i < 0 || i >= grid.length /* out of bounds */
                || j < 0 || j >= grid[0].length /* out of bounds */
                || grid[i][j] == 0 /* empty cell */
                || (1 < grid[i][j] && grid[i][j] < minutes) /* this orange is already rotten by another rotten orange */
        )
            return;
        else {
            grid[i][j] = minutes;
            rotAdjacent(grid, i - 1, j, minutes + 1);
            rotAdjacent(grid, i + 1, j, minutes + 1);
            rotAdjacent(grid, i, j - 1, minutes + 1);
            rotAdjacent(grid, i, j + 1, minutes + 1);
        }
    }
}
