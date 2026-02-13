package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class LastDayWhereYouCanStillCross {
    public static void main(String[] args) {
        int[][] cells = {{1, 1}, {2, 1}, {1, 2}, {2, 2}};
        int row = 2, col = 2;
        System.out.println(latestDayToCross(row, col, cells));
    }

    static int latestDayToCross(int row, int col, int[][] cells) {
        DSU dsu = new DSU(row * col + 2);
        int[][] grid = new int[row][col];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < row * col; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
            int id1 = r * col + c + 1;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && grid[nr][nc] == 1) {
                    dsu.union(id1, nr * col + nc + 1);
                }
            }

            if (c == 0)
                dsu.union(0, id1);
            if (c == col - 1)
                dsu.union(row * col + 1, id1);

            if (dsu.find(0) == dsu.find(row * col + 1))
                return i;
        }
        return -1;
    }

    private static class DSU {
        int[] root, size;

        public DSU(int n) {
            root = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++)
                root[i] = i;
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            if (root[x] != x)
                root[x] = find(root[x]);
            return root[x];
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry)
                return;
            if (size[rx] > size[ry]) {
                int temp = rx;
                rx = ry;
                ry = temp;
            }

            root[rx] = ry;
            size[ry] += size[rx];
        }
    }
}
