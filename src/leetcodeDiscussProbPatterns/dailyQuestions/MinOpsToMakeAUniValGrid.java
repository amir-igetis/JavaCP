package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinOpsToMakeAUniValGrid {
    public static void main(String[] args) {
        int[][] grid = {{2, 4}, {6, 8}};
        int x = 2;
        System.out.println(minOperations(grid, x));
    }

    // sorting and median tc O(mn * log mn) sc O(mn)
    static int minOperationsII(int[][] grid, int x) {
        // Create a list to store all the numbers from the grid
        ArrayList<Integer> numsArray = new ArrayList<>();
        int result = 0;

        // Flatten the grid into numsArray
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                numsArray.add(grid[row][col]);
            }
        }

        // Sort numsArray in non-decreasing order to easily find the median
        Collections.sort(numsArray);

        int length = numsArray.size();
        // Store the median element as the final common value
        int finalCommonNumber = numsArray.get(length / 2);

        // Iterate through each number in numsArray
        for (int number : numsArray) {
            // If the remainder when divided by x is different, return -1
            if (number % x != finalCommonNumber % x) {
                return -1;
            }
            // Add the number of operations required to make the current number equal to finalCommonNumber
            result += Math.abs(finalCommonNumber - number) / x;
        }

        return result;
    }


    // prefix and suffix sum tc O(mn * log mn) sc O(mn)
    static int minOperationsI(int[][] grid, int x) {
        // Initialize an empty array to store all numbers
        ArrayList<Integer> numsArray = new ArrayList<>();
        int result = Integer.MAX_VALUE;

        // Flatten the grid into numsArray and check if all elements have the same remainder when divided by x
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // If remainder of any element doesn't match the first element, return -1
                if (grid[row][col] % x != grid[0][0] % x) return -1;
                numsArray.add(grid[row][col]);
            }
        }

        // Sort numsArray in non-decreasing order to easily calculate operations
        Collections.sort(numsArray);

        int length = numsArray.size();
        int[] prefixSum = new int[length];
        int[] suffixSum = new int[length];

        // Calculate the prefix sum up to each index (excluding the current element)
        for (int index = 1; index < length; index++) {
            prefixSum[index] = prefixSum[index - 1] + numsArray.get(index - 1);
        }

        // Calculate the suffix sum from each index (excluding the current element)
        for (int index = length - 2; index >= 0; index--) {
            suffixSum[index] = suffixSum[index + 1] + numsArray.get(index + 1);
        }

        // Iterate through numsArray to calculate the number of operations for each potential common value
        for (int index = 0; index < length; index++) {
            int leftOperations =
                    (numsArray.get(index) * index - prefixSum[index]) / x;
            int rightOperations =
                    (suffixSum[index] -
                            numsArray.get(index) * (length - index - 1)) /
                            x;
            // Update the result with the minimum operations needed
            result = Math.min(result, leftOperations + rightOperations);
        }

        return result;
    }


    // two pointers tc O(mn * log mn) sc O(mn)
    static int minOperations(int[][] grid, int x) {
        List<Integer> numsArray = new ArrayList<>();
        int result = 0;

        // Flatten the grid into numsArray and check remainder condition
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] % x != grid[0][0] % x) return -1;
                // If any element has a different remainder than the first
                // element, it is impossible to make all elements equal, so
                // return -1
                numsArray.add(grid[row][col]);
            }
        }

        Collections.sort(numsArray);

        int length = numsArray.size();
        int prefixIndex = 0;
        int suffixIndex = length - 1;

        // Move prefixIndex and suffixIndex towards the middle
        while (prefixIndex < suffixIndex) {
            // If the prefix of equal elements is shorter than the suffix
            if (prefixIndex < length - suffixIndex - 1) {
                // Calculate the number of operations required to extend the prefix
                int prefixOperations =
                        ((prefixIndex + 1) *
                                (numsArray.get(prefixIndex + 1) -
                                        numsArray.get(prefixIndex))) /
                                x;
                result += prefixOperations;
                // Move the prefix index forward
                prefixIndex++;
            } else {
                // Calculate the number of operations required to extend the suffix
                int suffixOperations =
                        ((length - suffixIndex) *
                                (numsArray.get(suffixIndex) -
                                        numsArray.get(suffixIndex - 1))) /
                                x;
                result += suffixOperations;
                // Move the suffix index backward
                suffixIndex--;
            }
        }

        return result;

    }
}
