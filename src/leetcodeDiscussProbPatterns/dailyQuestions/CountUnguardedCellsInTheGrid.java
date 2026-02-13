package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountUnguardedCellsInTheGrid {
    public static void main(String[] args) {
        int m = 4, n = 6;
        int[][] guards = {{0, 0}, {1, 1}, {2, 3}}, walls = {{0, 1}, {2, 2}, {1, 4}};

        System.out.println(countUnguarded(m, n, guards, walls));
    }

    static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] i : walls) {
            grid[i[0]][i[1]] = -1;
        }

        for (int[] j : guards) {
            grid[j[0]][j[1]] = -2;
        }

        for (int[] j : guards) {
            int x = j[0], y = j[1];
//            upside
            for (int i = x - 1; i >= 0; i--) {
                if (grid[i][y] == -1 || grid[i][y] == -2)
                    break;
                grid[i][y] = 1;
            }
//            down
            for (int i = x + 1; i < m; i++) {
                if (grid[i][y] == -1 || grid[i][y] == -2)
                    break;
                grid[i][y] = 1;
            }
//            right
            for (int k = y - 1; k >= 0; k--) {
                if (grid[x][k] == -1 || grid[x][k] == -2)
                    break;
                grid[x][k] = 1;
            }
//            left
            for (int k = y + 1; k < n; k++) {
                if (grid[x][k] == -1 || grid[x][k] == -2)
                    break;
                grid[x][k] = 1;
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    count++;
            }
        }
        return count;
    }
}
