package leetcodeDiscussProbPatterns.dailyQuestions;

public class DetermineWhetherMatCanBeObtainedByRotation {
    public static void main(String[] args) {
        int[][] mat = {{0, 1}, {1, 1}}, target = {{1, 0}, {0, 1}};
        System.out.println(findRotation(mat, target));
    }

    static boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (isEqual(mat, target)) return true;
            rotate(mat);
        }

        return false;
    }

    private static void rotate(int[][] mat) {
        int n = mat.length;

        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    private static boolean isEqual(int[][] a, int[][] b) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }

        return true;
    }
}
