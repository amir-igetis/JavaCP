package striverAToZ.graphs.topoSortProbs;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class KahnAlgorithm {
    public static void main(String[] args) {
        int V = 6;

        // Create adjacency list for the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        ArrayList<Integer> ans = topoSort(V, adj);
        for (Integer it : ans)
            System.out.print(it + " ");
        System.out.println();

        int[] ansI = topoSortI(V, adj);
        for (int it : ansI)
            System.out.print(it + " ");
        System.out.println();
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0)
                dfs(it, adj, vis, st);
        }

        st.push(node);
    }

    static ArrayList<Integer> topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 1; i < V; i++) {
            if (vis[i] == 0)
                dfs(i, adj, vis, st);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
        return ans;
    }

    // using BFS
    static int[] topoSortI(int V, ArrayList<java.util.ArrayList<Integer>> adj) {
        // Create an array to store the in-degree of each vertex
        int[] indegree = new int[V];

        // Loop over all vertices to calculate in-degree
        for (int i = 0; i < V; i++) {
            // Loop over all adjacent vertices of current vertex
            for (int it : adj.get(i)) {
                // Increase in-degree of connected vertex
                indegree[it]++;
            }
        }

        // Create a queue to store vertices with in-degree zero
        Queue<Integer> q = new java.util.LinkedList<>();

        // Loop through all vertices
        for (int i = 0; i < V; i++) {
            // If in-degree is zero, add it to queue
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Array to store the topological order
        int[] topo = new int[V];
        // Index to track position in topo array
        int idx = 0;

        // Process vertices in queue
        while (!q.isEmpty()) {
            // Remove vertex from queue
            int node = q.poll();

            // Add it to the topological order
            topo[idx++] = node;

            // Loop through adjacent vertices of the current node
            for (int it : adj.get(node)) {
                // Reduce in-degree of connected vertex
                indegree[it]--;
                // If in-degree becomes zero, push it to queue
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        // Return the topological ordering
        return topo;
    }
}
