package striverAToZ.graphs.mstOrDisjointSet;

import java.util.HashSet;
import java.util.Set;

public class NumOfOpsToMakeNetworkConnected {
    public static void main(String[] args) {
        int[][] connections = {
                {0, 1}, {0, 2}, {0, 3}, {1, 4}
        };
        int n = 6;
        System.out.println(makeConnected(n, connections));
    }

    /// Function to find minimum operations to make the graph connected,
    /// tc O(N + M × α(N)) sc O(N)
    static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        for (int[] edge : connections)
            union(parent, rank, edge[0], edge[1]);

        Set<Integer> components = new HashSet<>();
        for (int i = 0; i < n; i++)
            components.add(find(parent, i));

        return components.size() - 1;
    }

    private static void union(int[] parent, int[] rank, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);
        if (px == py)
            return;
        if (rank[px] < rank[py])
            parent[px] = py;
        else if (rank[px] > rank[py])
            parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}
