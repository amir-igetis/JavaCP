package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetsI {

    /// Problem Statement: Given an array print all the sum of the subset generated from it, in the increasing order.
    public static void main(String[] args) {
        int[] arr = {5, 2, 1};
        List<Integer> result = subsetSums(arr);

        for (int sum : result) {
            System.out.print(sum + " ");
        }
        System.out.println();
    }

    /// Time Complexity: O(2n * n),We generate all possible subsets, which is 2n. For each subset, we iterate through the n elements to calculate its sum. Hence, total complexity is O(2n * n). Sorting the resulting sums takes O(2n log(2n)), which is smaller than O(2n * n) for large n, so the overall remains O(2n * n).
    ///
    /// Space Complexity: O(2n),We store all subset sums in a result array, which requires O(2n) space. Apart from this, only a few variables are used, so extra space is constant O(1).
    static List<Integer> subsetSums(int[] arr) {
        int n = arr.length;
        List<Integer> sums = new ArrayList<>();

        // Loop through all possible subsets represented by bitmasks
        for (int mask = 0; mask < (1 << n); mask++) {
            int sum = 0; // sum of current subset
            // Check each bit in the mask
            for (int i = 0; i < n; i++) {
                // If the i-th bit is set, include arr[i] in sum
                if ((mask & (1 << i)) != 0) {
                    sum += arr[i];
                }
            }
            sums.add(sum); // store sum
        }

        // Sort sums in increasing order
        Collections.sort(sums);
        return sums;
    }

    /// Time Complexity: O(2n),Each element has two choices: include or exclude, leading to 2n subsets. We directly compute sums without iterating over subsets, so complexity is O(2n). Sorting the sums adds O(2n log(2n)), making the total O(2n log(2n)).
    ///
    /// Space Complexity: O(2n),The result array holds all subset sums, requiring O(2n) space. Recursion uses an additional O(n) stack space due to function calls, so total auxiliary space is O(2n + n).
    // recursive
    static List<Integer> subsetSumsI(int[] arr) {
        List<Integer> sums = new ArrayList<>();
        findSums(0, 0, arr, sums);
        Collections.sort(sums);
        return sums;
    }

    private static void findSums(int index, int currentSum, int[] arr, List<Integer> sums) {
        if (index == arr.length) {
            sums.add(currentSum);
            return;
        }

        // Include current element
        findSums(index + 1, currentSum + arr[index], arr, sums);

        // Exclude current element
        findSums(index + 1, currentSum, arr, sums);
    }
}
