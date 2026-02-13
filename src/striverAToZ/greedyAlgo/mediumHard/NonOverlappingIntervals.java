package striverAToZ.greedyAlgo.mediumHard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 4}, {3, 5}, {1, 2}};

        // Output result
        System.out.println("Minimum number of intervals to remove: " +
                eraseOverlapIntervals(intervals));

    }

    // brute tc  O(2N × N log N) & sc O(N)
    static int eraseOverlapIntervals(int[][] intervals) {

        // Total number of intervals
        int n = intervals.length;

        // Track max valid non-overlapping subset
        int maxValid = 0;

        // Try all subsets using bitmasking
        for (int mask = 0; mask < (1 << n); mask++) {

            // Build the subset
            List<int[]> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0)
                    subset.add(intervals[i]);
            }

            // Sort subset by start time
            subset.sort((a, b) -> Integer.compare(a[0], b[0]));

            // Check for overlapping
            boolean valid = true;
            for (int i = 1; i < subset.size(); i++) {
                if (subset.get(i)[0] < subset.get(i - 1)[1]) {
                    valid = false;
                    break;
                }
            }

            // Update max valid size
            if (valid)
                maxValid = Math.max(maxValid, subset.size());
        }

        // Return total - max valid
        return n - maxValid;
    }

    // optimal tc O(nLogN) sc O(1)
    static int eraseOverlapIntervalsI(int[][] intervals) {
        // Sort intervals by their end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // Counter for how many intervals we need to remove
        int count = 0;

        // Track end time of last non-overlapping interval
        int prevEnd = intervals[0][1];

        // Loop through remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If current interval overlaps with previous
            if (intervals[i][0] < prevEnd) {
                // Increment count to remove this interval
                count++;
            } else {
                // Update previous end time
                prevEnd = intervals[i][1];
            }
        }

        // Return total number of intervals removed
        return count;
    }
}
