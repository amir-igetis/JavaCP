package leetcodeDiscussProbPatterns.dailyQuestions;

public class FlipSquareSubmatVertically {
    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int x = 1, y = 0, k = 3;
        int[][] ans = reverseSubmatrix(grid, x, y, k);
        for (int[] i : ans) {
            for (int j : i)
                System.out.print(j + " ");

            System.out.println();
        }
    }

    static int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k >> 1; j++) {
                swap(grid, x + j, y + i, x + k - j - 1, y + i);
            }
        }
        return grid;
    }

    private static void swap(int[][] grid, int a, int b, int x, int y) {
        int temp = grid[a][b];
        grid[a][b] = grid[x][y];
        grid[x][y] = temp;
    }

//    private static void swap(int a, int b) {
//        int temp = a;
//        a = b;
//        b = temp;
//    }
}
