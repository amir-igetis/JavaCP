package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class MinHammingDistAfterSwapOperations {
    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4}, target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};
        System.out.println(minimumHammingDistance(source, target, allowedSwaps));
    }

    // hamming dist + union find (DSU) tc O(n+m⋅α(n)) + sc O(n)

    private static int[] fa;
    private static int[] rank;

    private static int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (rank[x] < rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        fa[y] = x;
        if (rank[x] == rank[y]) {
            rank[x]++;
        }
    }

    static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        fa = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }

        for (int[] pair : allowedSwaps) {
            union(pair[0], pair[1]);
        }

        Map<Integer, Map<Integer, Integer>> sets = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int f = find(i);
            sets.putIfAbsent(f, new HashMap<>());
            Map<Integer, Integer> cnt = sets.get(f);
            cnt.put(source[i], cnt.getOrDefault(source[i], 0) + 1);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int f = find(i);
            Map<Integer, Integer> cnt = sets.get(f);
            if (cnt.getOrDefault(target[i], 0) > 0) {
                cnt.put(target[i], cnt.get(target[i]) - 1);
            } else {
                ans++;
            }
        }
        return ans;
    }
}
