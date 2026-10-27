package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinSumOfSquaredDiff {
    public static void main(String[] args) {
        int[] nums1 = {1, 4, 10, 12}, nums2 = {5, 8, 6, 9};
        int k1 = 1, k2 = 1;
        System.out.println(minSumSquareDiff(nums1, nums2, k1, k2));
        System.out.println(minSumSquareDiffI(nums1, nums2, k1, k2));
    }

    // greedy

    /// Let n be the length of nums_1 and nums_2
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Computing the absolute differences takes O(n) time. Sorting the array of size n takes O(nlogn) time. The subsequent linear pass to reduce the elements takes O(n) time. Thus, the overall time complexity is dominated by sorting: O(nlogn).
    ///
    /// Space complexity: O(n) or O(logn).
    ///
    /// Extra space is required to store and sort the differences. In languages like Python and Java, an auxiliary array of size O(n) is allocated. For in-place implementations, the built-in sorting algorithm uses O(logn) to O(n) auxiliary space depending on the language.
    static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;

        long sum = 0;
        for (int i = 0; i < n; i++) {
            nums1[i] = Math.abs(nums1[i] - nums2[i]);
            sum += nums1[i];
        }
        if (sum <= k) {
            return 0;
        }

        Arrays.sort(nums1);
        int[] d = new int[n + 1];
        for (int i = 0; i < n; i++) {
            d[i] = nums1[n - 1 - i];
        }

        for (int i = 1; i <= n; i++) {
            long cost = (long) (d[i - 1] - d[i]) * i;
            if (cost > k) {
                long q = k / i,
                        r = k % i,
                        hi = d[i - 1] - q;
                long ans = hi * hi * (i - r) + (hi - 1) * (hi - 1) * r;
                for (int j = i; j < n; j++) {
                    ans += (long) d[j] * d[j];
                }
                return ans;
            }
            k -= cost;
        }
        return 0;
    }

    // binary search on answer

    /// Let n be the length of nums_1 and nums_2 and let m be the maximum absolute difference between corresponding elements of the two arrays.
    ///
    /// Time complexity: O(n(logn+logm)).
    ///
    /// Finding the maximum difference takes O(n) time. Binary search takes O(nlogm) time across O(logm) iterations. Sorting the array takes O(nlogn) time, and the final pass takes O(n) time. Thus, the overall time complexity is O(n(logn+logm)).
    ///
    /// Space complexity: O(n) or O(logn).
    ///
    /// Extra space is required for sorting, taking O(logn) to O(n) auxiliary space depending on the programming language.
    static long minSumSquareDiffI(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int k = k1 + k2;
        int maxDif = 0;
        for (int i = 0; i < n; i++) {
            nums1[i] = Math.abs(nums1[i] - nums2[i]);
            maxDif = Math.max(maxDif, nums1[i]);
        }

        int l = 0,
                r = maxDif,
                res = 0;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            long sum = 0;
            for (int num : nums1) {
                sum += num > mid ? num - mid : 0;
            }
            if (sum <= k) {
                r = mid - 1;
                res = mid;
            } else {
                l = mid + 1;
            }
        }

        for (int num : nums1) {
            if (num > res) {
                k -= num - res;
            }
        }

        Arrays.sort(nums1);
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            long diff = Math.min(nums1[i], res);
            if (k > 0 && diff > 0) {
                diff--;
                k--;
            }
            ans += diff * diff;
        }
        return ans;
    }
}
