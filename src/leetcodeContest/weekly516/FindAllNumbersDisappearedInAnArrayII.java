package leetcodeContest.weekly516;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInAnArrayII {
    public static void main(String[] args) {
        int[] nums = {3, 9, 7};
        int lower = 1;
        int upper = 12;

        List<List<Integer>> result = findDisappearedNumbers(nums, lower, upper);

        // Print the result
        System.out.print("Output: ");
        for (List<Integer> range : result) {
            System.out.print("[" + range.get(0) + "," + range.get(1) + "] ");
        }
        System.out.println();

    }

    static List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);

        // List to store missing ranges dynamically
        List<List<Integer>> missingRanges = new ArrayList<>();

        // Track the next expected number in the sequence
        int nextExpected = lower;

        // Step 2: Traverse through all numbers in the array
        for (int num : nums) {
            // Skip duplicates or numbers less than our expected value
            if (num < nextExpected) {
                continue;
            }

            // If the number matches, just move the expectation forward
            if (num == nextExpected) {
                nextExpected++;
            }
            // We found a gap between nextExpected and the current number
            else if (num > nextExpected) {
                // The gap ends just before the current number, or at upper bound
                int end = Math.min(num - 1, upper);

                // Add the gap to our list if it's within bounds
                if (nextExpected <= upper) {
                    missingRanges.add(Arrays.asList(nextExpected, end));
                }

                // Update next expectation to right after the current number
                nextExpected = num + 1;
            }

            // Optimization: Early exit if we have processed up to the upper bound
            if (nextExpected > upper) {
                break;
            }
        }

        // Step 3: Check if there is a remaining gap at the end up to the upper bound
        if (nextExpected <= upper) {
            missingRanges.add(Arrays.asList(nextExpected, upper));
        }

        // Step 4: Convert the List to a 2D array to match the required return type
        List<List<Integer>> result = new ArrayList<>();

//        for (int i = 0; i < missingRanges.size(); i++) {
//            result.get(i).get(0) = missingRanges.get(i).get(0);
//            result.get(i).get(1) = missingRanges.get(i).get(1);
//        }

        return result;
    }
}
