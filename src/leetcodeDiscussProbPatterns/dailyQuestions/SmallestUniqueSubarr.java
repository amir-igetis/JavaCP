package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestUniqueSubarr {
    public static void main(String[] args) {
        int[] nums = {3, 3, 3};
        System.out.println(smallestUniqueSubarray(nums));
        System.out.println(smallestUniqueSubarrayI(nums));
    }

    // Binary Search on the Answer + Rolling Hash

    /// Let n be the length of nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Precomputing prefix hashes takes O(n) time. Binary search considers O(logn) lengths. Each check examines at most n subarrays, computing each hash in O(1) time and counting occurrences with a hash table. Each check therefore takes O(n) time, giving O(nlogn) overall.
    ///
    /// Space complexity: O(n).
    ///
    /// The prefix hash and base-power arrays each require O(n) space. The hash table used by each check stores at most O(n) values.
    static int smallestUniqueSubarray(int[] nums) {
        final long MOD = 1000000007L;
        final long BASE = 911382323L;

        int n = nums.length;
        long[] h = new long[n + 1];
        long[] p = new long[n + 1];
        p[0] = 1;

        // Compute prefix hashes and powers of BASE
        for (int i = 0; i < n; ++i) {
            h[i + 1] = (h[i] * BASE + nums[i]) % MOD;
            p[i + 1] = (p[i] * BASE) % MOD;
        }

        int lo = 1,
                hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(mid, n, h, p, MOD)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private static boolean check(int len, int n, long[] h, long[] p, long MOD) {
        Map<Long, Integer> cnt = new HashMap<>();

        for (int l = 0; l + len <= n; ++l) {
            int r = l + len;
            long key = (h[r] - ((h[l] * p[len]) % MOD) + MOD) % MOD;
            cnt.merge(key, 1, Integer::sum);
        }

        for (int times : cnt.values()) {
            if (times == 1) {
                return true;
            }
        }
        return false;
    }

    // Suffix Array

    /// Let n be the length of nums, and U=max(nums). The given range of nums[i] is of the same order as n, so n is also used when analyzing counting sort.
    ///
    /// Time complexity: O(nlogn) (or O(nlog
    /// 2
    ///  n) in Python).
    ///
    /// Constructing sa with doubling optimized by counting sort takes O(nlogn) time (in Python, sorting tuples with standard sort takes O(nlog
    /// 2
    ///  n)). Kasai's algorithm computes the longest common prefix array in O(n) amortized time. The overall time complexity is therefore O(nlogn).
    ///
    /// Space complexity: O(n).
    ///
    /// The suffix array sa, rank, and other auxiliary arrays each require O(n) space. The counting-sort array requires O(n+U) space, which can be approximated as O(n) in this problem.
    static int smallestUniqueSubarrayI(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] nextRank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; ++i) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        int maxRank = 0;
        for (int x : nums) {
            maxRank = Math.max(maxRank, x);
        }

        int cntSize = Math.max(n + 1, maxRank + 2);
        int[] cnt = new int[cntSize];

        for (int k = 1; k < n; k <<= 1) {
            final int kk = k;
            // Sort by the second key first
            Arrays.fill(cnt, 0);
            for (int i : sa) {
                int key = (i + kk < n ? rank[i + kk] : -1) + 1;
                ++cnt[key];
            }
            for (int i = 1; i <= maxRank + 1; ++i) cnt[i] += cnt[i - 1];
            for (int p = n - 1; p >= 0; --p) {
                int i = sa[p];
                int key = (i + kk < n ? rank[i + kk] : -1) + 1;
                temp[--cnt[key]] = i;
            }
            int[] swap = sa;
            sa = temp;
            temp = swap;

            // Then stably sort by the first key
            Arrays.fill(cnt, 0);
            for (int i : sa) {
                int key = rank[i] + 1;
                ++cnt[key];
            }
            for (int i = 1; i <= maxRank + 1; ++i) {
                cnt[i] += cnt[i - 1];
            }
            for (int p = n - 1; p >= 0; --p) {
                int i = sa[p];
                int key = rank[i] + 1;
                temp[--cnt[key]] = i;
            }
            swap = sa;
            sa = temp;
            temp = swap;

            nextRank[sa[0]] = 0;
            for (int i = 1; i < n; ++i) {
                int a = sa[i - 1],
                        b = sa[i];
                int ax = rank[a],
                        ay = a + kk < n ? rank[a + kk] : -1;
                int bx = rank[b],
                        by = b + kk < n ? rank[b + kk] : -1;
                nextRank[b] = nextRank[a] + (ax != bx || ay != by ? 1 : 0);
            }

            int[] t = rank;
            rank = nextRank;
            nextRank = t;
            maxRank = rank[sa[n - 1]];

            if (maxRank == n - 1) {
                break;
            }
        }

        // Kasai LCP
        int[] pos = new int[n];
        int[] lcp = new int[n];
        for (int i = 0; i < n; ++i) pos[sa[i]] = i;

        int h = 0;
        for (int i = 0; i < n; ++i) {
            if (pos[i] == 0) {
                h = 0;
                continue;
            }
            int j = sa[pos[i] - 1];
            while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                ++h;
            }
            lcp[pos[i]] = h;
            if (h > 0) {
                --h;
            }
        }

        int answer = n;
        for (int i = 0; i < n; ++i) {
            int next = i + 1 < n ? lcp[i + 1] : 0;
            int need = Math.max(lcp[i], next) + 1;
            if (sa[i] + need - 1 < n) {
                answer = Math.min(answer, need);
            }
        }

        return answer;
    }
}
