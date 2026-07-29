package striverAToZ.graphs.topoSortProbs;

import java.util.*;

public class CourseScheduleII {

    /// Problem Statement I (Course Schedule): There are a total of n tasks you have to pick, labeled from 0 to n-1.
    /// Some tasks may have prerequisites tasks, for example, to pick task 0 you have to first finish tasks 1,
    /// which is expressed as a pair: (0, 1)
    ///
    /// Given the total number of n tasks and a list of prerequisite pairs of size m.
    /// Find the order of tasks you should pick to finish all tasks.
    ///
    /// Note: There may be multiple correct orders, you need to return one of them.
    /// If it is impossible to finish all tasks, return an empty array.
    ///
    /// Problem Statement II (Pre-requisite Tasks): There are a total of N tasks, labeled from 0 to N-1.
    /// Some tasks may have prerequisites, for example, to do task 0 you have to first complete task 1,
    /// which is expressed as a pair: (0, 1)
    ///
    /// Given the total number of tasks N and a list of prerequisite pairs P, find if it is possible to finish all tasks.
    ///
    /// Note: These two questions are linked. The second question asks if it is possible to finish all the tasks and
    /// the first question states to return the ordering of the tasks if it is possible to perform all the tasks,
    /// otherwise return an empty array.

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] ans = findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(ans));
    }

    static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build adjacency list
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

        // Create result list
        int[] order = new int[numCourses];
        int idx = 0;

        // Process queue
        while (!q.isEmpty()) {
            int node = q.poll();
            order[idx++] = node;

            // Reduce in-degree of neighbors
            for (int nei : adj.get(node)) {
                inDegree[nei]--;
                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        // Return order if valid, else empty
        if (idx == numCourses) {
            return order;
        }
        return new int[0];
    }


}
