package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfSetsOfKNonOverlappingLineSegments {
    public static void main(String[] args) {
        int n = 4, k = 2;
        System.out.println(numberOfSets(n, k));
    }

    // dp
    /// Let n be the number of points and k be the target number of line segments.
    ///
    /// Time complexity: O(nk).
    ///
    /// There are O(nk) states, and each state takes O(1) time to compute.
    ///
    /// Space complexity: O(n).
    ///
    /// The rolling array dp and the prefix sums array prefixSums both require O(n) space.
    private static final int MOD = 1000000007;

    static int numberOfSets(int n, int k) {
        int[] dp = new int[n];
        int[] prefixSums = new int[n + 1];
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
        }
        for (int i = 1; i <= k; i++) {
            dp[0] = 0;
            for (int j = 1; j < n; j++) {
                dp[j] = (dp[j - 1] + prefixSums[j]) % MOD;
            }
            for (int j = 0; j < n; j++) {
                prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
            }
        }
        return dp[n - 1];
    }

    // combinatorics
    /// Let M=10^9 +7 be the modulus.
    ///
    /// Time complexity: O(k+logM).
    ///
    /// Computing the binomial coefficient takes O(k+logM) time.
    ///
    /// Space complexity: O(1).
    private static final long MODI = 1000000007L;

    private static long quickPow(long a, long e) {
        long result = 1;
        while (e > 0) {
            if ((e & 1) != 0) result = (result * a) % MODI;
            a = (a * a) % MODI;
            e >>= 1;
        }
        return result;
    }

    static int numberOfSetsI(int n, int k) {
        int m = 2 * k;
        long numerator = 1, denominator = 1;
        for (int i = 1; i <= m; i++) {
            numerator = (numerator * (n + k - i)) % MODI;
            denominator = (denominator * i) % MODI;
        }
        return (int) ((numerator * quickPow(denominator, MODI - 2)) % MODI);
    }
}
