package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4, k = 2;
        System.out.println(networkDelayTime(times, n, k));
    }

    // tc O((E + V) * log V) sc O(v + e)
    static int networkDelayTime(int[][] times, int n, int k) {
        // Create adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            adj.get(u).add(new int[]{v, w});
        }

        // Initialize min-heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});

        // Initialize distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Process nodes
        while (!pq.isEmpty()) {
            // Get node with smallest time
            int[] curr = pq.poll();
            int time = curr[0], node = curr[1];

            // Traverse all neighbors of the node
            for (int[] nbr : adj.get(node)) {
                int v = nbr[0], wt = nbr[1];

                // If shorter path to neighbor is found
                if (dist[v] > time + wt) {
                    // Update distance
                    dist[v] = time + wt;
                    // Add updated distance to priority queue
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        // Get maximum time
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }

}
