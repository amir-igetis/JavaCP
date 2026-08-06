package striverAToZ.graphs.shortestPathAlgoAndProbs;

import java.util.*;

public class CheapestFlightWithinKStops {

    /// Question 7
    /// Problem Statement: There are n cities and m edges connected by some number of flights. You are given an
    /// array of flights where flights(i) = ( fromi, toi, pricei) indicates that there is a flight from city fromi to
    /// city toi with cost price. You have also given three integers src, dst, and k, and return the cheapest price
    /// from src to dst with at most k stops. If there is no such route, return -1.

    public static void main(String[] args) {
// Driver Code
        int n = 4, src = 0, dst = 3, K = 1;

        // Flight routes and their costs
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600},
                {2, 3, 200}};


        // Call the method to find the cheapest flight
        int ans = cheapestFLight(n, flights, src, dst, K);

        // Output the result
        System.out.println(ans);
    }

    /// Time Complexity: O(N), where the additional log(N) time is eliminated by using a simple queue rather than a
    /// priority queue, which is usually used in Dijkstra’s Algorithm. Where N = Number of flights / Number of edges.
    ///
    /// Space Complexity: O(|E| + |V|), for the adjacency list, priority queue, and the dist array. Where
    /// E = Number of edges (flights.size()) and V = Number of airports.

    // this is the modified code for the dijkstra algo
    static int cheapestFLight(int n, int[][] flights, int src, int dst, int K) {

        // Create the adjacency list to represent airports and flights as a graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges for the flights to the adjacency list
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // Create a queue to store the node, its distance from the source, and the number of stops
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, src, 0});  // Push the source node with 0 stops and 0 cost

        // Create a distance array to store the minimum cost to reach each node
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // BFS traversal with a queue to process the nodes
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int stops = current[0];  // Number of stops so far
            int node = current[1];  // Current node
            int cost = current[2];  // Cost to reach the current node

            // If the number of stops exceeds K, continue to the next iteration
            if (stops > K)
                continue;

            // Iterate over all the adjacent nodes (next destinations)
            for (int[] adjNode : adj.get(node)) {
                int nextNode = adjNode[0];  // Next destination node
                int edW = adjNode[1];  // Cost of the flight to the next destination

                // If a shorter path to the adjacent node is found, update the distance
                if (cost + edW < dist[nextNode] && stops <= K) {
                    dist[nextNode] = cost + edW;  // Update the distance
                    q.offer(new int[]{stops + 1, nextNode, cost + edW});  // Push the new node with updated stops and cost
                }
            }
        }

        // If destination node is unreachable, return -1
        if (dist[dst] == Integer.MAX_VALUE)
            return -1;

        return dist[dst];  // Return the minimum cost to reach the destination
    }
}
