package leetcodeDiscussProbPatterns.dailyQuestions;

public class StoneRemovalGame {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(canAliceWin(n));
        System.out.println(canAliceWinI(n));
    }

    // Simulation

    /// Let n be the initial number of stones.
    ///
    /// Time complexity: O(1).
    ///
    /// Since n≤50 and the first 8 moves require 10+9+⋯+3=52 stones, the loop executes at most 7 times.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few extra variables are used.
    static boolean canAliceWin(int n) {
        int take = 10,
                k = 0;
        while (n >= take) {
            n -= take;
            take--;
            k++;
        }
        return k % 2 == 1;
    }

    // Math

    /// Time complexity: O(1).
    ///
    /// Only a constant number of arithmetic operations and one square root operation are needed.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few extra variables are used.
    static boolean canAliceWinI(int n) {
        int k = (int) ((21 - Math.sqrt(441 - 8 * n)) / 2);
        return k % 2 == 1;
    }
}
