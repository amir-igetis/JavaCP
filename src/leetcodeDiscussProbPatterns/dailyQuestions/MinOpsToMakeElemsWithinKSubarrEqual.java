package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MinOpsToMakeElemsWithinKSubarrEqual {
    public static void main(String[] args) {
        int[] nums = {5, -2, 1, 3, 7, 3, 6, 4, -1};
        int x = 3, k = 2;
        System.out.println(minOperations(nums, x, k));
        System.out.println(minOperationsI(nums, x, k));
    }

    //  Sliding Window + Two Heaps + Mathematics + Dynamic Programming

    /// Let n be the length of nums, and k be the number of subarrays to select.
    ///
    /// Time complexity: O(nlogn+nk).
    ///
    /// The sliding window performs O(n) insertions, transfers between heaps, and deletions in total. With lazy deletion, elements that have left the window but have not reached the heap top may remain in a heap. The actual heap size can therefore reach O(n) in the worst case, so each heap operation takes O(logn) time. Precomputing all window costs takes O(nlogn) time. Dynamic programming has O(nk) states, each with an O(1) transition, giving a total time complexity of O(nlogn+nk).
    ///
    /// Space complexity: O(nk).
    ///
    /// The two heaps, the lazy-deletion hash table, and the window-cost array ops each use at most O(n) space. The two-dimensional dynamic programming array dp uses O(nk) space, giving a total of O(nk)


    private static class DualHeap {

        PriorityQueue<Integer> left = new PriorityQueue<>(
                Comparator.reverseOrder()
        );
        PriorityQueue<Integer> right = new PriorityQueue<>();
        Map<Integer, Integer> delayed = new HashMap<>();
        int leftSize = 0,
                rightSize = 0;
        long sumL = 0,
                sumR = 0;

        void prune(PriorityQueue<Integer> heap) {
            while (
                    !heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0
            ) {
                int num = heap.poll();
                delayed.put(num, delayed.get(num) - 1);
            }
        }

        void balance() {
            if (leftSize > rightSize + 1) {
                int num = left.poll();
                right.offer(num);
                sumR += num;
                sumL -= num;
                ++rightSize;
                --leftSize;
                prune(left);
            } else if (rightSize > leftSize) {
                int num = right.poll();
                left.offer(num);
                sumL += num;
                sumR -= num;
                ++leftSize;
                --rightSize;
                prune(right);
            }
        }

        void add(int num) {
            if (left.isEmpty() || num <= left.peek()) {
                left.offer(num);
                ++leftSize;
                sumL += num;
            } else {
                right.offer(num);
                ++rightSize;
                sumR += num;
            }
            balance();
        }

        void remove(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (num <= left.peek()) {
                --leftSize;
                sumL -= num;
                if (num == left.peek()) {
                    prune(left);
                }
            } else {
                --rightSize;
                sumR -= num;
                if (num == right.peek()) {
                    prune(right);
                }
            }
            balance();
        }
    }

    static long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        DualHeap heaps = new DualHeap();
        long[] ops = new long[n];
        for (int i = 0; i < n; ++i) {
            heaps.add(nums[i]);
            if (i >= x) {
                heaps.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                long mid = heaps.left.peek();
                ops[i] =
                        heaps.sumR -
                                heaps.sumL +
                                mid * (heaps.leftSize - heaps.rightSize);
            }
        }

        long[][] dp = new long[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
            dp[i][0] = 0;
        }
        for (int j = 1; j <= k; ++j) {
            if (j == 1) {
                dp[x - 1][j] = ops[x - 1];
            } else {
                dp[j * x - 1][j] = dp[(j - 1) * x - 1][j - 1] + ops[j * x - 1];
            }
        }
        for (int j = 1; j <= k; ++j) {
            for (int i = j * x; i < n; ++i) {
                dp[i][j] = Math.min(dp[i - x][j - 1] + ops[i], dp[i - 1][j]);
            }
        }
        return dp[n - 1][k];
    }

    // Sliding Window + Ordered Multisets + Mathematics + Dynamic Programming (Rolling Arrays)

    /// Let n be the length of nums, x be the length of each selected subarray, and k be the number of subarrays to select.
    ///
    /// Time complexity: O(nlogx+nk).
    ///
    /// With ordered multisets implemented using balanced trees, each insertion, lookup, deletion, and transfer takes O(logx) time. The window moves O(n) times, so precomputing all window costs takes O(nlogx) time. Dynamic programming has k layers, and resetting the rolling array and computing the transitions each take O(n) time per layer. The total time complexity is O(nlogx+nk).
    ///
    /// Space complexity: O(n).
    ///
    /// The two ordered multisets store O(x) elements in total. The window-cost array ops uses O(n) space, and the rolling arrays pre and cur together use O(n) space. Since x≤n, the total space complexity is O(n)
    private static class MultiSet {

        TreeMap<Integer, Integer> counts = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int value) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
            ++size;
            sum += value;
        }

        void remove(int value) {
            int count = counts.get(value);
            if (count == 1) {
                counts.remove(value);
            } else {
                counts.put(value, count - 1);
            }
            --size;
            sum -= value;
        }

        int min() {
            return counts.firstKey();
        }

        int max() {
            return counts.lastKey();
        }
    }

    private static long[] getCosts(int[] nums, int x) {
        int n = nums.length;
        long[] ops = new long[n];
        MultiSet left = new MultiSet();
        MultiSet right = new MultiSet();

        for (int i = 0; i < n; ++i) {
            int value = nums[i];
            if (left.size == 0 || value <= left.max()) {
                left.add(value);
            } else {
                right.add(value);
            }
            if (i >= x) {
                value = nums[i - x];
                if (left.counts.containsKey(value)) {
                    left.remove(value);
                } else {
                    right.remove(value);
                }
            }
            if (left.size > right.size + 1) {
                value = left.max();
                left.remove(value);
                right.add(value);
            } else if (left.size < right.size) {
                value = right.min();
                right.remove(value);
                left.add(value);
            }
            if (i >= x - 1) {
                long mid = left.max();
                ops[i] =
                        mid * left.size - left.sum + right.sum - mid * right.size;
            }
        }
        return ops;
    }

    static long minOperationsI(int[] nums, int x, int k) {
        int n = nums.length;
        long[] ops = getCosts(nums, x);
        long[] pre = new long[n + 1];
        long[] cur = new long[n + 1];

        for (int j = 1; j <= k; ++j) {
            Arrays.fill(cur, Long.MAX_VALUE);
            for (int i = j * x; i <= n; ++i) {
                cur[i] = Math.min(cur[i - 1], pre[i - x] + ops[i - 1]);
            }
            long[] temp = pre;
            pre = cur;
            cur = temp;
        }
        return pre[n];
    }
}
