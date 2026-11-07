package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinMovesToEqualArrElems {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(minMoves(nums));
    }

    /// tc O(n) sc O(1)
    static int minMoves(int[] nums) {
        int lo = Arrays.stream(nums).min().getAsInt(), moves = 0;
        for (int x : nums) moves += x - lo;
        return moves;
    }

    /// Complexity
    /// Time Complexity: O(N) where N is the size of the array. We iterate through the array exactly twice—once to find the minimum and once to calculate the total moves.
    ///
    /// Space Complexity: O(1) as we only use a few integer variables to store the minimum value and the total moves.
    static int minMovesI(int[] nums) {
        int min = Integer.MAX_VALUE;

        // Step 1: Find the minimum element in the array
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        // Step 2: Calculate the moves needed to bring every element down to the minimum
        int moves = 0;
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}
