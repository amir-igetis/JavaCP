package graphPrac;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinEffort {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(pathWithMinEffort(mat));
    }

    static int pathWithMinEffort(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<Triplet> q = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        q.add(new Triplet(0, 0, 0));
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            Triplet it = q.poll();
            int r = it.row;
            int c = it.col;
            int weight = it.weight;
            if (r == n - 1 && c == m - 1) return weight;
            for (int i = 0; i < 4; i++) {
                int newr = dr[i] + r;
                int newc = dc[i] + c;
                if (newr >= 0 && newr < n && newc >= 0 && newc < m) {
                    int newWeightOrDiff = Math.max(Math.abs(grid[r][c] - grid[newr][newc]), weight);
                    if (newWeightOrDiff < dist[newr][newc]) {
                        dist[newr][newc] = newWeightOrDiff;
                        q.add(new Triplet(newr, newc, newWeightOrDiff));
                    }
                }
            }
        }
        return 0;
    }
}
