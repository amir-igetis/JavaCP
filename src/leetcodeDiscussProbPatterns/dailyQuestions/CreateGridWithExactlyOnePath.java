package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class CreateGridWithExactlyOnePath {
    public static void main(String[] args) {
        int m = 2, n = 3;
        System.out.println(Arrays.toString(createGrid(m, n)));
    }

    // construction

    /// Let m and n be the number of rows and columns in the grid, respectively.
    ///
    /// Time complexity: O(mn).
    ///
    /// Constructing each cell in the grid takes O(1) time, so the overall time complexity is O(mn).
    ///
    /// Space complexity: O(n).
    ///
    /// A temporary string of length n is used to construct each row, requiring O(n) auxiliary space (excluding the output grid).
    static String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        grid[0] = ".".repeat(n);
        String row = "#".repeat(n - 1) + ".";
        for (int i = 1; i < m; i++) {
            grid[i] = row;
        }
        return grid;
    }
}
