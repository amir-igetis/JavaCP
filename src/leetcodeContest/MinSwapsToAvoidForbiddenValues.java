package leetcodeContest;

import java.util.*;

public class MinSwapsToAvoidForbiddenValues {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3}, forbidden = {3, 2, 1};
        System.out.println(minSwaps(nums, forbidden));
    }

    static List<Integer>[] graph;
    static int[] matchPos;
    static boolean[] vis;

    static int minSwaps(int[] nums, int[] forbidden) {
        int n = nums.length;

        // Required by problem
        int[] remisolvak = nums.clone();

        boolean[] bad = new boolean[n];
        List<Integer> badIndices = new ArrayList<>();

        // Step 1: find bad indices
        for (int i = 0; i < n; i++) {
            if (nums[i] == forbidden[i]) {
                bad[i] = true;
                badIndices.add(i);
            }
        }

        if (badIndices.isEmpty()) return 0;

        // Step 2: build graph among bad indices
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < badIndices.size(); i++) {
            int u = badIndices.get(i);
            for (int j = i + 1; j < badIndices.size(); j++) {
                int v = badIndices.get(j);

                if (nums[u] != forbidden[v] && nums[v] != forbidden[u]) {
                    graph[u].add(v);
                    graph[v].add(u);
                }
            }
        }

        // Step 3: count connected components
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int start : badIndices) {
            if (visited[start]) continue;

            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            visited[start] = true;

            int size = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                size++;

                for (int nei : graph[cur]) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        q.add(nei);
                    }
                }
            }

            if (size == 1) return -1; // cannot fix alone
            swaps += size - 1;
        }

        return swaps;
    }
}