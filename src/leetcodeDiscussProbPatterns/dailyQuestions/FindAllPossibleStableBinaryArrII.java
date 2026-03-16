package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindAllPossibleStableBinaryArrII {
    public static void main(String[] args) {
        int zero = 1, one = 1, limit = 2;
        System.out.println(numberOfStableArrays(zero, one, limit));
    }

    static int numberOfStableArrays(int zero, int one, int limit) {
        final long mod = 1_000_000_007;
        // store[i][j][0] -> ending in 0
        // store[i][j][1] -> ending in 1
        long[][][] store = new long[zero + 1][one + 1][2];

        // Base cases: filling arrays with only 0s
        for (int i = 1; i <= zero; i++) {
            if (i <= limit) store[i][0][0] = 1;
        }
        // Base cases: filling arrays with only 1s
        for (int j = 1; j <= one; j++) {
            if (j <= limit) store[0][j][1] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // To end in 0: we can append 0 to any stable array of (i-1, j)
                // store[i][j][0] = store[i-1][j][0] + store[i-1][j][1]
                store[i][j][0] = (store[i - 1][j][0] + store[i - 1][j][1]) % mod;

                // If we exceed the limit of 0s, subtract the sequences that
                // would have had (limit + 1) consecutive 0s.
                if (i > limit) {
                    store[i][j][0] = (store[i][j][0] - store[i - limit - 1][j][1] + mod) % mod;
                }

                // To end in 1: we can append 1 to any stable array of (i, j-1)
                store[i][j][1] = (store[i][j - 1][0] + store[i][j - 1][1]) % mod;

                // If we exceed the limit of 1s, subtract the sequences that
                // would have had (limit + 1) consecutive 1s.
                if (j > limit) {
                    store[i][j][1] = (store[i][j][1] - store[i][j - limit - 1][0] + mod) % mod;
                }
            }
        }

        return (int) ((store[zero][one][0] + store[zero][one][1]) % mod);

    }
}
