package dpPrac.zeroOneKnapsack;

import java.util.Arrays;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};
        int W = 7;
        System.out.println(knapsackII(wt, val, W, wt.length));
    }

    static int knapsack(int[] wt, int[] val, int W, int n) {

        if (n == 0 || W == 0)
            return 0;

        if (wt[n - 1] <= W) {
            return Math.max(val[n - 1] +
                            knapsack(wt, val, W - wt[n - 1], n - 1),
                    knapsack(wt, val, W, n - 1)
            );
        } else {
            return knapsack(wt, val, W, n - 1);
        }
    }

    // memoization
    static int knapsackI(int[] wt, int[] val, int W, int n) {
        int[][] t = new int[n + 1][W + 1];

        // initialize with -1
        for (int[] i : t) {
            Arrays.fill(i, -1);
        }

        return solve(wt, val, W, n, t);
    }

    private static int solve(int[] wt, int[] val, int W, int n, int[][] t) {

        if (n == 0 || W == 0)
            return 0;

        if (t[n][W] != -1)
            return t[n][W];

        if (wt[n - 1] <= W) {
            return t[n][W] = Math.max(
                    val[n - 1] + solve(wt, val, W - wt[n - 1], n - 1, t),
                    solve(wt, val, W, n - 1, t)
            );
        } else {
            return t[n][W] = solve(wt, val, W, n - 1, t);
        }
    }

    // top doWn
    static int knapsackII(int[] wt, int[] val, int W, int n) {

        int[][] t = new int[n + 1][W + 1];
        for (int[] i : t)
            Arrays.fill(i, 0);
        // build table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    t[i][j] = Math.max(
                            val[i - 1] + t[i - 1][j - wt[i - 1]],
                            t[i - 1][j]);
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return t[n][W];

    }
}
