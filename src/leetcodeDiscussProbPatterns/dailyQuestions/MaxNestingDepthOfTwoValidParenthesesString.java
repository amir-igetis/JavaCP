package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxNestingDepthOfTwoValidParenthesesString {
    public static void main(String[] args) {
        String seq = "(()())";
        System.out.println(Arrays.toString(maxDepthAfterSplit(seq)));
    }

    // Bracket matching using a stack

    /// Let n be the n of the string.
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse the input string once.
    ///
    /// Space complexity: O(1).
    ///
    /// Apart from the answer array, we only need a constant number of variables.
    static int[] maxDepthAfterSplit(String seq) {
        int d = 0;
        int n = seq.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                ++d;
                ans[i] = d % 2;
            } else {
                ans[i] = d % 2;
                --d;
            }
        }
        return ans;
    }
}