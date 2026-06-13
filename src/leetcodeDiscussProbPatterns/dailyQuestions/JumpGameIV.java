package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class JumpGameIV {
    public static void main(String[] args) {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println(minJumpsI(arr));
    }

    // BFS tc and sc O(n)
    static int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);

        List<Integer> curr = new LinkedList<>();
        curr.add(0);
        Set<Integer> vis = new HashSet<>();
        int step = 0;
        while (!curr.isEmpty()) {
            List<Integer> nex = new LinkedList<>();
            for (int node : curr) {
                if (node == n - 1) {
                    return step;
                }

                for (int child : graph.get(arr[node])) {
                    if (!vis.contains(child)) {
                        vis.add(child);
                        nex.add(child);
                    }
                }
                graph.get(arr[node]).clear();
                if (node + 1 < n && !vis.contains(node + 1)) {
                    vis.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !vis.contains(node - 1)) {
                    vis.add(node - 1);
                    nex.add(node - 1);
                }
            }
            curr = nex;
            step++;
        }
        return -1;
    }

    // bidirectional BFS tc & sc O(n)
    static int minJumpsI(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        HashSet<Integer> curs = new HashSet<>(); // store layers from start
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(n - 1);
        int step = 0;

        HashSet<Integer> other = new HashSet<>(); // store layers from end
        other.add(n - 1);

        // when current layer exists
        while (!curs.isEmpty()) {
            // search from the side with fewer nodes
            if (curs.size() > other.size()) {
                HashSet<Integer> tmp = curs;
                curs = other;
                other = tmp;
            }

            HashSet<Integer> nex = new HashSet<>();

            // iterate the layer
            for (int node : curs) {

                // check same value
                for (int child : graph.get(arr[node])) {
                    if (other.contains(child)) {
                        return step + 1;
                    }
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }

                // clear the list to prevent redundant search
                graph.get(arr[node]).clear();

                // check neighbors
                if (other.contains(node + 1) || other.contains(node - 1)) {
                    return step + 1;
                }

                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }

            curs = nex;
            step++;
        }

        return -1;
    }
}
