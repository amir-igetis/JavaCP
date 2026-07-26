package striverAToZ.graphs.mstOrDisjointSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrCol {
    public static void main(String[] args) {
        int[][] stones = {
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        };
        System.out.println(removeStones(stones));
    }

    ///  tc O(N * α(N)) sc O(N)
    static int removeStones(int[][] stones) {
        DSU dsu = new DSU();
        for (int[] stone : stones) {
            dsu.union(stone[0], stone[1] + 10001);
        }
        Set<Integer> components = new HashSet<>();
        for (int[] stone : stones)
            components.add(dsu.find(stone[0]));

        return stones.length - components.size();
    }

    private static class DSU {
        Map<Integer, Integer> parent = new HashMap<>();

        public int find(int x) {
            parent.putIfAbsent(x, x);
            if (x != parent.get(x))
                parent.put(x, find(parent.get(x)));

            return parent.get(x);
        }

        public void union(int x, int y) {
            parent.put(find(x), find(y));
        }
    }
}
