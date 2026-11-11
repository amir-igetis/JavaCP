package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class FindServersThatHandledMostNumOfReq {
    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 5}, load = {5, 2, 3, 3, 3};
        List<Integer> ans = busiestServers(k, arrival, load);
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /// Time: $O(N \log K)$ where $N$ is the number of requests. Each request takes $O(\log K)$ to update the TreeSet and PriorityQueue.Space: $O(K)$ to store the servers in the structures and track request counts.
    static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestsHandled = new int[k];

        // TreeSet stores available server IDs for fast O(log k) lookups
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        // PriorityQueue stores int[] {freeTime, serverId}
        // Ordered by freeTime ascending
        PriorityQueue<int[]> busyServers = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int loadTime = load[i];

            // Free up servers that have finished their tasks by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                int[] freedServer = busyServers.poll();
                availableServers.add(freedServer[1]);
            }

            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }

            // Find the assigned server using wrap-around logic
            int targetServer = i % k;
            Integer assignedServer = availableServers.ceiling(targetServer);

            // Wrap around to the beginning if no server is available at or after targetServer
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }

            // Mark the assigned server as busy
            availableServers.remove(assignedServer);
            busyServers.offer(new int[]{arrivalTime + loadTime, assignedServer});

            // Update counts and track the maximum
            requestsHandled[assignedServer]++;
            maxRequests = Math.max(maxRequests, requestsHandled[assignedServer]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestsHandled[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }


    // Striver's standard template style for Segment Trees
    private static class SGTree {
        int[] seg;

        public SGTree(int n) {
            // Standard 4*n allocation for Segment Tree
            seg = new int[4 * n + 1];
        }

        // Point update: updates the release time of a specific server
        public void update(int ind, int low, int high, int i, int val) {
            if (low == high) {
                seg[ind] = val;
                return;
            }
            int mid = (low + high) >> 1;
            if (i <= mid) {
                update(2 * ind + 1, low, mid, i, val);
            } else {
                update(2 * ind + 2, mid + 1, high, i, val);
            }
            // Store the minimum available time in the range
            seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
        }

        // Binary search on Segment Tree to find the earliest server ID available <= val
        public int query(int ind, int low, int high, int l, int r, int val) {
            // Out of range or no server in this segment is free by 'val' (arrival time)
            if (r < low || high < l || seg[ind] > val) {
                return -1;
            }

            // Reached the exact server (leaf node)
            if (low == high) {
                return low;
            }

            int mid = (low + high) >> 1;

            // Always try to traverse the left child first to guarantee the smallest index
            int left = query(2 * ind + 1, low, mid, l, r, val);
            if (left != -1) {
                return left;
            }

            // If no server was found on the left, search the right child
            return query(2 * ind + 2, mid + 1, high, l, r, val);
        }
    }

    /// Time: $O(N \log K)$. Querying and updating the segment tree takes $O(\log K)$ per request.Space: $O(K)$ for the segment tree array.Java
    static List<Integer> busiestServersI(int k, int[] arrival, int[] load) {
        SGTree sg = new SGTree(k);
        int[] requests = new int[k];
        int maxReq = 0;

        for (int i = 0; i < arrival.length; i++) {
            int arr = arrival[i];
            int time = load[i];
            int target = i % k;

            // Query 1: Try to find a server in the range [target, k - 1]
            int serverId = sg.query(0, 0, k - 1, target, k - 1, arr);

            // Query 2: Wrap around and search the range [0, target - 1]
            if (serverId == -1 && target > 0) {
                serverId = sg.query(0, 0, k - 1, 0, target - 1, arr);
            }

            // If a server is found, process the load and update the Segment Tree
            if (serverId != -1) {
                requests[serverId]++;
                maxReq = Math.max(maxReq, requests[serverId]);

                // Update the server's next available time
                sg.update(0, 0, k - 1, serverId, arr + time);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxReq) {
                res.add(i);
            }
        }

        return res;
    }
}
