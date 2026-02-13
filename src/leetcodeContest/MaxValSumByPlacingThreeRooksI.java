package leetcodeContest;

import java.util.Arrays;

public class MaxValSumByPlacingThreeRooksI {
    private static long maxSum = Long.MIN_VALUE;
    public static void main(String[] args) {
//        int[][] board = {{-3, 1, 1, 1}, {-3, 1, -3, 1}, {-3, 2, 1, 1}};
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

//        long ans = maximumValueSum(board);
        long ans = maxRookSumI(board);
        System.out.println(ans);

    }

    static long maximumValueSum(int[][] board) {
        int n = board[0].length;
        int m = board.length;
        int maxMask = (1 << n) - 1;
        long[][] dp = new long[m + 1][1 << n];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int mask = 0; mask <= maxMask; mask++) {
                if (dp[i][mask] == Long.MIN_VALUE) continue;

                for (int j = 0; j < n; j++) {
                    if ((mask & (1 << j)) == 0) {
                        int newMask = mask | (1 << j);
                        dp[i + 1][newMask] = Math.max(dp[i + 1][newMask], dp[i][mask] + board[i][j]);
                    }
                }
            }
        }
        long result = Long.MIN_VALUE;
        for (int mask = 0; mask <= maxMask; mask++) {
            if (Integer.bitCount(mask) == 3) {
                result = Math.max(result, dp[m][mask]);
            }
        }
        return result;
    }

    static long maxRookSumI(int[][] board) {
        int n = board[0].length;
        int m = board.length;
        backtrack(board, new boolean[m], new boolean[n], 0, 0);
        return maxSum;
    }

    private static void backtrack(int[][] board, boolean[] usedRows, boolean[] usedCols, int row, int count) {
        if (count == 3) {
            long currentSum = 0;
            for (int r = 0; r < usedRows.length; r++) {
                for (int c = 0; c < usedCols.length; c++) {
                    if (usedRows[r] && usedCols[c]) {
                        currentSum += board[r][c];
                    }
                }
            }
            maxSum = Math.max(maxSum, currentSum);
            return;
        }
        if (row >= board.length) return;

        for (int col = 0; col < board[0].length; col++) {
            if (!usedCols[col]) {
                usedRows[row] = true;
                usedCols[col] = true;
                backtrack(board, usedRows, usedCols, row + 1, count + 1);
                usedRows[row] = false;
                usedCols[col] = false;
            }
        }

        backtrack(board, usedRows, usedCols, row + 1, count);
    }

}
