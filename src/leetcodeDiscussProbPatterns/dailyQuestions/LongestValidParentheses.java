package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        String s = ")()())";
        System.out.println(longestValidParentheses(s));
        System.out.println(longestValidParenthesesI(s));
    }

    // using stack O(n) sc O(n)
    static int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                st.push(i);
            else {
                st.pop();
                if (st.isEmpty())
                    st.push(i);
                else maxLength = Math.max(maxLength, i - st.peek());
            }
        }
        return maxLength;
    }

    // two pass
    static int longestValidParenthesesI(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        // Pass 1: Left to Right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            // Whenever pairs match, update the max length
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            }
            // If closing brackets exceed opening, the sequence is permanently invalid
            else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        // Reset counters for the second pass
        left = 0;
        right = 0;

        // Pass 2: Right to Left
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            }
            // If opening brackets exceed closing scanning backward, it's invalid
            else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }
}
