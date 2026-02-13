package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MinCostPartWithEdgeRev {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0, 1, 3}, {3, 1, 1}, {2, 3, 4}, {0, 2, 2}};
        System.out.println(minCostI(n, edges));
    }

    private static class Edge {
        int to, w;

        Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    private static class State {
        int node, used;
        long dist;

        State(int n, int u, long d) {
            node = n;
            used = u;
            dist = d;
        }
    }

    //   tc O(n+mlogm).
//    Sc: O(n+m)
    public static int minCost(int n, int[][] edges) {
        List<Edge>[] adj = new ArrayList[n];
        List<Edge>[] revAdj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new Edge(v, w));
            revAdj[v].add(new Edge(u, w)); // incoming edge
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a.dist, b.dist)
        );

        dist[0][0] = 0;
        pq.add(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int u = cur.node;
            int used = cur.used;

            if (cur.dist > dist[u][used]) continue;

            // Normal edges
            for (Edge e : adj[u]) {
                if (dist[e.to][used] > cur.dist + e.w) {
                    dist[e.to][used] = cur.dist + e.w;
                    pq.add(new State(e.to, used, dist[e.to][used]));
                }
            }

            // Reverse edges (only if switch not used)
            if (used == 0) {
                for (Edge e : revAdj[u]) {
                    long cost = cur.dist + 2L * e.w;
                    if (dist[e.to][1] > cost) {
                        dist[e.to][1] = cost;
                        pq.add(new State(e.to, 1, cost));
                    }
                }
            }
        }

        long ans = Math.min(dist[n - 1][0], dist[n - 1][1]);
        return ans == INF ? -1 : (int) ans;
    }

    // leetcode soln
    static int minCostI(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int w = e[2];
            g[x].add(new int[]{y, w});
            g[y].add(new int[]{x, 2 * w});
        }

        // Dijkstra
        int[] d = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0])
        );
        pq.offer(new int[]{0, 0}); // [Distance, Node]

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int x = current[1];

            if (x == n - 1) {
                return dist;
            }

            if (visited[x]) {
                continue;
            }
            visited[x] = true;

            for (int[] neighbor : g[x]) {
                int y = neighbor[0];
                int w = neighbor[1];

                if (dist + w < d[y]) {
                    d[y] = dist + w;
                    pq.offer(new int[]{d[y], y});
                }
            }
        }

        return -1;
    }

}
