package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class MaxNestingDepthOfTheParentheses {
    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxDepth(s));
        System.out.println(maxDepthI(s));
    }

    // Stack

    /// Here, N is the number of characters in the string s.
    ///
    /// Time complexity: O(N)
    ///
    /// We are iterating over each character in the string s, and hence the time complexity will be equal to O(N).
    ///
    /// Space complexity: O(N)
    ///
    /// The size of the stack can grow up to n/2
    ///   for strings like (((()))), and hence the space complexity of this approach will be O(N).
    static int maxDepth(String s) {
        int ans = 0;

        Stack<Character> st = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                st.pop();
            }

            ans = Math.max(ans, st.size());
        }

        return ans;
    }

    // Counter Variable

    /// Complexity Analysis
    /// Here, N is the number of characters in the string s.
    ///
    /// Time complexity: O(N)
    ///
    /// We are iterating over each character in the string s, and hence the time complexity will be equal to O(N).
    ///
    /// Space complexity: O(1)
    ///
    /// The only variables we require are openBrackets and ans. Hence, the space complexity is constant.
    static int maxDepthI(String s) {
        int ans = 0;
        int openBrackets = 0;

        for (Character c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else if (c == ')') {
                openBrackets--;
            }

            ans = Math.max(ans, openBrackets);
        }

        return ans;
    }
}

