package striverAToZ.heaps.hardProbs;

import java.util.*;

public class MaxSumCombination {
    public static void main(String[] args) {
        int[] nums1 = {7, 3};
        int[] nums2 = {1, 6};
        int k = 2;

        List<Integer> result = maxCombinationsI(nums1, nums2, k);
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Brute force O(N*M + NLog(MN)) sc O(N * M)

    static List<Integer> maxCombinations(int[] nums1, int[] nums2, int k) {

        // List to store all possible pair sums
        List<Integer> allSums = new ArrayList<>();

        // Iterate through each element in nums1
        for (int i = 0; i < nums1.length; i++) {

            // Iterate through each element in nums2
            for (int j = 0; j < nums2.length; j++) {

                // Store the sum of the pair
                allSums.add(nums1[i] + nums2[j]);
            }
        }

        // Sort all sums in descending order
        allSums.sort(Collections.reverseOrder());

        // Return the first k sums
        return allSums.subList(0, k);
    }

//    Optimized using pq

    static List<Integer> maxCombinationsI(int[] nums1, int[] nums2, int k) {
        // Sort both arrays in descending order
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;

        // Max heap to store sums and their indices
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Set to keep track of visited index pairs
        Set<String> visited = new HashSet<>();

        // Push initial max sum combination
        maxHeap.offer(new int[]{nums1[n - 1] + nums2[n - 1], n - 1, n - 1});
        visited.add((n - 1) + "," + (n - 1));

        // Result list
        List<Integer> result = new ArrayList<>();

        // Extract top k combinations
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int sum = current[0], i = current[1], j = current[2];

            result.add(sum);

            // Check for new combination (i - 1, j)
            if (i - 1 >= 0) {
                String key1 = (i - 1) + "," + j;
                if (!visited.contains(key1)) {
                    maxHeap.offer(new int[]{nums1[i - 1] + nums2[j], i - 1, j});
                    visited.add(key1);
                }
            }

            // Check for new combination (i, j - 1)
            if (j - 1 >= 0) {
                String key2 = i + "," + (j - 1);
                if (!visited.contains(key2)) {
                    maxHeap.offer(new int[]{nums1[i] + nums2[j - 1], i, j - 1});
                    visited.add(key2);
                }
            }
        }

        return result;
    }
}

