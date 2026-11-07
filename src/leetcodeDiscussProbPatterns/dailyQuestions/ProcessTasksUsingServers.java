package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ProcessTasksUsingServers {
    public static void main(String[] args) {
        int[] servers = {3, 3, 2}, tasks = {1, 2, 3, 2, 1, 2};
        System.out.println(Arrays.toString(assignTasks(servers, tasks)));
    }

    // Priority Queue

    /// Let m and n be the lengths of servers and tasks, respectively.
    ///
    /// Time complexity: O((m+n)logm) or O(m+nlogm).
    ///
    /// Putting all servers into idle takes O(mlogm) or O(m) time, depending on the API used.
    ///
    /// Iterating through the tasks takes O(n) time. Each task is assigned exactly one server. This assignment moves the server from idle to busy, and at some future time, when the task finishes, the server moves back from busy to idle. Therefore, each task incurs O(1) amortized priority queue operations. Since each priority queue operation takes O(logm) time, processing all tasks takes O(nlogm) time, leading to a total time complexity of O((m+n)logm) (or O(m+nlogm) if a linear-time heapify is supported).
    ///
    /// Space complexity: O(m).
    ///
    /// This is the space required by the priority queues busy and idle (excluding the O(n) space for the return array)
    static int[] assignTasks(int[] servers, int[] tasks) {
        // Busy servers, stored as pairs (t, idx)
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Idle servers, stored as pairs (w, idx)
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        for (int i = 0; i < servers.length; i++) {
            idle.offer(new int[]{servers[i], i});
        }

        int ts = 0;
        int[] ans = new int[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            ts = Math.max(ts, i);
            release(busy, idle, servers, ts);
            if (idle.isEmpty()) {
                ts = busy.peek()[0];
                release(busy, idle, servers, ts);
            }

            int[] server = idle.poll();
            int idx = server[1];
            ans[i] = idx;
            busy.offer(new int[]{ts + tasks[i], idx});
        }

        return ans;
    }

    // Move servers with t<=ts from priority queue busy to priority queue idle one by one
    private static void release(
            PriorityQueue<int[]> busy,
            PriorityQueue<int[]> idle,
            int[] servers,
            int ts
    ) {
        while (!busy.isEmpty() && busy.peek()[0] <= ts) {
            int[] server = busy.poll();
            int idx = server[1];
            idle.offer(new int[]{servers[idx], idx});
        }
    }
}
