package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MinCostToConvertStringI {
    public static void main(String[] args) {
        String source = "abcd", target = "acbe";
        char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost = {2, 5, 5, 1, 2, 20};

        System.out.println(minimumCostI(source, target, original, changed, cost));
    }

    //    dijkstras algo tc O(m + n) and sc O(m)
    static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        List<int[]>[] graph = new List[26];
        for (int i = 0; i < 26; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < original.length; i++) {
            graph[original[i] - 'a'].add(new int[]{changed[i] - 'a', cost[i]});
        }

        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            dist[i] = dijkstra(i, graph);
        }

        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long c = dist[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
                if (c == Long.MAX_VALUE) return -1;
                ans += c;
            }
        }
        return ans;
    }

    private static long[] dijkstra(int start, List<int[]>[] graph) {
        long[] dist = new long[26];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a.cost, b.cost)
        );

        pq.add(new Pair(0, start));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (cur.cost > dist[cur.node]) continue;

            for (int[] edge : graph[cur.node]) {
                int next = edge[0];
                long w = edge[1];

                if (dist[next] > cur.cost + w) {
                    dist[next] = cur.cost + w;
                    pq.add(new Pair(dist[next], next));
                }
            }
        }
        return dist;
    }

    private static class Pair {
        long cost;
        int node;

        Pair(long cost, int node) {
            this.cost = cost;
            this.node = node;
        }
    }


//    floyd warsall algo tc O(m + n) and sc O(1)

    static long minimumCostI(
            String source,
            String target,
            char[] original,
            char[] changed,
            int[] cost
    ) {
        // Initialize result to store the total minimum cost
        long totalCost = 0;

        // Initialize a 2D array to store the minimum conversion cost
        // between any two characters
        long[][] minCost = new long[26][26];
        for (long[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Fill the initial conversion costs from the given original,
        // changed, and cost arrays
        for (int i = 0; i < original.length; ++i) {
            int startChar = original[i] - 'a';
            int endChar = changed[i] - 'a';
            minCost[startChar][endChar] = Math.min(
                    minCost[startChar][endChar],
                    (long) cost[i]
            );
        }

        // Use Floyd-Warshall algorithm to find the shortest path between any
        // two characters
        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    minCost[i][j] = Math.min(
                            minCost[i][j],
                            minCost[i][k] + minCost[k][j]
                    );
                }
            }
        }

        // Calculate the total minimum cost to transform the source string to
        // the target string
        for (int i = 0; i < source.length(); ++i) {
            if (source.charAt(i) == target.charAt(i)) {
                continue;
            }
            int sourceChar = source.charAt(i) - 'a';
            int targetChar = target.charAt(i) - 'a';

            // If the transformation is not possible, return -1
            if (minCost[sourceChar][targetChar] >= Integer.MAX_VALUE) {
                return -1;
            }
            totalCost += minCost[sourceChar][targetChar];
        }

        return totalCost;
    }

}
