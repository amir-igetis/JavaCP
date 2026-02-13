package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.PriorityQueue;

public class MinPairRemovalToSortArrII {
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        System.out.println(minimumPairRemoval(nums));
    }

    //    priority queue + lazy deletion
    static int minimumPairRemoval(int[] nums) {
        PriorityQueue<PQItem> pq = new PriorityQueue<>();
        boolean[] merged = new boolean[nums.length];

        int decreaseCount = 0;
        int count = 0;
        Node head = new Node(nums[0], 0);
        Node current = head;

        for (int i = 1; i < nums.length; i++) {
            Node newNode = new Node(nums[i], i);
            current.next = newNode;
            newNode.prev = current;
            pq.offer(
                    new PQItem(current, newNode, current.value + newNode.value)
            );
            if (nums[i - 1] > nums[i]) {
                decreaseCount++;
            }
            current = newNode;
        }

        while (decreaseCount > 0) {
            PQItem item = pq.poll();
            Node first = item.first;
            Node second = item.second;
            long cost = item.cost;

            if (
                    merged[first.left] ||
                            merged[second.left] ||
                            first.value + second.value != cost
            ) {
                continue;
            }
            count++;
            if (first.value > second.value) {
                decreaseCount--;
            }

            Node prevNode = first.prev;
            Node nextNode = second.next;
            first.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = first;
            }

            if (prevNode != null) {
                if (prevNode.value > first.value && prevNode.value <= cost) {
                    decreaseCount--;
                } else if (
                        prevNode.value <= first.value && prevNode.value > cost
                ) {
                    decreaseCount++;
                }

                pq.offer(new PQItem(prevNode, first, prevNode.value + cost));
            }

            if (nextNode != null) {
                if (second.value > nextNode.value && cost <= nextNode.value) {
                    decreaseCount--;
                } else if (
                        second.value <= nextNode.value && cost > nextNode.value
                ) {
                    decreaseCount++;
                }

                pq.offer(new PQItem(first, nextNode, cost + nextNode.value));
            }

            first.value = cost;
            merged[second.left] = true;
        }

        return count;
    }

    private static class Node {

        long value;
        int left;
        Node prev;
        Node next;

        Node(int value, int left) {
            this.value = value;
            this.left = left;
        }
    }

    private static class PQItem implements Comparable<PQItem> {

        Node first;
        Node second;
        long cost;

        PQItem(Node first, Node second, long cost) {
            this.first = first;
            this.second = second;
            this.cost = cost;
        }

        @Override
        public int compareTo(PQItem other) {
            if (this.cost == other.cost) {
                return this.first.left - other.first.left;
            }
            return this.cost < other.cost ? -1 : 1;
        }
    }

    // another solution
    private static class NodeI implements Comparable<NodeI> {
        long sum;
        int leftIndex;

        NodeI(long sum, int leftIndex) {
            this.sum = sum;
            this.leftIndex = leftIndex;
        }

        @Override
        public int compareTo(NodeI other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            // Tie-break: choose the leftmost index as per problem rules
            return Integer.compare(this.leftIndex, other.leftIndex);
        }
    }

    static int minimumPairRemovalI(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];

        boolean[] removed = new boolean[n];
        PriorityQueue<NodeI> pq = new PriorityQueue<>();

        int sorted = 0;
        for (int i = 1; i < n; i++) {
            pq.offer(new NodeI(arr[i - 1] + arr[i], i - 1));
            if (arr[i] >= arr[i - 1]) sorted++;
        }

        if (sorted == n - 1) return 0;

        int remaining = n;
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        while (remaining > 1 && !pq.isEmpty()) {
            NodeI top = pq.poll();
            long sum = top.sum;
            int left = top.leftIndex;

            int right = next[left];

            // Validity check: skip if nodes were removed or the sum is outdated
            if (right >= n || removed[left] || removed[right] || (arr[left] + arr[right]) != sum) {
                continue;
            }

            int pre = prev[left];
            int nxt = next[right];

            // Subtract from sorted count before the merge
            if (arr[left] <= arr[right]) sorted--;
            if (pre != -1 && arr[pre] <= arr[left]) sorted--;
            if (nxt != n && arr[right] <= arr[nxt]) sorted--;

            // Merge: update value and mark right node as removed
            arr[left] += arr[right];
            removed[right] = true;
            remaining--;

            // Update Linked List structure
            if (pre != -1) {
                pq.offer(new NodeI(arr[pre] + arr[left], pre));
                if (arr[pre] <= arr[left]) sorted++;
            } else {
                prev[left] = -1;
            }

            if (nxt != n) {
                prev[nxt] = left;
                next[left] = nxt;
                pq.offer(new NodeI(arr[left] + arr[nxt], left));
                if (arr[left] <= arr[nxt]) sorted++;
            } else {
                next[left] = n;
            }

            // Return operations performed if array becomes non-decreasing
            if (sorted == remaining - 1) {
                return n - remaining;
            }
        }

        return n;
    }

    // 132 ms solution
    static int minimumPairRemovalII(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        long[] val = new long[n];
        int[] nxt = new int[n];
        int[] pre = new int[n];
        boolean[] removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            nxt[i] = i + 1;
            pre[i] = i - 1;
        }


        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        int inversions = 0;
        for (int i = 0; i < n - 1; i++) {
            if (val[i] > val[i + 1]) inversions++;
            pq.offer(new long[]{val[i] + val[i + 1], i});
        }

        int operations = 0;

        while (inversions > 0 && !pq.isEmpty()) {
            long[] current = pq.poll();
            int i = (int) current[1];
            int j = nxt[i];

            if (j >= n || removed[i] || removed[j] || (val[i] + val[j] != current[0])) {
                continue;
            }


            if (val[i] > val[j]) inversions--;

            int p = pre[i];
            int q = nxt[j];

            if (p != -1 && val[p] > val[i]) inversions--;
            if (q != n && val[j] > val[q]) inversions--;

            val[i] += val[j];
            removed[j] = true;

            nxt[i] = q;
            if (q != n) pre[q] = i;

            if (p != -1 && val[p] > val[i]) inversions++;
            if (q != n && val[i] > val[q]) inversions++;

            if (p != -1) pq.offer(new long[]{val[p] + val[i], p});
            if (q != n) pq.offer(new long[]{val[i] + val[q], i});

            operations++;
        }

        return operations;
    }
}
