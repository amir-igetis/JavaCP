package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class FindTheDistValBetweenTwoArr {
    public static void main(String[] args) {
        int[] arr1 = {4, 5, 8}, arr2 = {10, 9, 1, 8};
        int d = 2;
        System.out.println(findTheDistanceValue(arr1, arr2, d));
        System.out.println(findTheDistanceValueI(arr1, arr2, d));
    }

    // Simulation

    /// Let n and m be the numbers of elements in arr1 and arr2, respectively.
    ///
    /// Time complexity: O(n×m).
    ///
    /// We iterate through each of the n elements in arr1, and for each element, compare it against all m elements in arr2. Each difference calculation and comparison takes O(1) time, yielding an overall time complexity of O(n×m).
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few variables are used to maintain the counts and iteration states, so the space complexity is O(1).
    static int findTheDistanceValueI(int[] arr1, int[] arr2, int d) {
        int cnt = 0;
        for (int x : arr1) {
            boolean ok = true;
            for (int y : arr2) {
                ok &= Math.abs(x - y) > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    // Binary Search

    /// Let n and m be the numbers of elements in arr1 and arr2, respectively.
    ///
    /// Time complexity: O((n+m)logm).
    ///
    /// Sorting arr2 takes O(mlogm) time. Performing a binary search in arr2 for every element in arr1 takes O(nlogm) time. Therefore, the asymptotic time complexity is O((n+m)logm).
    ///
    /// Space complexity: O(logm) or O(m).
    ///
    /// The space complexity depends on the sorting algorithm implementation in each language. Sorting requires O(logm) auxiliary stack space in languages such as C++, Java, and Go, whereas Timsort in Python and JavaScript requires O(m) auxiliary space.

    static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int cnt = 0;
        for (int x : arr1) {
            int p = binarySearch(arr2, x);
            boolean ok = true;
            if (p < arr2.length) {
                ok &= arr2[p] - x > d;
            }
            if (p - 1 >= 0 && p - 1 <= arr2.length) {
                ok &= x - arr2[p - 1] > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0,
                high = arr.length - 1;
        if (arr[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
