package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRaisingWater {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println(swimInWaterI(grid));
    }

//    priority queue or dijkstra

    // using for the bfs solution also
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] vis = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        int maxHeight = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int height = curr[0], r = curr[1], c = curr[2];
            maxHeight = Math.max(maxHeight, height);
            if (r == n - 1 && c == n - 1)
                return maxHeight;

            if (vis[r][c])
                continue;
            vis[r][c] = true;

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n || vis[nr][nc])
                    continue;
                pq.offer(new int[]{grid[nr][nc], nr, nc});
            }
        }
        return -1;
    }

    //    using bfs + binary search
    static int swimInWaterI(int[][] grid) {
        int n = grid.length;
        int low = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int high = n * n - 1;
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(grid, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canReach(int[][] grid, int waterLvl) {
        int n = grid.length;
        if (grid[0][0] > waterLvl)
            return false;

        boolean[][] vis = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            if (r == n - 1 && c == n - 1)
                return true;

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n || vis[nr][nc])
                    continue;
                if (grid[nr][nc] <= waterLvl) {
                    vis[nr][nc] = true
                    ;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}
