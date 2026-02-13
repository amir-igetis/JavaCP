package LeetcodeInterview.matrix;

public class GameOfLife {
    public static void main(String[] args) {
//
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        gameOfLife(board);
        for (int[] i : board) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void gameOfLifeI(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] newBoard = new int[m][n];

        int[] neighbors = {-1, 0, 1};
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int liveCount = 0;
                for (int rOffset : neighbors) {
                    for (int cOffset : neighbors) {
                        if (rOffset == 0 && cOffset == 0)
                            continue;
                        int newRow = row + rOffset;
                        int newCol = col + cOffset;
                        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                            liveCount += board[newRow][newCol];
                        }
                    }
                }

                if (board[row][col] == 1 && (liveCount < 2 || liveCount > 3)) {
                    newBoard[row][col] = 0;
                } else if (board[row][col] == 0 && liveCount == 3) {
                    newBoard[row][col] = 1;
                } else {
                    newBoard[row][col] = board[row][col];
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = newBoard[row][col];
            }
        }
    }

    static void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        int[] directions = {-1, 0, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbours = 0;
                for (int dx : directions) {
                    for (int dy : directions) {
                        if (dx == 0 && dy == 0)
                            continue;
                        int ni = i + dx, nj = j + dy;
                        if (ni >= 0 && ni < m && nj >= 0
                                && nj < n && Math.abs(board[ni][nj]) == 1) {
                            liveNeighbours++;

                        }
                    }
                }

                if (board[i][j] == 1 & (liveNeighbours < 2 || liveNeighbours > 3)) {
                    board[i][j] = -1;
                }

                if (board[i][j] == 0 && liveNeighbours == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
