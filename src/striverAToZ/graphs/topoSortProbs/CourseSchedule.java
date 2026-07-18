package striverAToZ.graphs.topoSortProbs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }

    static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        // Build in-degree array
        int[] inDegree = new int[numCourses];

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Fill adjacency and in-degree
        for (int[] pre : prerequisites) {
            int a = pre[0], b = pre[1];
            adj.get(b).add(a);
            inDegree[a]++;
        }

        // Initialize queue with zero in-degree nodes
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // Count processed nodes
        int count = 0;

        // Process queue
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;

            // Reduce in-degree of neighbors
            for (int nei : adj.get(node)) {
                inDegree[nei]--;
                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        // Return true if all nodes processed
        return count == numCourses;
    }
}
