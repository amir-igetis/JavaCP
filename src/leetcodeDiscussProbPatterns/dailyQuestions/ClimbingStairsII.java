package leetcodeDiscussProbPatterns.dailyQuestions;

public class ClimbingStairsII {
    public static void main(String[] args) {
        int n = 4;
        int[] costs = {1, 2, 3, 4};
        System.out.println(climbStairs(n, costs));
    }

    // DP

    /// Let n be the length of costs.
    ///
    /// Time complexity: O(n).
    ///
    /// We iterate through the array of length n once, performing a constant number of comparisons and additions at each step.
    ///
    /// Space complexity: O(1).
    ///
    /// Only three rolling variables (a, b, and c) are used to track the dynamic programming states, requiring constant extra memory.
    static int climbStairs(int n, int[] costs) {
        int a = 0,
                b = 0,
                c = 0;
        for (int i = 0; i < n; ++i) {
            int nxt = Math.min(Math.min(a + 9, b + 4), c + 1) + costs[i];
            a = b;
            b = c;
            c = nxt;
        }
        return c;
    }
}
