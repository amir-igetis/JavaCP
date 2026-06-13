package striverAToZ.graphs.learning;


import java.util.*;

public class GraphRep {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
//            for directed graph just adj[i].add(v);
            adj[u].add(v);
            adj[v].add(u);
        }
        sc.close();
    }


    // printing a graph
    static ArrayList<ArrayList<Integer>> printGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            ans.add(temp);
        }
        int i = 0;
        for (ArrayList<Integer> temp : adj)
            ans.get(i).addAll(temp);

        return ans;
    }

    // bfs of graph
    static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] vis = new boolean[V + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while (!q.isEmpty()) {
            Integer node = q.poll();
            res.add(node);
            for (Integer f : adj.get(node)) {
                if (!vis[f]) {
                    vis[f] = true;
                    q.add(f);
                }
            }

        }
        return res;
    }

    // dfs of Graph
    static ArrayList<Integer> dfs(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V + 1];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                ans.add(i);
                helper(adj, i, vis, ans);
            }
        }
        return ans;
    }

    private static void helper(ArrayList<ArrayList<Integer>> adj, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Integer e : adj.get(src)) {
            if (!vis[e]) {
                ans.add(e);
                helper(adj, e, vis, ans);
            }
        }
    }
}
