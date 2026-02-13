package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class CountOfTrapezoidI {
    public static void main(String[] args) {
        int[][] points = {{1, 0}, {2, 0}, {3, 0}, {2, 2}, {3, 2}};
        System.out.println(countTrapezoids(points));
    }

    static int countTrapezoids(int[][] points) {
        Map<Integer, Integer> mp = new HashMap<>();
        final int MOD = 1_000_000_007;
        long sum = 0, ans = 0;
        for (int[] point : points) {
            mp.put(point[1], mp.getOrDefault(point[1], 0) + 1);

        }

        for (int pNum : mp.values()) {
            long edge = ((long) pNum * (pNum - 1)) / 2;
            ans = (ans + edge * sum) % MOD;
            sum = (sum + edge) % MOD;
        }
        return (int) ans;
    }
}
