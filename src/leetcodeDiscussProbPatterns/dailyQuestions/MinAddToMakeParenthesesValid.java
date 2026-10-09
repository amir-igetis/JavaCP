package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinAddToMakeParenthesesValid {
    public static void main(String[] args) {
        String s = "())";
        System.out.println(minAddToMakeValid(s));
    }

    // Open Bracket Counter

    /// Here, N is the number of characters in the string s.
    ///
    /// Time complexity: O(N)
    ///
    /// We iterate over each character in the string s once. For each character, we either increment, decrement, or compare a counter. These operations take constant time. Therefore, the overall time complexity is linear, O(N).
    ///
    /// Space complexity: O(1)
    ///
    /// We use only two variables, openBrackets and minAddsRequired, to count unmatched brackets. These variables require constant space, and we do not use any extra data structures that depend on the input size. Thus, the space complexity is constant.
    static int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAddsRequired = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else {
                // If an open bracket exists, match it with the closing one
                // If not, we need to add an open bracket.
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    minAddsRequired++;
                }
            }
        }

        // Add the remaining open brackets as closing brackets would be required.
        return minAddsRequired + openBrackets;
    }
}
