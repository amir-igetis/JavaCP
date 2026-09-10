package leetcodeDiscussProbPatterns.dailyQuestions;

public class DistinctSubseq {
    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println(numDistinct(s, t));
    }

    static int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    // dp

    /// Time Complexity: O(mn)
    /// where m and n are the lengths of strings s and t, respectively. The 2D array dp has m+1 rows and n+1 columns, and each element in dp needs to be calculated.
    /// Space Complexity: O(mn)
    /// where m and n are the lengths of strings s and t, respectively. A 2D array dp with m+1 rows and n+1 columns is created.
    static int numDistinctI(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    // dp (space optimized)
    static int numDistinctII(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = 0; j < n; j++) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[j] = dp[j + 1] + dp[j];
                }
            }
        }

        return dp[0];
    }

}
