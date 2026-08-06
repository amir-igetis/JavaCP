package striverAToZ.recursion.hadQuestions;

public class WordSearch {

    /// Problem Statement: Given an m x n grid of characters board and a string word, return true if the word exists in the grid. The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(board, "ABCCED")); // true
        System.out.println(exist(board, "SEE"));    // true
        System.out.println(exist(board, "ABCB"));   // false

    }

    /// Time Complexity: O(m * n * 4^L),We may start from each of the m×n cells, and explore up to 4 directions for each of the L letters in the word.
    ///
    /// Space Complexity: O(L),Recursion depth equals the length of the word; we also modify the board in-place, so no extra space for visited tracking.
    static boolean exist(char[][] board, String word) {
        // Get number of rows
        int rows = board.length;
        // Get number of columns
        int cols = board[0].length;

        // Iterate over all cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Start DFS if first letter matches
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        // No match found
        return false;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int idx) {
        // If word is completely found
        if (idx == word.length()) return true;

        // Boundary and mismatch check
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(idx)) {
            return false;
        }

        // Store character and mark visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Explore in all four directions
        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
                dfs(board, word, i - 1, j, idx + 1) ||
                dfs(board, word, i, j + 1, idx + 1) ||
                dfs(board, word, i, j - 1, idx + 1);

        // Restore character
        board[i][j] = temp;

        return found;
    }
}
