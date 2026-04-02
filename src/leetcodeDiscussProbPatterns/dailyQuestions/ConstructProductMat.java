package leetcodeDiscussProbPatterns.dailyQuestions;

public class ConstructProductMat {
    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {3, 4}};
        int[][] ans = constructProductMatrix(grid);
        for (int[] i : ans) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    static int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int mod = 12345;

        int size = n * m;
        int[] arr = new int[size];

        // flatten grid
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j] % mod;
            }
        }

        // prefix product
        int[] prefix = new int[size];
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * arr[i - 1]) % mod;
        }

        // suffix product
        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * arr[i + 1]) % mod;
        }

        // build result
        int[][] res = new int[n][m];
        idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = (prefix[idx] * suffix[idx]) % mod;
                idx++;
            }
        }

        return res;
    }
}
