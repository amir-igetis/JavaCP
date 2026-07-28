package striverAToZ.dynamicProgramming.introduction;

import java.util.Arrays;

public class DPIntro {
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(fib(n, dp));
    }

    static int fib(int n, int[] dp) {
        if (n <= 1)
            return n;
        if (dp[n] != -1)
            return dp[n];
        dp[n] = fib(n - 1, dp) + fib(n - 2, dp);

        return dp[n];
    }

    // tabulation
    static int fibI(int n) {
        // If n is 0 or 1, return n
        if (n <= 1) return n;

        // Create dp array
        int[] dp = new int[n + 1];

        // Initialize base cases
        dp[0] = 0;
        dp[1] = 1;

        // Fill dp array iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return final answer
        return dp[n];
    }

    // space optimization
    static int fibII(int n) {
        // If n is 0 return 0
        if (n == 0) return 0;
        // If n is 1 return 1
        if (n == 1) return 1;

        // prev2 stores fib(n-2)
        int prev2 = 0;
        // prev stores fib(n-1)
        int prev = 1;
        // curr stores current fib
        int curr = 0;

        // Loop from 2 to n
        for (int i = 2; i <= n; i++) {
            // Calculate current fib
            curr = prev + prev2;
            // Update prev2
            prev2 = prev;
            // Update prev
            prev = curr;
        }
        // Return final answer
        return prev;
    }
}

