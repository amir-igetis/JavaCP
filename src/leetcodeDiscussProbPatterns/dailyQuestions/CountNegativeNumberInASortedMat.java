package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountNegativeNumberInASortedMat {
    public static void main(String[] args) {
        int[][] grid = {
                {4, 3, 2, -1},
                {3, 2, 1, -1},
                {1, 1, -1, -2},
                {-1, -1, -2, -3}
        };
        System.out.println(countNegativesI(grid));
    }

    static int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            int left = 0, right = n - 1;
            int firstNeg = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (grid[i][mid] < 0) {
                    firstNeg = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            count += (n - firstNeg);
        }
        return count;
    }

    //    Top right traversal
    static int countNegativesI(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int row = 0, col = n - 1, count = 0;
        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                count += (m - row);
                col--;
            } else {
                row++;
            }
        }
        return count;
    }

}
