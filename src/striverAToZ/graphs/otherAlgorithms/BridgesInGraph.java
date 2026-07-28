package striverAToZ.graphs.otherAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgesInGraph {
    public static void main(String[] args) {
    	int n = 4;
        int[][] connections = {{0,1},{1,2},{2,0},{1,3}};

        List<List<Integer>> bridges = criticalConnections(n, connections);

        System.out.println("Critical Connections (Bridges): " + bridges);
    }
    
    private static int timer = 1;
    private static void dfs(int node, int parent, boolean[] vis, 
    		List<List<Integer>> adj, int[] tin, int[] low, 
    		List<List<Integer>> bridges) {
    	vis[node] = true;            // Mark as visited
        tin[node] = low[node] = timer++; // Set discovery and low-link time

        for (int neighbor : adj.get(node)) { // Explore neighbors
            if (neighbor == parent) continue; // Skip parent

            if (!vis[neighbor]) {
                // Recurse on unvisited neighbor
                dfs(neighbor, node, vis, adj, tin, low, bridges);

                // Update low-link value
                low[node] = Math.min(low[node], low[neighbor]);

                // Check if it's a bridge
                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(neighbor, node));
                }
            } else {
                // Back edge: update low-link
                low[node] = Math.min(low[node], low[neighbor]);
            }
        }
    }
    
    
    /// O(V+2E), where V = no. of vertices, E = no. of edges sc O(V+2E)+O(3V)
    static List<List<Integer>> criticalConnections(int n, int[][] connections) {
        // Step 1: Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] conn : connections) {
            int u = conn[0], v = conn[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Step 2: Initialize helper arrays
        boolean[] vis = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();

        // Step 3: Run DFS
        dfs(0, -1, vis, adj, tin, low, bridges);

        return bridges;
    }
}
