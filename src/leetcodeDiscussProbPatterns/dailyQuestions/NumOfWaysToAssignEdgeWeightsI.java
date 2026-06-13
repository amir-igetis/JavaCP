package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class NumOfWaysToAssignEdgeWeightsI {
    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {3, 4}, {3, 5}};
        System.out.println(assignEdgeWeights(edges));
    }

    // Depth-First Search + Mathematics tc & sc O(n)
    private static final int MOD = 1_000_000_007;

    private static int qpow(int x, int y) {
        long res = 1;
        long base = x;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            y >>= 1;
        }
        return (int) res;
    }

    private static int dfs(List<List<Integer>> g, int x, int f) {
        int maxDep = 0;
        for (int y : g.get(x)) {
            if (y == f) continue;
            maxDep = Math.max(maxDep, dfs(g, y, x) + 1);
        }
        return maxDep;
    }

    static int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            g.get(u).add(v);
            g.get(v).add(u);
        }
        int maxDep = dfs(g, 1, 0);
        return qpow(2, maxDep - 1);
    }
}

