package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

// Question No. - 09
public class NumOfEnclaves {
    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0 },
                { 1, 0, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        System.out.println(numberOfEnclaves(grid));
    }

    static int numberOfEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int n = grid.length, m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 1 && !vis[i][j]) {
                        vis[i][j] = true;
                        q.add(new int[] { i, j });
                    }
                }
            }
        }

        int[] delrow = { -1, 0, 1, 0 };
        int[] delcol = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1];

            for (int k = 0; k < 4; k++) {
                int nrow = row + delrow[k];
                int ncol = col + delcol[k];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && !vis[nrow][ncol] && grid[nrow][ncol] == 1) {
                    vis[nrow][ncol] = true;
                    q.add(new int[] { nrow, ncol });
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j])
                    cnt++;
            }
        }
        return cnt;
    }

    // striver code
    // soln for https://leetcode.com/problems/number-of-enclaves/
    // soln for
    // https://practice.geeksforgeeks.org/problems/number-of-enclaves/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=number-of-enclaves
    static int numOfEnclavesI(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        // traverse boundary elements
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // first row, first col, last row, last col
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    // if it is a land then store it in queue
                    if (grid[i][j] == 1) {
                        q.add(new Pair(i, j));
                        vis[i][j] = 1;
                    }
                }
            }
        }
        int[] delrow = { -1, 0, +1, 0 };
        int[] delcol = { 0, +1, +0, -1 };

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();
            // traverse all 4 directions
            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                // check for valid coordinates and for land cell
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol));
                    vis[nrow][ncol] = 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // check for unvisited land cell
                if (grid[i][j] == 1 & vis[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    } // O(NxMx4) ~ O(N x M), For the worst case, assuming all the pieces as land, the
      // BFS function will be called for (N x M) nodes and for every node, we are
      // traversing for 4 neighbors, so it will take O(N x M x 4) time.

    // Space Complexity ~ O(N x M), O(N x M) for the visited array, and queue space
    // takes up N x M locations at max.

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
