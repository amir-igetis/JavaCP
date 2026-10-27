package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfSortableInt {
    public static void main(String[] args) {
        int[] nums = {5, 8};
        System.out.println(sortableIntegers(nums));
        System.out.println(sortableIntegersI(nums));
    }

    // KMP

    /// Let n be the length of nums, and let k denote a divisor of n being checked.
    ///
    /// Time complexity: O(n(sqrt(n))).
    ///
    /// There are O(sqrt(n)) divisors of n. For each divisor k, nums is partitioned into
    /// n / k subarrays of length k. Slicing and checking whether one such subarray can be cyclically rotated into its target subarray takes O(k) time. Thus, checking one divisor takes O(
    /// (n/k .(k)) =O(n) time, and checking all divisors takes O(n(sqrt(n)) time.
    ///
    /// In addition, sorting nums takes O(nlogn) time, and finding all divisors of n takes O(sqrt(n))
    ///  ) time. Both terms are asymptotically smaller than O(n(sqrt(n)) and are therefore omitted from the final complexity.
    ///
    /// Space complexity: O(n).
    ///
    /// The target array and the KMP algorithm require O(n) space. Slicing and array concatenation in the code also require O(n) temporary space.
    static int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> fac = new ArrayList<>();

        for (int x = 1; x * x <= n; x++) {
            if (n % x == 0) {
                fac.add(x);
                if (n / x != x) {
                    fac.add(n / x);
                }
            }
        }

        int[] target = nums.clone();
        Arrays.sort(target);

        int ans = 0;

        for (int d : fac) {
            boolean allGood = true;
            for (int left = 0; left < n; left += d) {
                int right = left + d - 1;
                int[] sub1 = Arrays.copyOfRange(nums, left, right + 1);
                int[] sub2 = Arrays.copyOfRange(target, left, right + 1);

                if (!check(sub1, sub2)) {
                    allGood = false;
                    break;
                }
            }
            if (allGood) {
                ans += d;
            }
        }

        return ans;
    }

    private static boolean check(int[] nums1, int[] nums2) {
        // Check whether cyclic rotations of nums1 can produce nums2
        int[] doubled = new int[nums1.length * 2];
        System.arraycopy(nums1, 0, doubled, 0, nums1.length);
        System.arraycopy(nums1, 0, doubled, nums1.length, nums1.length);

        return kmp(doubled, nums2);
    }

    private static boolean kmp(int[] text, int[] pattern) {
        int n = text.length,
                m = pattern.length;
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            pi[i] = j;
        }

        j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[j]) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }

    // case analysis

    /// Let n be the length of nums, and let k denote a divisor of n being checked.
    ///
    /// Time complexity: O(n(sqrt(n))).
    ///
    /// There are O(sqrt(n)) divisors of n. For each divisor k, nums is partitioned into
    /// n / k subarrays of length k. Slicing and checking whether one such subarray can be cyclically rotated into its target subarray takes O(k) time. Thus, checking one divisor takes O(
    /// (n/k .(k)) =O(n) time, and checking all divisors takes O(n(sqrt(n)) time.
    ///
    /// In addition, sorting nums takes O(nlogn) time, and finding all divisors of n takes O(sqrt(n))
    ///  ) time. Both terms are asymptotically smaller than O(n(sqrt(n)) and are therefore omitted from the final complexity.
    ///
    /// Space complexity: O(n).
    ///
    /// The target array, along with slicing and array concatenation in the code, requires O(n) temporary space.
    static int sortableIntegersI(int[] nums) {
        int n = nums.length;
        List<Integer> fac = new ArrayList<>();

        for (int x = 1; x * x <= n; x++) {
            if (n % x == 0) {
                fac.add(x);
                if (n / x != x) {
                    fac.add(n / x);
                }
            }
        }

        int[] target = nums.clone();
        Arrays.sort(target);

        int ans = 0;

        for (int d : fac) {
            boolean allGood = true;
            for (int left = 0; left < n; left += d) {
                int right = left + d - 1;

                int[] sub1 = Arrays.copyOfRange(nums, left, right + 1);
                int[] sub2 = Arrays.copyOfRange(target, left, right + 1);

                if (!checkI(sub1, sub2)) {
                    allGood = false;
                    break;
                }
            }
            if (allGood) {
                ans += d;
            }
        }

        return ans;
    }

    // Check whether cyclic rotations of nums1 can produce nums2
    private static boolean checkI(int[] nums1, int[] nums2) {
        int L = nums1.length;

        for (int i = 0; i < L - 1; i++) {
            if (nums1[i] > nums1[i + 1]) {
                int[] rotated = new int[L];
                System.arraycopy(nums1, i + 1, rotated, 0, L - i - 1);
                System.arraycopy(nums1, 0, rotated, L - i - 1, i + 1);
                return Arrays.equals(rotated, nums2);
            }
        }

        return Arrays.equals(nums1, nums2);
    }

}
