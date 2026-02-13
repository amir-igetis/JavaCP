package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class PowerGridMaintenance {
    public static void main(String[] args) {
        int c = 5;
        int[][] connections = {{1, 2}, {2, 3}, {3, 4}, {4, 5}}, queries = {{1, 3}, {2, 1}, {1, 1}, {2, 2}, {1, 2}};

        System.out.println(Arrays.toString(processQueries(c, connections, queries)));

    }

    static int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1];
        for (int i = 1; i <= c; i++)
            parent[i] = i;


        return new int[]{1, 1};
    }
}
