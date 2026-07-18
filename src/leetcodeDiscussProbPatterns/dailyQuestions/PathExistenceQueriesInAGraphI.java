package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class PathExistenceQueriesInAGraphI {
    public static void main(String[] args) {
        int n = 2;
        int[] nums = {1, 3};
        int maxDiff = 1;
        int[][] queries = {{0, 0}, {0, 1}};
        System.out.println(Arrays.toString(pathExistenceQueries(n, nums, maxDiff, queries)));
        System.out.println(Arrays.toString(pathExistenceQueriesI(n, nums, maxDiff, queries)));
    }

    static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] component = new int[n];
        int id = 0;
        component[0] = id;

        // Assign connected component IDs
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                id++;
            }
            component[i] = id;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = component[u] == component[v];
        }

        return ans;
    }

    // using DSU

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // Path Compression
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py)
                return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    static boolean[] pathExistenceQueriesI(int n, int[] nums, int maxDiff, int[][] queries) {

        DSU dsu = new DSU(n);

        // Union adjacent indices if they are connected
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                dsu.union(i, i - 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = dsu.find(u) == dsu.find(v);
        }

        return ans;
    }

}
