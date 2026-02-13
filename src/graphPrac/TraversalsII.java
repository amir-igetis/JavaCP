package graphPrac;

import java.util.*;
import java.util.stream.Collectors;

public class TraversalsII {
    public static void main(String[] args) {
        int v = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        AddEdge ae = new AddEdge();
        ae.addEdge(adj, 1, 2);
        ae.addEdge(adj, 1, 4);
        ae.addEdge(adj, 2, 3);
        ae.addEdge(adj, 4, 5);
        ae.addEdge(adj, 4, 7);
        ae.addEdge(adj, 5, 6);
        ae.addEdge(adj, 6, 7);

        System.out.println("The adjacency of the list is : ");
        for (ArrayList<Integer> it : adj) {
            System.out.println(it);
        }

        System.out.println("The bfs traversal of the graph is : ");
        List<Integer> res = bfs(adj, v);
        for (Integer i : res) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("The dfs traversal of the graph is : ");
        List<Integer> resI = dfs(adj, v);
        for (Integer i : resI) {
            System.out.print(i + " ");
        }
        System.out.println();

        ArrayList<ArrayList<ArrayList<Integer>>> adjI = new ArrayList<>();
        for (int i = 0; i <= v; i++ ) {
            adjI.add(new ArrayList<>());
        }

        AddEdgeWeighted aew = new AddEdgeWeighted();
        aew.addEdgeWeight(adjI, 1, 2, 4);
        aew.addEdgeWeight(adjI, 1, 3, 5);
        aew.addEdgeWeight(adjI, 1, 4, 7);
        aew.addEdgeWeight(adjI, 2, 3, 9);
        aew.addEdgeWeight(adjI, 3, 6, 4);
        aew.addEdgeWeight(adjI, 4, 5, 2);
        aew.addEdgeWeight(adjI, 4, 7, 8);
        aew.addEdgeWeight(adjI, 5, 6, 3);
        aew.addEdgeWeight(adjI, 6, 7, 10);

        int src = 1;

        System.out.println("The Weighted graph is : ");
        for (int i = 1; i <= v; i++) {
            for (ArrayList<Integer> itI :adjI.get(i)) {
                System.out.print(itI + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("The shortest path wight is : ");
ArrayList<Integer> resII = dijkstras(adjI, v, src);
        System.out.println(resII + " ");
    }

    static List<Integer> bfs(ArrayList<ArrayList<Integer>> adj, int v) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] distance = new boolean[v + 1];
        q.add(1);
        distance[1] = true;
        while (!q.isEmpty()) {
            Integer node = q.poll();
            ans.add(node);
            for (int i = 0; i < adj.get(node).size(); i++) {
                if (!distance[adj.get(node).get(i)]) {
                    distance[adj.get(node).get(i)] = true;
                    q.add(adj.get(node).get(i));
                }
            }
//            for (int i = 0; i < adj.get(node).size(); i++) {
//                int neighbour = adj.get(node).get(i);
//                if (!distance[neighbour]) {
//                    distance[neighbour] = true;
//                    q.add(neighbour);
//                }
//            }
//            for (Integer it : adj.get(node)) {
//                if (!distance[it]) {
//                    distance[it] = true;
//                    q.add(it);
//                }
//            }
        }
        return ans;
    }

    private static void dfsHelper(ArrayList<ArrayList<Integer>> adj, int node, boolean[] distance, List<Integer> ans) {
        distance[node] = true;
        ans.add(node);
        for (int i = 0; i < adj.get(node).size(); i++) {
            if (!distance[adj.get(node).get(i)]) {
                dfsHelper(adj, adj.get(node).get(i), distance, ans);
            }
        }
    }

    static List<Integer> dfs(ArrayList<ArrayList<Integer>> adj, int v) {
        List<Integer> ans = new ArrayList<>();
        boolean[] distance = new boolean[v + 1];
        distance[1] = true;
        dfsHelper(adj, 1, distance, ans);
        return ans;
    }

    static ArrayList<Integer> dijkstras(ArrayList<ArrayList<ArrayList<Integer>>> adj, int v, int src) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[src] = 0;
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int vertex = curr[0];
            int weight = curr[1];
            for (ArrayList<Integer> it : adj.get(vertex)) {
                int edgeWeight = it.get(1);
                int adjNode = it.get(0);
                if (weight + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = weight + edgeWeight;
                    pq.add(new int[]{adjNode, dist[adjNode]});
                }
            }
        }
//        ArrayList<Integer> result = (ArrayList<Integer>) Arrays.stream(dist).boxed().collect(Collectors.toList());
//        return result;
        ArrayList<Integer> res = new ArrayList<>();
        for (int j : dist) {
            res.add(j);
        }
        return res;
    }
}
