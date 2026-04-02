package leetcodeDiscussProbPatterns.dailyQuestions;

public class MatSimilarityAfterCyclicShifts {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 4;
        System.out.println(areSimilar(mat, k));
    }

    static boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k = k % n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != mat[i][(j + k) % n])
                    return false;
            }
        }
        return true;
    }
}
