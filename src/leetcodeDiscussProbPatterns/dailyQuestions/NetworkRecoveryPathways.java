package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class NetworkRecoveryPathways {
    public static void main(String[] args) {
        int[][] edges = {{0, 1, 5}, {1, 3, 10}, {0, 2, 3}, {2, 3, 4}};
        boolean[] online = {true, true, true, true};
        int k = 10;
        System.out.println(findMaxPathScoreII
                (edges, online, k));
    }

    //    Binary search + dijkstra tc O((E+V)logVlogU) + scO(v + e)
    static int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++)
            g.add(new ArrayList<>());

        int l = Integer.MAX_VALUE;
        int r = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (!online[u] || !online[v])
                continue;

            g.get(u).add(new int[]{v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        if (!check(g, l, k, n)) {
            return -1;
        }

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (check(g, mid, k, n)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }


    private static boolean check(List<List<int[]>> g, int mid, long k, int n) {
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) ->
                Long.compare(a[0], b[0])
        );

        dis[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0];
            int u = (int) top[1];

            if (d > k) {
                return false;
            }
            if (u == n - 1) {
                return true;
            }
            if (d > dis[u]) {
                continue;
            }

            for (int[] edge : g.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (w < mid) {
                    continue;
                }
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    pq.offer(new long[]{dis[v], v});
                }
            }
        }
        return false;
    }

//    Binary search + memoization tc O((V+E)logU) & sc O(V+E).

    private static List<int[]>[] g;
    private static long[] memo;
    private static int n;

    public static int findMaxPathScoreI(int[][] edges, boolean[] online, long k) {
        n = online.length;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        int l = Integer.MAX_VALUE;
        int r = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (!online[u] || !online[v]) {
                continue;
            }
            g[u].add(new int[]{v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        if (!checkI(l, k)) {
            return -1;
        }

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (checkI(mid, k)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private static boolean checkI(int mid, long k) {
        memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(0, mid) <= k;
    }

    private static long dfs(int u, int mid) {
        if (u == n - 1) {
            return 0;
        }
        if (memo[u] != -1) {
            return memo[u];
        }

        long res = Long.MAX_VALUE / 2;
        for (int[] edge : g[u]) {
            int v = edge[0];
            int w = edge[1];
            if (w >= mid) {
                res = Math.min(res, dfs(v, mid) + w);
            }
        }
        memo[u] = res;
        return res;
    }


//    Binary Answer + Topological Sorting + Dynamic Programming tc O((E+V)logU) scO(v + e)

    static int findMaxPathScoreII(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] g = new ArrayList[n];
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        int l = Integer.MAX_VALUE,
                r = 0;
        for (int[] edge : edges) {
            int u = edge[0],
                    v = edge[1],
                    w = edge[2];
            if (!online[u] || !online[v]) {
                continue;
            }
            g[u].add(new int[]{v, w});
            deg[v]++;
            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        // Delete unreachable nodes
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            if (deg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] edge : g[u]) {
                int v = edge[0];
                if (--deg[v] == 0 && v != 0) {
                    q.offer(v);
                }
            }
        }

        if (!checkII(l, k, g, deg, n)) {
            return -1;
        }

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (checkII(mid, k, g, deg, n)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private static boolean checkII(int mid, long k, List<int[]>[] g, int[] deg, int n) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        int[] cdeg = deg.clone();
        dp[0] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == n - 1) {
                return dp[u] <= k;
            }

            for (int[] edge : g[u]) {
                int v = edge[0],
                        w = edge[1];
                if (w >= mid) {
                    dp[v] = Math.min(dp[v], dp[u] + w);
                }
                if (--cdeg[v] == 0) {
                    q.offer(v);
                }
            }
        }
        return false;
    }


}
