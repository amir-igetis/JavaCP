package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveOutermostParentheses {
    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParentheses(s));
        System.out.println(removeOuterParenthesesI(s));
    }

    // stack

    /// Let n be the length of the input s.
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse s once.
    ///
    /// Space complexity: O(n).
    ///
    /// The stack stores at most n/2 characters in the worst case (e.g., when all opening parentheses are nested), taking O(n) auxiliary space.
    static String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res.append(c);
            }
            if (c == '(') {
                stack.push(c);
            }
        }
        return res.toString();
    }

    // counting

    /// Let n be the length of the input s.
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse s once.
    ///
    /// Space complexity: O(1).
    ///
    /// Excluding the space required to store the output string, only a constant amount of extra space is used for variables.
    static String removeOuterParenthesesI(String s) {
        int level = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                level--;
            }
            if (level > 0) {
                res.append(c);
            }
            if (c == '(') {
                level++;
            }
        }
        return res.toString();
    }
}
