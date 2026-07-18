package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
        System.out.println(removeCoveredIntervals(intervals));
    }

    static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });
        int count = 1;
        int maxEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] > maxEnd) {
                count++;
                maxEnd = intervals[i][1];
            }
        }
        return count;
    }
}
