package graphPrac;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
            {
                add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
                // here 3 is u 2 is V and 6 is weight
                add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
                add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
                add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
                add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
            }
        };

        int src = 0;

        int[] res = bellmanFord(edges, V, src);
        System.out.println(Arrays.toString(res));
    }

    static int[] bellmanFord(ArrayList<ArrayList<Integer>> adj, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> e : adj) {
                int u = e.get(0);
                int v = e.get(1);
                int w = e.get(2);
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w;
            }
        }
//        check for negative cycle
        for (ArrayList<Integer> it : adj) {
            int u = it.get(0);
            int v = it.get(1);
            int w = it.get(2);
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                return new int[]{-1};
        }
        return dist;
    }
}
