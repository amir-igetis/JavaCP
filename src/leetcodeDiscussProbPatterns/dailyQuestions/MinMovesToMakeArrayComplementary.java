package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinMovesToMakeArrayComplementary {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 3};
        int limit = 4;
        System.out.println(minMovesI(nums, limit));
    }

    //    difference tc O(n+l) & sc O(l)
    static int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            diff[2] += 2;
            diff[a + 1] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[b + limit + 1] += 1;

        }
        int minOps = n;
        int currOps = 0;
        for (int c = 2; c <= 2 * limit; c++) {
            currOps += diff[c];
            minOps = Math.min(minOps, currOps);
        }
        return minOps;
    }

    // binary search tc O(nlogn+Llogn) & sc O(n)
    static int minMovesI(int[] nums, int limit) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int[] minArr = new int[n / 2];
        int[] maxArr = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            mp.merge(a + b, 1, Integer::sum);
            minArr[i] = a;
            maxArr[i] = b;
        }
        Arrays.sort(minArr);
        Arrays.sort(maxArr);
        int minOps = n;
        for (int c = 2; c <= 2 * limit; c++) {
            int addLeft = n / 2 - lowerBound(minArr, c);
            int addRight = lowerBound(maxArr, c - limit);

            int currOps = n / 2 + addLeft + addRight - mp.getOrDefault(c, 0);
            minOps = Math.min(minOps, currOps);
        }
        return minOps;
    }

    private static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= target)
                right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
