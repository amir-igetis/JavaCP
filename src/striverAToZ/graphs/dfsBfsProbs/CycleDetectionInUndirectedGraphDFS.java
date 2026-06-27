package striverAToZ.graphs.dfsBfsProbs;

import java.util.ArrayList;

// Question No. - 06
public class CycleDetectionInUndirectedGraphDFS {
    public static void main(String[] args) {
//     Example: Graph with 5 nodes and a cycle
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Add edges
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(4).add(3);
        adj.get(4).add(1);

        if (isCycle(V, adj))
            System.out.println("Cycle detected");
        else
            System.out.println("No cycle found");
    }

    static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                if (dfs(i, -1, adj, vis))
                    return true;
        }
        return false;
    }

    private static boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!vis[neighbor]) {
                if (dfs(neighbor, node, adj, vis))
                    return true;
            } else if (neighbor != parent)
                return true;
        }
        return false;
    }
}

