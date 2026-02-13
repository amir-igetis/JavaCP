package LeetcodeInterview.graphBFS;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public static void main(String[] args) {
        //

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        System.out.println("The answer is " + snakesAndLadders(board));

    }

    static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flatten = new int[n * n + 1]; // 1-based indexing
        int index = 1;
        boolean leftToRight = true;

        // Flatten the board into a 1D array
        for (int row = n - 1; row >= 0; row--) {
            if (leftToRight) {
                for (int col = 0; col < n; col++)
                    flatten[index++] = board[row][col];
            } else {
                for (int col = n - 1; col >= 0; col--)
                    flatten[index++] = board[row][col];
            }
            leftToRight =  !leftToRight;
        }

        // BFS for shortest path
        Queue<int[]> queue = new LinkedList<>(); // {position, moves}
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0], moves = current[1];

            for (int dice = 1; dice <= 6; dice++) {
                int nextPos = pos + dice;
                if (nextPos > n * n)
                    break;

                if (flatten[nextPos] != -1)
                    nextPos = flatten[nextPos]; // Ladder or Snake jump

                if (nextPos == n * n)
                    return moves + 1; // Reached last cell

                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, moves + 1});
                }
            }
        }

        return -1;
    }

    public static int snakesAndLaddersI(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == n * n) {
                    return moves;
                }

                // Try all 6 possible dice rolls
                for (int j = 1; j <= 6; j++) {
                    int next = current + j;
                    if (next > n * n) {
                        break;  // We can't go beyond the board
                    }

                    // Get the actual position (considering boustrophedon style)
                    int[] pos = getBoardPosition(next, n);
                    int row = pos[0], col = pos[1];

                    // If there's a snake or ladder, take it
                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }

        return -1;  // If we can't reach the  end
    }

    // Helper function to convert number to board coordinates
    private static int[] getBoardPosition(int num, int n) {
        int row = (num - 1) / n;
        int col = (num - 1) % n;

        // Boustrophedon style: alternate direction each row
        if (row % 2 == 1) {
            col = n - 1 - col;
        }

        // Board is stored in reverse order (bottom to top)
        row = n - 1 - row;

        return new int[]{row, col};
    }
}
