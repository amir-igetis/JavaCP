package LeetcodeInterview.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MergeIntervals {
    public static void main(String[] args) {
//
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] ans = merge(intervals);
        for (int[] i : ans) {
            for (int j : i) {
                System.out.print(j + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[][] mergeII(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();

        for (int[] interval : intervals) {
            if (stack.isEmpty() || interval[0] > stack.peek()[1]) {
                stack.push(interval);
            } else {
                stack.peek()[1] = Math.max(stack.peek()[1], interval[1]);
            }
        }

        return stack.toArray(new int[stack.size()][]);
    }

    static int[][] mergeI(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();

        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curr[1]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                res.add(curr);
                curr = intervals[i];
            }
        }
        res.add(curr);
        return res.toArray(new int[res.size()][]);
    }

    static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();

        int[] currInterval = intervals[0];
        merged.add(currInterval);
        for (int[] interval : intervals) {
            int currEnd = currInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            if (nextStart <= currEnd) {
                currInterval[1] = Math.max(currEnd, nextEnd);
            } else {
                currInterval = interval;
                merged.add(currInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
