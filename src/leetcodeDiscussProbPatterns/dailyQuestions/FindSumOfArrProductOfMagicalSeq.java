package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindSumOfArrProductOfMagicalSeq {
    public static void main(String[] args) {
        int m = 5, k = 5;
        int[] nums = {1, 10, 100, 10000, 1000000};
        System.out.println(magicalSum(m, k, nums));
    }

    private static final int MOD = 1_000_000_007;

    static int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;
        if (m == 0) return (k == 0) ? 1 : 0;

        long[] fact = new long[m + 1];
        long[] invFact = new long[m + 1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[m] = modInverse(fact[m]);
        for (int i = m - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        long[][] powNums = new long[n][m + 1];
        for (int j = 0; j < n; j++) {
            powNums[j][0] = 1;
            long base = ((nums[j] % MOD) + MOD) % MOD;
            for (int t = 1; t <= m; t++) powNums[j][t] = (powNums[j][t - 1] * base) % MOD;
        }

        int maxCarry = m;
        int maxOnes = Math.min(k, m + 20);
        int K = k;

        long[][][] dp = new long[m + 1][maxCarry + 1][K + 1];
        dp[0][0][0] = 1L;

        for (int j = 0; j < n; j++) {
            long[][][] next = new long[m + 1][maxCarry + 1][K + 1];

            for (int used = 0; used <= m; used++) {
                for (int carry = 0; carry <= maxCarry; carry++) {
                    for (int ones = 0; ones <= K; ones++) {
                        long cur = dp[used][carry][ones];
                        if (cur == 0) continue;

                        int remain = m - used;
                        for (int t = 0; t <= remain; t++) {
                            int newUsed = used + t;
                            int sum = carry + t;
                            int bit = sum & 1;
                            int newOnes = ones + bit;
                            if (newOnes > K)
                                break;
                            int newCarry = sum >>> 1;
                            long weight = cur;
                            long mult = (powNums[j][t] * invFact[t]) % MOD;
                            weight = (weight * mult) % MOD;

                            next[newUsed][newCarry][newOnes] += weight;
                            if (next[newUsed][newCarry][newOnes] >= MOD) next[newUsed][newCarry][newOnes] -= MOD;
                        }
                    }
                }
            }

            dp = next;
        }

        long ansInner = 0L;
        for (int carry = 0; carry <= maxCarry; carry++) {
            int extra = Integer.bitCount(carry);
            for (int ones = 0; ones <= K; ones++) {
                int totalOnes = ones + extra;
                if (totalOnes == K) {
                    long v = dp[m][carry][ones];
                    if (v != 0) {
                        ansInner += v;
                        if (ansInner >= MOD) ansInner -= MOD;
                    }
                }
            }
        }
        long ans = (ansInner * fact[m]) % MOD;
        return (int) ans;
    }

    private static long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private static long modPow(long a, long e) {
        long res = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return res;
    }


    // leetcode solution

    private static int magicalSumI(int m, int k, int[] nums) {
        int n = nums.length;
        long mod = 1000000007;
        long[] fac = new long[m + 1];
        fac[0] = 1;
        for (int i = 1; i <= m; i++) {
            fac[i] = (fac[i - 1] * i) % mod;
        }
        long[] ifac = new long[m + 1];
        ifac[0] = 1;
        ifac[1] = 1;
        for (int i = 2; i <= m; i++) {
            ifac[i] = quickmul(i, mod - 2, mod);
        }
        for (int i = 2; i <= m; i++) {
            ifac[i] = (ifac[i - 1] * ifac[i]) % mod;
        }
        long[][] numsPower = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            numsPower[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                numsPower[i][j] = (numsPower[i][j - 1] * nums[i]) % mod;
            }
        }
        long[][][][] f = new long[n][m + 1][m * 2 + 1][k + 1];
        for (int j = 0; j <= m; j++) {
            f[0][j][j][0] = (numsPower[0][j] * ifac[j]) % mod;
        }
        for (int i = 0; i + 1 < n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int p = 0; p <= m * 2; p++) {
                    for (int q = 0; q <= k; q++) {
                        int q2 = (p % 2) + q;
                        if (q2 > k) {
                            break;
                        }
                        for (int r = 0; r + j <= m; r++) {
                            int p2 = p / 2 + r;
                            f[i + 1][j + r][p2][q2] +=
                                    (((f[i][j][p][q] * numsPower[i + 1][r]) % mod) *
                                            ifac[r]) %
                                            mod;
                            f[i + 1][j + r][p2][q2] %= mod;
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int p = 0; p <= m * 2; p++) {
            for (int q = 0; q <= k; q++) {
                if (Integer.bitCount(p) + q == k) {
                    res = (res + ((f[n - 1][m][p][q] * fac[m]) % mod)) % mod;
                }
            }
        }
        return (int) res;
    }

    private static long quickmul(long x, long y, long mod) {
        long res = 1;
        long cur = x % mod;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * cur) % mod;
            }
            y >>= 1;
            cur = (cur * cur) % mod;
        }
        return res;
    }

}
