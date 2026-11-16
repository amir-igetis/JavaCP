package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class CountNonDecreasingSubarrAfterKOps {
    public static void main(String[] args) {
        int[] nums = {6, 3, 1, 2, 4, 4};
        int k = 7;
        System.out.println(countNonDecreasingSubarrays(nums, k));
    }

    // Sliding Window

    /// Let n be the length of the given array nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Breaking down the costs:
    ///
    /// Both sliding-window pointers left and right only move to the right, at most n times each, so there are O(n) window expansions and contractions in total.
    /// During the scan, the decreasing monotonic stack maintains the nearest greater element to the left and finds the first greater-or-equal element to the right for each index. Each index is pushed and popped at most once, for a total cost of O(n).
    /// Each window expansion retrieves the maximum from the priority queue to calculate the cost increase. After the window shrinks, queue-front elements outside the window are removed. Each index enters and leaves the queue at most once, so priority queue operations take O(nlogn) in total, with O(logn) per operation.
    /// Shrinking the window requires traversing cnt[left], the set of indices covered by the removed element. Each index is covered by at most one element, so the sum of the sizes of all cnt sets is at most n. This part therefore takes O(n) in total.
    /// Thus, the overall time complexity is O(nlogn).
    ///
    /// Space complexity: O(n).
    ///
    /// The array pos records the first greater-or-equal element to the right of each index, and cnt records the indices covered by each element. Their combined size is O(n). The decreasing monotonic stack and priority queue are each no larger than the window length, also requiring O(n) space.
    static long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long operations = 0;
        // Maintain the window maximum with a priority queue
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // Record the set of elements covered by this index
        List<List<Integer>> cnt = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cnt.add(new ArrayList<>());
        }
        // Index of the first greater-or-equal element to the right
        int[] pos = new int[n];
        Arrays.fill(pos, n);
        // Decreasing monotonic stack
        Deque<Integer> st = new ArrayDeque<>();

        for (int left = 0, right = 0; right < n; right++) {
            // Maintain the monotonic stack
            while (!st.isEmpty() && nums[right] >= nums[st.peek()]) {
                pos[st.peek()] = right;
                st.pop();
            }
            // The stack top is the nearest greater element to the left of nums[right]
            if (!st.isEmpty()) {
                cnt.get(st.peek()).add(right);
            }
            st.push(right);

            // The priority queue front is the window maximum
            q.offer(new int[]{nums[right], right});
            // Adding nums[right] increases the operation count
            operations += Math.max(0, q.peek()[0] - nums[right]);

            // The minimum operation count exceeds k; shrink the window
            while (operations > k) {
                // Removing nums[left] from the window decreases the operation count
                for (int i : cnt.get(left)) {
                    // Subtract the contribution
                    operations -=
                            (long) (nums[left] - nums[i]) *
                                    (Math.min(pos[i], right + 1) - i);
                }
                left++;
            }

            // Remove elements with indices less than left from the priority queue
            while (!q.isEmpty() && q.peek()[1] < left) {
                q.poll();
            }

            // There are right - left + 1 valid contiguous subarrays ending at right
            ans += right - left + 1;
        }

        return ans;
    }
}
