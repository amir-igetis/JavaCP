package leetcodeContest.biWeekly185;

import java.util.ArrayList;
import java.util.List;

public class FinishTimeOfTasksI {
    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}};
        int[] baseTime = {9, 5, 3};
        System.out.println(finishTime(n, edges, baseTime));
    }

    static long finishTime(int n, int[][] edges, int[] baseTime) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());


        for (int[] i : edges) {
            int u = i[0];
            int v = i[1];
            adjList.get(u).add(v);
        }

        return dfs(0, adjList, baseTime);

    }

    private static long dfs(int node, List<List<Integer>> adjList, int[] baseTime) {
        if (adjList.get(node).isEmpty())
            return baseTime[node];

        long early = Integer.MAX_VALUE;
        long late = Integer.MIN_VALUE;

        for (int i : adjList.get(node)) {
            long childTime = dfs(i, adjList, baseTime);
            early = Math.min(early, childTime);
            late = Math.max(late, childTime);
        }
        long dura = (late - early) + baseTime[node];
        return late + dura;
    }
}
