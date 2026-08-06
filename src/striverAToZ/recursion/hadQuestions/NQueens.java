package striverAToZ.recursion.hadQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    /// Problem Statement: The n-queens is the problem of placing n queens on n × n chessboard such that no two queens can attack each other. Given an integer n, return all distinct solutions to the n -queens puzzle. Each solution contains a distinct boards configuration of the queen's placement, where ‘Q’ and ‘.’ indicate queen and empty space respectively.
    public static void main(String[] args) {
        // Set board size
        int n = 4;

        // Solve N-Queens
        List<List<String>> res = solveNQueens(n);

        // Print each solution
        for (List<String> board : res) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    /// Time Complexity: O(N!*N), we try all possible permutations of placing the queens and check for safety.
    /// Space Complexity: O(N^2 + N), additional space used for storing distinct boards and stack space.
    // Brute-Force Approach
    static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Start backtracking from column 0
        solve(0, board, ans, n);
        return ans;
    }

    private static boolean isSafe(int row, int col, char[][] board, int n) {
        // Check left in the same row
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 'Q') return false;
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check lower-left diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Return true if it's safe to place
        return true;
    }

    private static void solve(int col, char[][] board,
                              List<List<String>> ans, int n) {
        // If all columns are filled, save the solution
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            ans.add(temp);
            return;
        }

        // Try placing queen in each row for the current column
        for (int row = 0; row < n; row++) {
            if (isSafe(row, col, board, n)) {
                // Place queen
                board[row][col] = 'Q';
                // Recurse to next column
                solve(col + 1, board, ans, n);
                // Backtrack
                board[row][col] = '.';
            }
        }
    }

    /// Time Complexity: O(N!), we try all possible permutations of placing the queens.
    /// Space Complexity: O(N), three boolean arrays are stored to check for safety.
    // Optimal
    static List<List<String>> solveNQueensI(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        int[] leftRow = new int[n];
        int[] lowerDiagonal = new int[2 * n - 1];
        int[] upperDiagonal = new int[2 * n - 1];
        solveI(0, board, n, leftRow, upperDiagonal, lowerDiagonal, res);
        return res;
    }

    private static void solveI(int col, char[][] board, int n,
                               int[] leftRow, int[] upperDiagonal, int[] lowerDiagonal,
                               List<List<String>> res) {
        // If all queens are placed
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            res.add(temp);
            return;
        }

        // Iterate through rows
        for (int row = 0; row < n; row++) {
            // Check safety
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 &&
                    upperDiagonal[n - 1 + col - row] == 0) {

                // Place queen
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[n - 1 + col - row] = 1;

                // Recurse
                solveI(col + 1, board, n, leftRow, upperDiagonal, lowerDiagonal, res);

                // Backtrack
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[n - 1 + col - row] = 0;
            }
        }
    }

}
