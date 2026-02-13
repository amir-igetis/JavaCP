package leetcodeContest;

public class PathExistenceQueriesInAGraph {
    public static void main(String[] args) {

//        PS
        /*
        You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.

        You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.

                An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).

        You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a path between nodes ui and vi.

                Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query and false otherwise.
            */
        int n = 2;
        int[] nums = {1, 3};
        int maxDiff = 1;
        int[][] queries = {{0, 0}, {0, 1}};
        boolean[] ans = pathExistenceQueries(n, nums, maxDiff, queries);
        for (boolean i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
// 1) Build component IDs over the sorted 'nums' array
        int[] comp = new int[n];
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            // If adjacent difference ≤ maxDiff, same component; else start a new one
            if (nums[i] - nums[i - 1] <= maxDiff) {
                comp[i] = comp[i - 1];
            } else {
                comp[i] = comp[i - 1] + 1;
            }
        }

        // 2) Answer each query by comparing component IDs
        boolean[] answer = new boolean[queries.length];
        for (int k = 0; k < queries.length; k++) {
            int u = queries[k][0], v = queries[k][1];
            answer[k] = (comp[u] == comp[v]);
        }
        return answer;
    }
}
