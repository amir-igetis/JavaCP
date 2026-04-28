package dpPrac.zeroOneKnapsack;

import java.util.Arrays;

public class MinSubsetSumDifference {
    public static void main(String[] args) {
        int[] arr = {1, 2, 7};

        System.out.println(minSubsetSumDiffI(arr));
    }

    static int minSubsetSumDiff(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i : arr)
            sum += i;

        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++) {
            if (dp[n][i]) {
                int s2 = sum - i;
                minDiff = Math.min(minDiff, Math.abs(s2 - i));
            }
        }
        return minDiff;

    }

    // space optimized
    static int minSubsetSumDiffI(int[] arr) {
        int sum = 0;
        for (int x : arr) sum += x;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        int minDiff = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= sum / 2; s1++) {
            if (dp[s1]) {
                minDiff = Math.min(minDiff, sum - 2 * s1);
            }
        }

        return minDiff;
    }

}

