package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxNumOfNonOverlappingPalinSubstr {
    public static void main(String[] args) {
        String s = "abaccdbbd";
        int k = 3;
        System.out.println(maxPalindromes(s, k));
    }

    // dp

    /// Let n be the length of the string s.
    ///
    /// Time complexity: O(n^2)
    ///
    /// Both preprocessing the palindromic intervals and computing dp require O(n^2)
    ///
    /// Space complexity: O(n^2).
    ///
    /// The two-dimensional array isPalindrome requires O(n^ 2) space, and the array dp requires O(n) space.
    static int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        for (int len = 1; len <= n; ++len) {
            for (int left = 0; left + len <= n; ++left) {
                int right = left + len - 1;
                isPalindrome[left][right] =
                        s.charAt(left) == s.charAt(right) &&
                                (len <= 2 || isPalindrome[left + 1][right - 1]);
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1];
            for (int j = 0; j + k <= i; ++j) {
                if (isPalindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }

    // greedy

    /// Let n be the length of the string s.
    ///
    /// Time complexity: O(nk).
    ///
    /// There are n ending positions. For each position, we check at most two substrings of length at most k+1. Each palindrome check takes O(k) time using two pointers, giving an overall time complexity of O(nk).
    ///
    /// Space complexity: O(1).
    ///
    /// Only a constant number of additional variables are used.
    static int maxPalindromesI(String s, int k) {
        int n = s.length();
        int ans = 0,
                start = 0;

        for (int r = k - 1; r < n; ++r) {
            int l = r - k + 1;
            if (l >= start && check(s, l, r)) {
                ++ans;
                start = r + 1;
                continue;
            }

            l = r - k;
            if (l >= start && check(s, l, r)) {
                ++ans;
                start = r + 1;
            }
        }

        return ans;
    }

    private static boolean check(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
