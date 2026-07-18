package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindTheNumOfSubseqWithEqualGCD {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(subsequencePairCount(nums));
    }

    // DP tc O(nm^2logm) sc O(m^2)
    private static final int MOD = 1000000007;

    static int subsequencePairCount(int[] nums) {
        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }

        int[][] dp = new int[m + 1][m + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] ndp = new int[m + 1][m + 1];
            for (int j = 0; j <= m; j++) {
                int divisor1 = gcd(j, num);
                for (int k = 0; k <= m; k++) {
                    int val = dp[j][k];
                    if (val == 0) {
                        continue;
                    }
                    int divisor2 = gcd(k, num);
                    ndp[j][k] = (ndp[j][k] + val) % MOD;
                    ndp[divisor1][k] = (ndp[divisor1][k] + val) % MOD;
                    ndp[j][divisor2] = (ndp[j][divisor2] + val) % MOD;
                }
            }
            dp = ndp;
        }

        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = (ans + dp[j][j]) % MOD;
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}

