package dpPrac.zeroOneKnapsack;

public class CountTheNumberOfSubsetWithGivenDiff {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int diff = 1;

        System.out.println(countTheNumOfSubsetWithGivenDiff(arr, diff));
    }

    static int countTheNumOfSubsetWithGivenDiff(int[] arr, int diff) {
        int sum = 0;
        for (int i : arr)
            sum += i;
        if ((sum + diff) % 2 != 0)
            return 0;
        int target = (sum + diff) / 2;
        return countSubsetI(arr, target);

    }

    private static int countSubsetI(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int j = 1; j <= k; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]]
                            + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][k];
    }
}
