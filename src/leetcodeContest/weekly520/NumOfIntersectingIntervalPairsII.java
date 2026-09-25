package leetcodeContest.weekly520;

import java.util.Arrays;

public class NumOfIntersectingIntervalPairsII {
    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(countIntersectingIntervals(intervals));
    }

    static long countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        for (int i = 0; i < n; i++)
            starts[i] = intervals[i][0];

        Arrays.sort(starts);
        long disjointPairs = 0;
        for (int i = 0; i < n; i++) {
            int endVal = intervals[i][1];
            int idx = upperBound(starts, endVal);
            disjointPairs += (n - idx);
        }

        long totalPairs = (long) n * (n - 1) / 2;
        return totalPairs - disjointPairs;
    }

    private static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
