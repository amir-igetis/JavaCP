package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class NetworkDelayTime {

    ///  Question 9,
    /// Problem Statement: You are given a directed weighted graph representing a communication network with n nodes,
    /// numbered from 1 to n. The graph is provided as an edge list times, where each record is of the form (ui, vi, wi) where,
    /// ui is the source node of the directed edge
    /// vi is the target node of the directed edge
    /// wi is the time it takes for a signal to travel from ui to vi (non-negative and integer)
    ///
    /// A single signal is injected at node k at time 0. The signal propagates 1-way along the directed edges and
    /// whenever it reaches a node, that node immediately retransmits the signal to all of its outgoing neighbors,
    /// each traversal taking exactly the edge’s weight wi units of time.
    ///
    /// Return the minimum time required for every node in the network to receive the signal. If some node is unreachable, return -1.

    public static void main(String[] args) {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4, k = 2;
        System.out.println(networkDelayTime(times, n, k));
    }

    /// Time Complexity: O((E + V) * log V), Each edge is relaxed atmost once. Priority queue operations take log V time.
    /// Space Complexity: O(V+E), We store the adjacency list, an in-degree array and a priority queue in order to find
    /// the minimum time required for every node in the network to receive the signal.

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
