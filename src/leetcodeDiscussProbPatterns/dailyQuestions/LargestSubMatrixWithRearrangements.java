package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class LargestSubMatrixWithRearrangements {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
//        output = 4;
        System.out.println(largestSubmatrix(matrix));
    }

    static int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1)
                    matrix[i][j] += matrix[i - 1][j];

            }
        }
//        for (int i = 0; i < row; i++) {
//            Arrays.sort(matrix[i]);
//            for (int j = 0; j < col; j++)
//                res = Math.max(res, matrix[i][j] * (col - j));
//        }
        for (int[] ints : matrix) {
            Arrays.sort(ints);
            for (int j = 0; j < col; j++)
                res = Math.max(res, ints[j] * (col - j));
        }
        return res;

    }
}
