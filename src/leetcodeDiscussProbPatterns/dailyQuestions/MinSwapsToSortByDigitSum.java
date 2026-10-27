package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinSwapsToSortByDigitSum {
    public static void main(String[] args) {
        int[] nums = {37, 100};
        System.out.println(minSwaps(nums));
        System.out.println(minSwapsI(nums));
    }

    // permutation cycle

    /// Let n be the length of the array nums, and M be the maximum value in the array.
    ///
    /// Time complexity: O(nlogn+nlogM).
    ///
    /// Calculating the digit sums takes O(nlogM), sorting takes O(nlogn), and traversing the permutation cycles takes O(n). The overall time complexity is O(nlogn+nlogM).
    ///
    /// Space complexity: O(n).
    ///
    /// We need four auxiliary arrays, digitSum, indices, targetPos, and visited, each taking O(n) space.
    static int minSwaps(int[] nums) {
        int n = nums.length;
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i],
                    sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                return Integer.compare(digitSum[a], digitSum[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        int[] targetPos = new int[n];
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = targetPos[j];
                }
            }
        }

        return n - cycles;
    }

    // union-find

    /// Let n be the length of the array nums, and M be the maximum value in the array.
    ///
    /// Time complexity: O(nlogn+nlogM).
    ///
    /// Calculating the digit sums takes O(nlogM), and sorting takes O(nlogn). The union-find operations use only path compression, without union by rank, and take O(nlogn) amortized time. The overall time complexity is O(nlogn+nlogM).
    ///
    /// Space complexity: O(n).
    ///
    /// We need four auxiliary arrays, digitSum, indices, targetPos, and parent, each taking O(n) space.
    static int minSwapsI(int[] nums) {
        int n = nums.length;
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i],
                    sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                return Integer.compare(digitSum[a], digitSum[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        int[] targetPos = new int[n];
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int components = n;

        for (int i = 0; i < n; i++) {
            int ri = find(parent, i);
            int rj = find(parent, targetPos[i]);
            if (ri != rj) {
                parent[ri] = rj;
                components--;
            }
        }

        return n - components;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
