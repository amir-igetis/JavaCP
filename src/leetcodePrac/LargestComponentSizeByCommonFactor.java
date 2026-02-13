package leetcodePrac;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class LargestComponentSizeByCommonFactor {
    public static void main(String[] args) {
        int[] arr = {4, 6, 15, 35};
        System.out.println(largestComponentSize(arr)); // Output: 4
    }

    static int largestComponentSize(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        UnionFind uf = new UnionFind(max + 1);

        // Union each number with its prime factors
        for (int num : nums) {
            for (int factor = 2; factor * factor <= num; factor++) {
                if (num % factor == 0) {
                    uf.union(num, factor);
                    uf.union(num, num / factor);
                }
            }
        }

        // Count component sizes
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int root = uf.find(num);
            count.put(root, count.getOrDefault(root, 0) + 1);
            res = Math.max(res, count.get(root));
        }
        return res;
    }

    // ---------- DSU Implementation ----------
    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

