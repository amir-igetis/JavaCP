package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfThereIsAValidPathInAGrid {
    public static void main(String[] args) {
        int[][] grid = {{2, 4, 3}, {6, 5, 2}};
        System.out.println(hasValidPath(grid));
    }

    static int[][][] dirs = {
            {},
            {{0, -1}, {0, 1}},   // 1: left, right
            {{-1, 0}, {1, 0}},   // 2: up, down
            {{0, -1}, {1, 0}},   // 3: left, down
            {{0, 1}, {1, 0}},    // 4: right, down
            {{0, -1}, {-1, 0}},  // 5: left, up
            {{0, 1}, {-1, 0}}    // 6: right, up
    };

    public static boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];

            if (x == m - 1 && y == n - 1) return true;

            for (int[] d : dirs[grid[x][y]]) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny])
                    continue;

                // 🔥 check reverse connection
                for (int[] back : dirs[grid[nx][ny]]) {
                    if (nx + back[0] == x && ny + back[1] == y) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        break;
                    }
                }
            }
        }

        return false;
    }

}
