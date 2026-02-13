package LeetcodeInterview.dp;

import java.util.*;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(coinChange(coins, amount));
    }

    static int coinChange(int[] coins, int amount) {
        return dfs(coins, amount);
    }

    private static int dfs(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dfs(coins, amount - coin);
            if (sub != Integer.MAX_VALUE) {
                res = Math.min(res, sub + 1);
            }
        }
        return res;
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int coinChangeI(int[] coins, int amount) {
        if (amount == 0) return 0;
        int res = dfsI(coins, amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfsI(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;
        if (memo.containsKey(amount)) return memo.get(amount);

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dfsI(coins, amount - coin);
            if (sub != Integer.MAX_VALUE) {
                res = Math.min(res, sub + 1);
            }
        }
        memo.put(amount, res);
        return res;
    }

    static int coinChangeII(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
//        return dp[amount] > amount ? -1 : dp[amount];
        if (dp[amount] > amount)
            return -1;
        else return dp[amount];
    }


    static int coinChangeIII(int[] coins, int amount) {
        if (amount == 0) return 0;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        q.add(amount);
        visited[amount] = true;

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                for (int coin : coins) {
                    int next = curr - coin;
                    if (next == 0) return steps;
                    if (next > 0 && !visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
