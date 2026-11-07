package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinMovesToEqualArrayElementsIII {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        System.out.println(minMoves(nums));
    }

    // greedy

    /// Let n be the length of nums.
    ///
    /// Time complexity: O(n).
    ///
    /// We traverse the array twice: once to find the maximum and once to sum the differences.
    ///
    /// Space complexity: O(1).
    ///
    /// We need only a constant number of variables to store the maximum and the answer.
    static int minMoves(int[] nums) {
        int mx = nums[0];
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int ans = 0;
        for (int x : nums) {
            ans += mx - x;
        }
        return ans;
    }
}
