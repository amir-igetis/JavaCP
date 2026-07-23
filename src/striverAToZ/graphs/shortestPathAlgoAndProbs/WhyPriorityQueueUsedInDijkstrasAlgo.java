package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class WhyPriorityQueueUsedInDijkstrasAlgo {
    public static void main(String[] args) {
        int V = 5;

        // Adjacency list {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Example edges
        adj.get(0).add(new int[]{1, 2});
        adj.get(0).add(new int[]{2, 4});
        adj.get(1).add(new int[]{2, 1});
        adj.get(1).add(new int[]{3, 7});
        adj.get(2).add(new int[]{4, 3});
        adj.get(3).add(new int[]{4, 2});

        int[] dist = dijkstra(V, adj, 0);

        // Print shortest distances
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from 0 to " + i + " = " + dist[i]);
        }
    }

   /* In a weighted graph, the goal of Dijkstra’s algorithm is to find the shortest path from a source node to all other nodes. The idea is to always expand the closest node not yet processed. Using a priority queue (min-heap) ensures that we can efficiently pick the node with the smallest current distance, instead of scanning all nodes each time.

    The algorithm starts with the source node at distance 0. At each step, the priority queue pops the node with the smallest distance. For every neighbor of that node, if the new distance through this node is shorter than the current stored distance, we update it and push the neighbor into the priority queue. This process continues until all nodes are processed.
    Initialize a distance array with infinity for all nodes except the source (0).
    Use a priority queue (min-heap) to store pairs of (distance, node).
    Push the source with distance 0 into the queue.
    While the queue is not empty, pop the node with the smallest distance.
    If the popped distance is greater than the stored distance, skip it (already processed).
    For each neighbor of the current node, calculate the new distance.
    If the new distance is smaller than the stored one, update it and push the neighbor into the queue.
    After processing, the distance array will hold the shortest path distances from the source to all nodes.
    */

//    tc O((V+E)logV) sc O(V + E)
    static int[] dijkstra(int V, List<List<int[]>> adj, int src) {
        // Distance array initialized to large value
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);

        // Min-heap storing {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Distance to source is 0
        dist[src] = 0;

        // Push source into heap
        pq.add(new int[]{0, src});

        // Process nodes until heap is empty
        while (!pq.isEmpty()) {
            // Extract node with minimum distance
            int[] cur = pq.poll();
            int d = cur[0];
            int node = cur[1];

            // Skip if outdated distance
            if (d > dist[node]) continue;

            // Traverse all neighbors
            for (int[] edge : adj.get(node)) {
                int next = edge[0];
                int wt = edge[1];

                // Relaxation check
                if (dist[node] + wt < dist[next]) {
                    // Update distance
                    dist[next] = dist[node] + wt;

                    // Push into heap
                    pq.add(new int[]{dist[next], next});
                }
            }
        }
        return dist;
    }
}
