package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class ScoreOfParentheses {
    public static void main(String[] args) {
        String s = "()";
        System.out.println(scoreOfParentheses(s));
        System.out.println(scoreOfParenthesesI(s));
    }

    ///  Counting Cores (O(N) Time, O(1) Space) — Most OptimalIf you look closely at the rules, the final score is entirely determined by the deepest () pairs (the "cores"). Every time you find a core (), it contributes $2^{depth}$ to the total score, where depth is how many outer parentheses wrap it.This approach completely eliminates the need for extra memory.
    static int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // If we find an immediate "()", it's a core.
                // Add 2^depth to the total score.
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // 1 << depth is equivalent to Math.pow(2, depth)
                }
            }
        }

        return score;

    }

    /// Stack Approach (O(N) Time, O(N) Space)
    /// Since this problem involves nested structures, a Stack is the standard way most people solve it initially.
    ///
    /// The stack keeps track of the score at the current depth. When you see an open bracket (, you push 0 to start calculating the inner score. When you see a closing bracket ), you pop the inner score, calculate its multiplied value Math.max(2 * inner_score, 1), and add it to the score of the outer level.
    static int scoreOfParenthesesI(String s) {
        Stack<Integer> stack = new Stack<>();
        // Push 0 as the base score for the outermost layer
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Start a new inner layer with a score of 0
                stack.push(0);
            } else {
                // We closed a layer, calculate its score
                int innerScore = stack.pop();
                int outerScore = stack.pop();

                // If innerScore is 0, it means it was a direct "()", so score is 1.
                // Otherwise, it was "(A)", so score is 2 * innerScore.
                int currentScore = outerScore + Math.max(2 * innerScore, 1);

                // Add the computed score back to the current outer layer
                stack.push(currentScore);
            }
        }

        return stack.pop();
    }
}
