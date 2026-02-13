package leetcodeDiscussProbPatterns.dailyQuestions;

public class MagicSquaresInGrid {
    public static void main(String[] args) {
        int[][] grid = {{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}};
        System.out.println(numMagicSquaresInsideI(grid));
    }

    //    manual scan tc O(m.n) sc O(1)
    static int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int row = 0; row + 2 < m; row++) {
            for (int col = 0; col + 2 < n; col++) {
                if (isMagicSquare(grid, row, col)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean isMagicSquare(int[][] grid, int row, int col) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = grid[row + i][col + j];
                if (num < 1 || num > 9) return false;
                if (seen[num]) return false;
                seen[num] = true;
            }
        }

        // check if diagonal sums are same value
        int diagonal1 =
                grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2];
        int diagonal2 =
                grid[row + 2][col] + grid[row + 1][col + 1] + grid[row][col + 2];

        if (diagonal1 != diagonal2) return false;

        // check if all row sums share the same value as the diagonal sums
        int row1 = grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
        int row2 =
                grid[row + 1][col] +
                        grid[row + 1][col + 1] +
                        grid[row + 1][col + 2];
        int row3 =
                grid[row + 2][col] +
                        grid[row + 2][col + 1] +
                        grid[row + 2][col + 2];

        if (!(row1 == diagonal1 && row2 == diagonal1 && row3 == diagonal1)) {
            return false;
        }

        // check if all column sums share same value as the diagonal sums
        int col1 = grid[row][col] + grid[row + 1][col] + grid[row + 2][col];
        int col2 =
                grid[row][col + 1] +
                        grid[row + 1][col + 1] +
                        grid[row + 2][col + 1];
        int col3 =
                grid[row][col + 2] +
                        grid[row + 1][col + 2] +
                        grid[row + 2][col + 2];

        if (!(col1 == diagonal1 && col2 == diagonal1 && col3 == diagonal2)) {
            return false;
        }

        return true;
    }

    //    check unique properties of Magic square tc O(m.n) sc O(1)
    static int numMagicSquaresInsideI(int[][] grid) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        for (int row = 0; row + 2 < m; row++) {
            for (int col = 0; col + 2 < n; col++) {
                if (isMagicSquareI(grid, row, col))
                    ans++;
            }
        }
        return ans;
    }

    private static boolean isMagicSquareI(int[][] grid, int row, int col) {
        String seq = "2943816729438167";
        String seqRev = "7618349276183492";
        StringBuilder sb = new StringBuilder();
        int[] borderIdx = new int[]{0, 1, 2, 5, 8, 7, 6, 3};
        for (int i : borderIdx) {
            int num = grid[row + i / 3][col + (i % 3)];
            sb.append(num);
        }
        String borderConverted = sb.toString();

        return (
                grid[row][col] % 2 == 0 &&
                        (seq.contains(borderConverted) ||
                                seqRev.contains(borderConverted)) &&
                        grid[row + 1][col + 1] == 5
        );
    }


    //    gpt solution
    static int numMagicSquaresInsideII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Traverse all possible 3x3 subgrids
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isMagic(int[][] g, int r, int c) {

        // 1️⃣ Center must be 5
        if (g[r + 1][c + 1] != 5) return false;

        // 2️⃣ Check numbers 1–9 exactly once
        boolean[] seen = new boolean[10];
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = g[i][j];
                if (val < 1 || val > 9 || seen[val]) {
                    return false;
                }
                seen[val] = true;
            }
        }

        // 3️⃣ Check sums
        int sum = g[r][c] + g[r][c + 1] + g[r][c + 2];

        // Rows
        for (int i = 0; i < 3; i++) {
            if (g[r + i][c] + g[r + i][c + 1] + g[r + i][c + 2] != sum)
                return false;
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (g[r][c + j] + g[r + 1][c + j] + g[r + 2][c + j] != sum)
                return false;
        }

        // Diagonals
        if (g[r][c] + g[r + 1][c + 1] + g[r + 2][c + 2] != sum)
            return false;

        if (g[r][c + 2] + g[r + 1][c + 1] + g[r + 2][c] != sum)
            return false;

        return true;
    }

}
