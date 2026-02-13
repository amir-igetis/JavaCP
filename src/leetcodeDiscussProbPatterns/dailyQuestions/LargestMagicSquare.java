package leetcodeDiscussProbPatterns.dailyQuestions;

public class LargestMagicSquare {
    public static void main(String[] args) {
        int[][] grid = {{7, 1, 4, 5, 6}, {2, 5, 1, 6, 4}, {1, 5, 4, 3, 2}, {1, 2, 7, 3, 4}};
        System.out.println(largestMagicSquare(grid));
    }

    //    Enumerating Squares + Prefix Sum Optimization
//    tc & sc O(mn min(m, n)^ 2) & O(mn)
    static int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n];
        for (int i = 0; i < m; i++) {
            rowSum[i][0] = grid[i][0];
            for (int j = 1; j < n; j++) {
                rowSum[i][j] = rowSum[i][j - 1] + grid[i][j];
            }
        }

        int[][] colSum = new int[m][n];
        for (int j = 0; j < n; j++) {
            colSum[0][j] = grid[0][j];
            for (int i = 1; i < m; i++) {
                colSum[i][j] = colSum[i - 1][j] + grid[i][j];
            }
        }

        for (int edge = Math.min(m, n); edge >= 2; edge--) {
            for (int i = 0; i + edge <= m; i++) {
                for (int j = 0; j + edge <= n; j++) {

                    int stdSum = rowSum[i][j + edge - 1] -
                            (j > 0 ? rowSum[i][j - 1] : 0);

                    boolean check = true;

                    // check rows
                    for (int ii = i + 1; ii < i + edge; ii++) {
                        if (rowSum[ii][j + edge - 1] -
                                (j > 0 ? rowSum[ii][j - 1] : 0) != stdSum) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;

                    // check columns
                    for (int jj = j; jj < j + edge; jj++) {
                        if (colSum[i + edge - 1][jj] -
                                (i > 0 ? colSum[i - 1][jj] : 0) != stdSum) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;

                    // check diagonals
                    int d1 = 0, d2 = 0;
                    for (int k = 0; k < edge; k++) {
                        d1 += grid[i + k][j + k];
                        d2 += grid[i + k][j + edge - 1 - k];
                    }

                    if (d1 == stdSum && d2 == stdSum)
                        return edge;
                }
            }
        }

        return 1;
    }
}
