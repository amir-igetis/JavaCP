package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterII {
    public static void main(String[] args) {
//        int[][] heightMap = {
//                {1, 4, 3, 1, 3, 2},
//                {3, 2, 1, 3, 2, 4},
//                {2, 3, 3, 2, 3, 1}
//        };

        int[][] heightMap = {
                {3, 3, 3, 3, 3},
                {3, 2, 2, 2, 3},
                {3, 2, 1, 2, 3},
                {3, 2, 2, 2, 3},
                {3, 3, 3, 3, 3}
        };
        System.out.println(trapRainWater(heightMap));
    }

    static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        for (int i = 0; i < m; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            vis[i][0] = vis[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            vis[0][j] = vis[m - 1][j] = true;
        }

        int ans = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int[] d : dirs) {
                int r = cell.row + d[0];
                int c = cell.col + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || vis[r][c])
                    continue;

                vis[r][c] = true;
                ans += Math.max(0, cell.height - heightMap[r][c]);
                pq.offer(new Cell(r, c, Math.max(heightMap[r][c], cell.height)));
            }
        }
        return ans;
    }

    static class Cell {
        int row, col, height;

        Cell(int r, int c, int h) {
            row = r;
            col = c;
            height = h;
        }
    }
}
