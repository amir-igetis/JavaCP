package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxMatrixSum {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        System.out.println(maxMatrixSumI(matrix));
    }

    //    tc o(n * m) & sc o(1)
    static long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbsVal = Integer.MAX_VALUE;
        int negativeCount = 0;

        for (int[] row : matrix) {
            for (int val : row) {
                totalSum += Math.abs(val);
                if (val < 0) {
                    negativeCount++;
                }
                minAbsVal = Math.min(minAbsVal, Math.abs(val));
            }
        }

        // Adjust if the count of negative numbers is odd
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsVal;
        }

        return totalSum;
    }

    // previous solution

    static long maxMatrixSumI(int[][] matrix) {
        int n = matrix.length;
        long totalSum = 0;
        int negativeCount = 0;
        int smallestAbsVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                totalSum += Math.abs(val);
                if (val < 0)
                    negativeCount++;
                smallestAbsVal = Math.min(smallestAbsVal, Math.abs(val));
            }
        }

        if (negativeCount % 2 == 1)
            totalSum -= 2 * smallestAbsVal;

        return totalSum;
    }

}
