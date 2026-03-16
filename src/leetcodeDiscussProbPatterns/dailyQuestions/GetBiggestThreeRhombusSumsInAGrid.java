package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.TreeSet;

public class GetBiggestThreeRhombusSumsInAGrid {
    public static void main(String[] args) {
        int[][] grid = {{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}};
        System.out.println(Arrays.toString(getBiggestThreeI(grid)));
    }

    static int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // radius = 0 (single cell rhombus)
                set.add(grid[r][c]);
                int maxRadius = Math.min(Math.min(m, n), Math.min(r, n - c - 1));
                for (int k = 1; ; k++) {
                    if (r + 2 * k >= m || c - k < 0 || c + k >= n) break;
                    int sum = 0;
                    int x = r;
                    int y = c;
                    // top -> right
                    for (int i = 0; i < k; i++) sum += grid[x + i][y + i];
                    // right -> bottom
                    for (int i = 0; i < k; i++) sum += grid[x + k + i][y + k - i];
                    // bottom -> left
                    for (int i = 0; i < k; i++) sum += grid[x + 2 * k - i][y - i];
                    // left -> top
                    for (int i = 0; i < k; i++) sum += grid[x + k - i][y - k + i];
                    set.add(sum);
                }
            }
        }
        int size = Math.min(3, set.size());
        int[] result = new int[size];
//        for (int i = size - 1; i >= 0; i--) {
//            result[i] = set.pollLast();
//        }
        for (int i = 0; i <= size - 1; i++)
            result[i] = set.pollLast();
        return result;
    }


    static int[] getBiggestThreeI(int[][] grid) {
        int end = Math.min(grid.length, grid[0].length);
        int maxThree[] = {0, 0, 0};
        for (int length = 0; length < end; length++) {
            searchBigThree(grid, maxThree, length);
        }

        Arrays.sort(maxThree);

        // If there are less than three distinct values, return all of them.
        if (maxThree[0] == 0) {
            if (maxThree[1] == 0) {
                return new int[]{maxThree[2]};
            }
            return new int[]{maxThree[2], maxThree[1]};
        }

        // reverse array
        maxThree[0] = maxThree[0] ^ maxThree[2];
        maxThree[2] = maxThree[0] ^ maxThree[2];
        maxThree[0] = maxThree[0] ^ maxThree[2];


        return maxThree;
    }

    private static void searchBigThree(int[][] grid, int[] maxThree, int length) {
        int end = grid.length - (length == 0 ? 0 : 2 * length);
        int end1 = grid[0].length - (length);
        for (int start = 0; start < end; start++) {
            for (int start1 = length; start1 < end1; start1++) {
                if (start + start1 >= length) {
                    addToMaxThree(maxThree, getSum(grid, start, start1, length));
                }
            }
        }
    }

    /*
    get sum of edges of rhombus abcd
            a
           / \
          d   b
           \ /
            c

    */
    private static int getSum(int[][] grid, int i, int j, int length) {
        if (length == 0) {
            return grid[i][j];
        }

        int sum = 0;
        // edge ab
        for (int it = 0; it <= length; it++) {
            sum = sum + grid[i + it][j + it];
        }

        // edge ad
        for (int it = 1; it <= length; it++) {
            sum = sum + grid[i + it][j - it];
        }

        // edge dc
        for (int it = 1; it <= length; it++) {
            sum = sum + grid[i + length + it][j - length + it];
        }

        // edge bc
        for (int it = 1; it < length; it++) {
            sum = sum + grid[i + length + it][j + length - it];
        }

        return sum;
    }

    private static void addToMaxThree(int[] maxThree, int num) {
        // Do not add duplicate entry
        if (maxThree[0] == num || maxThree[1] == num || maxThree[2] == num) {
            return;
        }

        Arrays.sort(maxThree);

        if (maxThree[0] < num) {
            maxThree[0] = num;
        }
    }
}