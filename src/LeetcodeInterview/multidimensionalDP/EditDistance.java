package LeetcodeInterview.multidimensionalDP;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        System.out.println(minDistanceII(word1, word2));
    }

    static int minDistance(String word1, String word2) {
        return helper(word1, word2, word1.length(), word2.length());
    }

    private static int helper(String w1, String w2, int i, int j) {
        if (i == 0) return j; // insert remaining of w2
        if (j == 0) return i; // delete remaining of w1

        if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
            return helper(w1, w2, i - 1, j - 1);
        } else {
            int insertOp = helper(w1, w2, i, j - 1);
            int deleteOp = helper(w1, w2, i - 1, j);
            int replaceOp = helper(w1, w2, i - 1, j - 1);
            return 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
        }
    }

    private int[][] dp;

    public int minDistanceI(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperI(word1, word2, n, m);
    }

    private int helperI(String w1, String w2, int i, int j) {
        if (i == 0) return j;
        if (j == 0) return i;

        if (dp[i][j] != -1) return dp[i][j];

        if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
            dp[i][j] = helperI(w1, w2, i - 1, j - 1);
        } else {
            int insertOp = helperI(w1, w2, i, j - 1);
            int deleteOp = helperI(w1, w2, i - 1, j);
            int replaceOp = helperI(w1, w2, i - 1, j - 1);
            dp[i][j] = 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
        }
        return dp[i][j];
    }

    static int minDistanceII(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i; // delete all
        for (int j = 0; j <= m; j++) dp[0][j] = j; // insert all

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],  // replace
                            Math.min(dp[i - 1][j],    // delete
                                    dp[i][j - 1]));  // insert
                }
            }
        }
        return dp[n][m];
    }

    static int minDistanceIII(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        if (n < m) return minDistance(word2, word1); // ensure word1 is longer

        int[] prev = new int[m + 1], curr = new int[m + 1];

        for (int j = 0; j <= m; j++) prev[j] = j;

        for (int i = 1; i <= n; i++) {
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1]));
                }
            }
            prev = curr.clone();
        }
        return prev[m];
    }
}
