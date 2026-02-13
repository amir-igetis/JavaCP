package LeetcodeInterview.graphGeneral;

import java.util.LinkedList;
import java.util.Queue;

public class SorroundedRegions {
    public static void main(String[] args) {
//

        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        solveI(board);

        for (char[] i : board) {
            for (char j : i)
                System.out.print(j + ", ");
            System.out.println();
        }
    }

    static void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                q.add(new int[]{i, 0});

            if (board[i][n - 1] == 'O')
                q.add(new int[]{i, n - 1});
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                q.add(new int[]{0, j});
            if (board[m - 1][j] == 'O')
                q.add(new int[]{m - 1, j});
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];

            if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') continue;
            board[x][y] = '#';

            for (int[] dir : directions) {
                q.add(new int[]{x + dir[0], y + dir[1]});
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    // deepseek

    static void solveI(char[][] board) {
        if (board == null || board.length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Step 1: Mark border-connected 'O's as 'T'
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O')
                dfs(board, i, 0);
            if (board[i][cols - 1] == 'O')
                dfs(board, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O')
                dfs(board, 0, j);
            if (board[rows - 1][j] == 'O')
                dfs(board, rows - 1, j);
        }

        // Step 2: Flip remaining 'O's to 'X'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }

        // Step 3: Restore 'T' to 'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'T') board[i][j] = 'O';
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'T'; // Mark as temporary

        // Explore 4-directionally
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    //    DFS
    static void solveII(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                dfs(board, i, 0);
            if (board[i][n - 1] == 'O')
                dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                dfs(board, 0, j);
            if (board[m - 1][j] == 'O')
                dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private static void dfsI(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#';
        dfsI(board, i + 1, j);
        dfsI(board, i - 1, j);
        dfsI(board, i, j + 1);
        dfsI(board, i, j - 1);

    }

}
