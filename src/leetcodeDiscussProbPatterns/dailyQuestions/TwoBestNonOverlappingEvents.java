package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class TwoBestNonOverlappingEvents {
    public static void main(String[] args) {
        int[][] events = {{1, 3, 2}, {4, 5, 2}, {2, 4, 3}};
        System.out.println(maxTwoEventsI(events));
    }

    //    Top down DP
    static int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int[][] dp = new int[events.length][3];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return findEvents(events, 0, 0, dp);
    }

    private static int findEvents(int[][] events, int idx, int count, int[][] dp) {
        if (count == 2 || idx >= events.length)
            return 0;
        if (dp[idx][count] == -1) {
            int end = events[idx][1];
            int low = idx + 1, high = events.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (events[mid][0] > end)
                    high = mid;
                else low = mid + 1;
            }
            int include = events[idx][2] +
                    (low < events.length && events[low][0] > end
                            ? findEvents(events, low, count + 1, dp) : 0);
            int exclude = findEvents(events, idx + 1, count, dp);
            dp[idx][count] = Math.max(include, exclude);
        }
        return dp[idx][count];
    }

//    Greedy
    static int maxTwoEventsI(int[][] events) {
        List<int[]> times = new ArrayList<>();

        // Convert events into start and end times with their values
        for (int[] event : events) {
            // 1 denotes start time
            times.add(new int[]{event[0], 1, event[2]});
            // 0 denotes end time
            times.add(new int[]{event[1] + 1, 0, event[2]});
        }

        // Sort the times: first by time, then by type (end before start for same time)
        times.sort((a, b) ->
                a[0] == b[0]
                        ? Integer.compare(a[1], b[1])
                        : Integer.compare(a[0], b[0])
        );

        int ans = 0, maxValue = 0;

        // Process the sorted times
        for (int[] timeValue : times) {
            if (timeValue[1] == 1) {
                // Start time: Calculate the maximum sum
                ans = Math.max(ans, timeValue[2] + maxValue);
            } else {
                // End time: Update the maximum value seen so far
                maxValue = Math.max(maxValue, timeValue[2]);
            }
        }

        return ans;
    }

}
