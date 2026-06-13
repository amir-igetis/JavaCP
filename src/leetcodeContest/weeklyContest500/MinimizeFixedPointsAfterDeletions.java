package leetcodeContest.weeklyContest500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimizeFixedPointsAfterDeletions {
    public static void main(String[] args) {
//        int[] nums = {0, 2, 1};
        int[] nums = {0, 2, 1};
// output = 2
        System.out.println(maxFixedPointsI(nums));
    }

    static int maxFixedPointsI(int[] nums) {
        List<int[]> validPairs = new ArrayList<>();

        for (int j = 0; j < nums.length; j++) {
            if (j >= nums[j]) {
                validPairs.add(new int[]{nums[j], j - nums[j]});
            }
        }

        validPairs.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]); // Descending for k
            }
            return Integer.compare(a[0], b[0]);     // Ascending for x
        });

        List<Integer> tails = new ArrayList<>();
        for (int[] p : validPairs) {
            int k = p[1];
            int left = 0, right = tails.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails.get(mid) <= k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left == tails.size()) {
                tails.add(k);
            } else {
                tails.set(left, k);
            }
        }

        return tails.size();
    }

    static int maxFixedPoints(int[] nums) {
        List<Integer> diff = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= i)
                diff.add(i - nums[i]);
        }
        List<Integer> piles = new ArrayList<>();

        for (int i : diff) {
            int left = 0, right = piles.size() - 1;
            int pos = piles.size();

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (piles.get(mid) > i) {
                    pos = mid;
                    right = mid - 1;
                } else
                    left = mid + 1;
            }

            if (pos == piles.size())
                piles.add(i);
            else
                piles.set(pos, i);

        }

        return piles.size();
    }

    private static int maxFixedPointsSimpler(int[] nums) {
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= i) {
                arr.add(i - nums[i]);
            }
        }
        List<Integer> tails = new ArrayList<>();

        for (int i : arr) {
            int pos = Collections.binarySearch(tails, i);
            if (pos < 0) {
                pos = -pos - 1;
            }
            while (pos < tails.size() && tails.get(pos) <= i) {
                pos++;
            }

            if (pos == tails.size())
                tails.add(i);
            else
                tails.set(pos, i);

        }

        return tails.size();

    }
}
