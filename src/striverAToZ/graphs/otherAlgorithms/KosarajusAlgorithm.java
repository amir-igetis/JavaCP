package striverAToZ.graphs.otherAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KosarajusAlgorithm {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}
        };

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
        }

        int ans = kosaraju(n, adj);
        System.out.println("The number of strongly connected components is: " + ans);

    }

    // Step 1: Perform DFS and store nodes in stack by finishing time
    private static void dfs(int node, int[] vis, List<Integer>[] adj, Stack<Integer> st) {
        vis[node] = 1;
        for (int it : adj[node]) {
            if (vis[it] == 0) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node);
    }

    // Step 2: Perform DFS on transposed graph
    private static void dfs3(int node, int[] vis, List<Integer>[] adjT) {
        vis[node] = 1;
        for (int it : adjT[node]) {
            if (vis[it] == 0) {
                dfs3(it, vis, adjT);
            }
        }
    }

    /// tc and sc O(V + E)
    // Function to find number of strongly connected components
    static int kosaraju(int V, List<Integer>[] adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        // Step 1: Do DFS to fill stack
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, adj, st);
            }
        }

        // Step 2: Build transpose graph
        List<Integer>[] adjT = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjT[i] = new ArrayList<>();
        }
        Arrays.fill(vis, 0);

        for (int i = 0; i < V; i++) {
            for (int it : adj[i]) {
                adjT[it].add(i);
            }
        }

        // Step 3: Process stack to count SCCs
        int scc = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (vis[node] == 0) {
                scc++;
                dfs3(node, vis, adjT);
            }
        }
        return scc;
    }
}
