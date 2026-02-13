package striverAToZ.greedyAlgo.mediumHard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntervals {
    public static void main(String[] args) {
        int N = 3;
        int[][] intervals = {{1, 3}, {10, 15}, {20, 30}};
        int[] newEvent = {5, 6};
        System.out.println(Arrays.toString(insert(intervals, newEvent)));
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/insert-interval-1666733333/1
    // soln for https://leetcode.com/problems/insert-interval/description/
    static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        int i = 0;

        while (i < n && intervals[i][1] < newInterval[0])
            ans.add(intervals[i++]);

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            ++i;
        }

        ans.add(newInterval);

        while (i < n)
            ans.add(intervals[i++]);

        return ans.stream().toArray(int[][]::new);
    }
}
