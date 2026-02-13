package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxDotProductOfTwoSubseq {
    public static void main(String[] args) {
        int[] nums1 = {2, 1, -1, 5};
        int[] nums2 = {3, 0, -6};
        System.out.println(maxDotProduct(nums1, nums2));
    }

    static int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= m; j++)
                dp[i][j] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int takeBoth = nums1[i - 1] * nums2[j - 1];
                if (dp[i - 1][j - 1] > 0)
                    takeBoth += dp[i - 1][j - 1];

                dp[i][j] = Math.max(
                        takeBoth,
                        Math.max(dp[i - 1][j], dp[i][j - 1])
                );
            }
        }
        return dp[n][m];
    }
}
