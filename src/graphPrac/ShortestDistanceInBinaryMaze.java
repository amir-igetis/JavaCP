package graphPrac;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInBinaryMaze {
    public static void main(String[] args) {
        int[][] mat = {{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}, {1, 0, 0, 0}};
        int[] source = {0, 1};
        int[] destination = {2, 2};
        System.out.println(shortestPath(mat, source, destination));
    }

    static int shortestPath(int[][] grid, int[] source, int[] destination) {
        if (source[0] == destination[0] &&
                source[1] == destination[1]) return 0;
        Queue<Triplet> q = new LinkedList<>();

        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[source[0]][source[1]] = 0;
        q.add(new Triplet(source[0], source[1], 0));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            Triplet it = q.poll();
            int dis = it.weight;
            int r = it.row;
            int c = it.col;
            for (int i = 0; i < 4; i++) {
                int newr = r + dr[i];
                int newc = c + dc[i];
                if (newr >= 0 && newr < n && newc >= 0 && newc < m
                        && grid[newr][newc] == 1 && dis + 1 < dist[newr][newc]) {
                    dist[newr][newc] = 1 + dis;
                    if (newr == destination[0] &&
                            newc == destination[1]) {
                        return dist[newr][newc];
//                        or return 1 + dis;
                    }
                    q.add(new Triplet(newr, newc, dist[newr][newc]));
//                    or new Triplet(newr, newc, 1 + dist);
                }
            }
        }
        return -1;
    }
}
