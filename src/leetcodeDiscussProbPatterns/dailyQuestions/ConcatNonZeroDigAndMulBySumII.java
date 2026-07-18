package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class ConcatNonZeroDigAndMulBySumII {
    public static void main(String[] args) {
        String s = "10203004";
        int[][] queries = {{0, 7}, {1, 3}, {4, 6}};
        System.out.println(Arrays.toString(sumAndMultiply(s, queries)));
    }


    private static final int MOD = 1000000007;
    private static final int MAX_N = 100001;
    private static final long[] pow10 = new long[MAX_N];

    // static runs only once for all test cases
    static {
        pow10[0] = 1;
        for (int i = 1; i < MAX_N; ++i) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
    }

    static int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] sum = new int[n + 1];
        long[] x = new long[n + 1];
        int[] cnt = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int d = s.charAt(i) - '0';
            sum[i + 1] = sum[i] + d;
            x[i + 1] = d > 0 ? (x[i] * 10 + d) % MOD : x[i];
            cnt[i + 1] = cnt[i] + (d > 0 ? 1 : 0);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0];
            int r = queries[i][1] + 1;
            int length = cnt[r] - cnt[l];
            long val_x = (x[r] - ((x[l] * pow10[length]) % MOD) + MOD) % MOD;
            long val_sum = sum[r] - sum[l];
            res[i] = (int) ((val_x * val_sum) % MOD);
        }
        return res;
    }
}
