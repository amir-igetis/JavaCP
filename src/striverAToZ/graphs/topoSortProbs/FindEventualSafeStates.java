package striverAToZ.graphs.topoSortProbs;

import java.util.*;

public class FindEventualSafeStates {
    public static void main(String[] args) {
        // Adjacency list representation of the graph
        List<Integer>[] adj = new ArrayList[12];
        for (int i = 0; i < 12; i++) {
            adj[i] = new ArrayList<>();
        }

        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);
        adj[2].add(4);
        adj[3].add(4);
        adj[4].add(5);
        adj[5].add(6);
        adj[6].add(7);
        adj[8].add(1);
        adj[8].add(9);
        adj[9].add(10);
        adj[10].add(8);
        adj[11].add(9);

        int V = 12;  // Number of nodes in the graph

        List<Integer> safeNodes = eventualSafeNodes(V, adj);  // Call function to get safe nodes

        for (int node : safeNodes) {
            System.out.print(node + " ");  // Print the safe nodes
        }
        System.out.println();
    }

    // O(V + E) + O(N log N)  & sc O(3 N) or O(n)
    static List<Integer> eventualSafeNodes(int V, List<Integer>[] adj) {
        List<Integer>[] adjRev = new List[V];  // Reverse adjacency list
        int[] indegree = new int[V];  // Indegree array to track nodes with no outgoing edges

        // Initialize reverse adjacency list
        for (int i = 0; i < V; i++) {
            adjRev[i] = new ArrayList<>();
        }

        // Build the reverse graph and calculate indegrees
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj[i]) {
                adjRev[neighbor].add(i);  // Reverse the direction of edges
                indegree[i]++;  // Increment indegree for the current node
            }
        }

        Queue<Integer> q = new LinkedList<>();  // Queue to store nodes with no outgoing edges
        List<Integer> safeNodes = new ArrayList<>();

        // Add all nodes with 0 indegree to the queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // Process the queue to find all safe nodes
        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);  // This node is safe
            for (int parent : adjRev[node]) {
                indegree[parent]--;  // Decrease indegree of the parent nodes
                if (indegree[parent] == 0) {
                    q.offer(parent);  // If indegree becomes 0, it is a safe node
                }
            }
        }

        Collections.sort(safeNodes);  // Sort the safe nodes
        return safeNodes;
    }

}
