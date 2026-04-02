package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountSubmatricesWithEqualFrequencyOfXAndY {
    public static void main(String[] args) {
        char[][] grid = {{'X', 'Y', '.'}, {'Y', '.', '.'}};
        System.out.println(numberOfSubmatrices(grid));
    }

    static int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] sumX = new int[cols];
        int[] sumY = new int[cols];
        int res = 0;

        for (int i = 0; i < rows; i++) {
            int rx = 0, ry = 0;

            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'X') rx++;
                else if (grid[i][j] == 'Y') ry++;

                sumX[j] += rx;
                sumY[j] += ry;

                if (sumX[j] > 0 && sumX[j] == sumY[j]) res++;
            }
        }

        return res;
    }
}
