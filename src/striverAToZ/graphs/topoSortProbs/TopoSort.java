package striverAToZ.graphs.topoSortProbs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopoSort {
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
        for (Integer i : ans)
            System.out.print(ans.get(i) + " ");
        System.out.println();
    }

    //    using dfs tc and sc O(v + e)
    static void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0)
                dfs(it, adj, vis, st);
        }
        st.push(node);
    }

    static ArrayList<Integer> topoSort(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[v];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0)
                dfs(i, adj, vis, st);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty())
            ans.add(st.pop());
        return ans;
    }

    // BFS
    static ArrayList<Integer> topoSortI(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[v];
        // calculating indegree
        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i))
                indegree[it]++;
        }
        Queue<Integer> q = new LinkedList<>();
        // add all nodes to indegree 0
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
//        int[] topo = new int[v];
        ArrayList<Integer> topo = new ArrayList<>();
//        int idx = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
//            topo[idx++] = node;
            topo.add(node);
            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0)
                    q.add(it);
            }
        }
        return topo;
    }

}
