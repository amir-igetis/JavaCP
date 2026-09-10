package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class DistinctSubseqII {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(distinctSubseqII(s));
    }

    /// Dynamic Programming
    ///
    /// Time Complexity: O(N), where N is the length of S.
    ///
    /// Space Complexity: O(N). It is possible to adapt this solution to take O(1) space.
    static int distinctSubseqII(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i + 1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i + 1] -= dp[last[x]];
            dp[i + 1] %= MOD;
            last[x] = i;
        }

        dp[N]--;
        if (dp[N] < 0) dp[N] += MOD;
        return dp[N];

    }
}
