package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class ShiftTwoDGrid {
    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 1;
        List<List<Integer>> ans = shiftGrid(grid, k);
        for (List<Integer> i : ans) {
            for (Integer j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();
    }

    static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {

                // Original index in flattened array
                int currIndex = i * n + j;

                // Find the element that should come here
                int oldIndex = (currIndex - k + total) % total;

                int oldRow = oldIndex / n;
                int oldCol = oldIndex % n;

                ans.get(i).add(grid[oldRow][oldCol]);
            }
        }

        return ans;
    }
}
