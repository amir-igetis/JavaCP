package graphPrac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversals {

    static ArrayList<Integer> bfs(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[v + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;
        while (!q.isEmpty()) {
            int node = q.peek();
            q.poll();
            ans.add(node);
            for (Integer it : adj.get(node)) {
                if (!vis[it]) {
                    q.add(it);
                    vis[it] = true;
                }
            }
        }
        return ans;
    }

    private static void dfsHelper(int node, boolean[] vis, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        ans.add(node);
        for (Integer it : adj.get(node)) {
            if (!vis[it]) {
                dfsHelper(it, vis, ans, adj);
            }
        }
    }

    static ArrayList<Integer> dfs(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[v + 1];
        vis[1] = true;
        dfsHelper(1, vis, ans, adj);
        return ans;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void main(String[] args) {
        // soln for
        int v = 9;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= v; i++)
            adj.add(new ArrayList<>());

        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 3, 6);
        addEdge(adj, 3, 7);
        addEdge(adj, 5, 8);
        addEdge(adj, 6, 9);
        addEdge(adj, 8, 9);

//        ArrayList<Integer> ans = dfs(v, adj);
//        for (Integer it : ans)
//            System.out.print(it + " ");
//        System.out.println();

        ArrayList<Integer> ans = bfs(v, adj);
        for (Integer it : ans)
            System.out.print(it + " ");
        System.out.println();
    }
}
