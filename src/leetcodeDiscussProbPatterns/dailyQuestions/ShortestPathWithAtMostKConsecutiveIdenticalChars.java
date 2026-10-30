package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathWithAtMostKConsecutiveIdenticalChars {

    public static void main(String[] args) {
        int n = 3, k = 1;
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {0, 2, 3}};
        String labels = "aab";
        System.out.println(shortestPath(n, edges, labels, k));
    }

    // Dijkstra's Algorithm

    /// Let n be the number of nodes, m be the length of edges, and k be the maximum allowed number of consecutive identical characters.
    ///
    /// Time complexity: O(nk+mklog(mk)).
    ///
    /// Building the adjacency list takes O(n+m) time. Initializing the dis array takes O(nk) time. There are at most nk distinct states, and traversing outgoing edges across all states results in at most mk transitions. Each priority queue push and pop operation takes O(log(mk)) time, leading to an overall time complexity of O(nk+mklog(mk)).
    ///
    /// Space complexity: O(nk+mk).
    ///
    /// The adjacency list requires O(n+m) space, the dis array requires O(nk) space, and the priority queue holds at most O(mk) elements. Thus, the total auxiliary space complexity is O(nk+mk)
    static int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dis = new long[n][k + 1];
        for (long[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // {shortest path length, node index, number of consecutive identical letters at the end}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) ->
                Long.compare(a[0], b[0])
        );

        dis[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int x = (int) cur[1],
                    cnt = (int) cur[2];

            if (d > dis[x][cnt]) {
                continue;
            }
            if (x == n - 1) {
                return (int) d;
            }

            for (int[] e : g[x]) {
                int y = e[0],
                        w = e[1];
                int newCnt = labels.charAt(x) == labels.charAt(y) ? cnt + 1 : 1;
                if (newCnt > k) {
                    continue;
                }

                long nd = d + w;
                if (nd < dis[y][newCnt]) {
                    dis[y][newCnt] = nd;
                    pq.offer(new long[]{nd, y, newCnt});
                }
            }
        }

        return -1;
    }
}
