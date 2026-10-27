package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountIslandsWithTotalValueDivisibleByK {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 2, 1, 0, 0},
                {0, 5, 0, 0, 5},
                {0, 0, 1, 0, 0},
                {0, 1, 4, 7, 0},
                {0, 2, 0, 0, 8}
        };
        int k = 5;
        System.out.println(countIslands(grid, k));
    }

    // DFS
    /// Let m and n be the number of rows and columns in the matrix, respectively.
    ///
    /// Time complexity: O(mn).
    ///
    /// Each cell is visited at most once.
    ///
    /// Space complexity: O(mn).
    ///
    /// In the worst case, the recursion stack can grow up to O(mn) frames if an island spans the entire grid

    private static int m, n;
    private static final int[] dirs = {-1, 0, 1, 0, -1};

    static int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0 &&
                        dfs(grid, i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private static long dfs(int[][] grid, int i, int j) {
        long sum = grid[i][j];
        grid[i][j] = 0;
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d],
                    y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n
                    && grid[x][y] > 0) {
                sum += dfs(grid, x, y);
            }
        }
        return sum;
    }

}
