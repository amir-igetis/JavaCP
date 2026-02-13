package LeetcodeInterview.graphGeneral;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
//
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };
        System.out.println(canFinishI(numCourses, prerequisites));
    }

    // tuf code

    public boolean isPossible(int V, int[][] prerequisites) {
        // Form a graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }


        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }


        List<Integer> topo = new ArrayList<Integer>();
        // o(v + e)
        while (!q.isEmpty()) {
            int node = q.peek();

            q.remove();
            topo.add(node);
            // node is in your topo sort
            // so please remove it from the indegree

            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) q.add(it);
            }
        }

        return topo.size() == V;
    }


    // topological sort (kahn's algorithm)

    static boolean canFinishI(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i  = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adjList.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int finishCourses = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            finishCourses++;

            for (int nextCourse : adjList.get(course)) {
                indegree[nextCourse]++;
                if (indegree[nextCourse] == 0)
                    q.offer(nextCourse);
            }
        }

        return finishCourses == numCourses;
    }

        // this is using dfs
    static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build the adjacency list
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjList.get(prerequisiteCourse).add(course);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(i, adjList, visited, recursionStack)) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(int course, List<List<Integer>> adjList, boolean[] visited, boolean[] recursionStack) {
        visited[course] = true;
        recursionStack[course] = true;

        for (int nextCourse : adjList.get(course)) {
            if (!visited[nextCourse]) {
                if (hasCycle(nextCourse, adjList, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack[nextCourse]) {
                return true;
            }
        }

        recursionStack[course] = false;
        return false;
    }
}
