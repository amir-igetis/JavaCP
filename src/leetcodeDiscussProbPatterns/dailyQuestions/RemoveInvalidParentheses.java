package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        String s = "()())()";
        Solution sol = new Solution();
        SolutionI solI = new SolutionI();
        System.out.println(sol.removeInvalidParentheses(s));
        System.out.println(solI.removeInvalidParentheses(s));
        System.out.println();
    }


    // Backtracking

    /// Time Complexity : O(2^N) since in the worst case we will have only left parentheses in the expression and for every bracket we will have two options i.e.
    /// whether to remove it or consider it. Considering that the expression has N parentheses,
    /// the time complexity will be O(2^N).
    /// Space Complexity : O(N) because we are resorting to a recursive solution and for a recursive solution there is
    /// always stack space used as internal function states are saved onto a stack during recursion. The maximum depth of recursion decides the stack space used.
    /// Since we process one character at a time and the base case for the recursion is when we have processed all of the characters of the expression string,
    /// the size of the stack would be O(N). Note that we are not considering the space required to store the valid expressions. We only count the intermediate space here.
    static class Solution {

        private Set<String> validExpressions = new HashSet<String>();
        private int minimumRemoved;

        private void reset() {
            this.validExpressions.clear();
            this.minimumRemoved = Integer.MAX_VALUE;
        }

        private void recurse(
                String s,
                int index,
                int leftCount,
                int rightCount,
                StringBuilder expression,
                int removedCount) {

            // If we have reached the end of string.
            if (index == s.length()) {

                // If the current expression is valid.
                if (leftCount == rightCount) {

                    // If the current count of removed parentheses is <= the current minimum count
                    if (removedCount <= this.minimumRemoved) {

                        // Convert StringBuilder to a String. This is an expensive operation.
                        // So we only perform this when needed.
                        String possibleAnswer = expression.toString();

                        // If the current count beats the overall minimum we have till now
                        if (removedCount < this.minimumRemoved) {
                            this.validExpressions.clear();
                            this.minimumRemoved = removedCount;
                        }
                        this.validExpressions.add(possibleAnswer);
                    }
                }
            } else {

                char currentCharacter = s.charAt(index);
                int length = expression.length();

                // If the current character is neither an opening bracket nor a closing one,
                // simply recurse further by adding it to the expression StringBuilder
                if (currentCharacter != '(' && currentCharacter != ')') {
                    expression.append(currentCharacter);
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                    expression.deleteCharAt(length);
                } else {

                    // Recursion where we delete the current character and move forward
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                    expression.append(currentCharacter);

                    // If it's an opening parenthesis, consider it and recurse
                    if (currentCharacter == '(') {
                        this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                    } else if (rightCount < leftCount) {
                        // For a closing parenthesis, only recurse if right < left
                        this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                    }

                    // Undoing the append operation for other recursions.
                    expression.deleteCharAt(length);
                }
            }
        }

        public List<String> removeInvalidParentheses(String s) {

            this.reset();
            this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
            return new ArrayList(this.validExpressions);
        }
    }

    // Limited Backtracking

    /// Time Complexity : The optimization that we have performed is simply a better form of pruning. Pruning here is something that will vary from one test case to another.
    /// In the worst case, we can have something like ((((((((( and the left_rem = len(S) and in such a case we can discard all of the characters because all are misplaced.
    /// So, in the worst case we still have 2 options per parenthesis and that gives us a complexity of O(2^N).
    /// Space Complexity : The space complexity remains the same i.e. O(N) as previous solution.
    /// We have to go to a maximum recursion depth of N before hitting the base case. Note that we are not considering
    /// the space required to store the valid expressions. We only count the intermediate space here.
    static class SolutionI {

        private Set<String> validExpressions = new HashSet<String>();

        private void recurse(
                String s,
                int index,
                int leftCount,
                int rightCount,
                int leftRem,
                int rightRem,
                StringBuilder expression) {

            // If we reached the end of the string, just check if the resulting expression is
            // valid or not and also if we have removed the total number of left and right
            // parentheses that we should have removed.
            if (index == s.length()) {
                if (leftRem == 0 && rightRem == 0) {
                    this.validExpressions.add(expression.toString());
                }

            } else {
                char character = s.charAt(index);
                int length = expression.length();

                // The discard case. Note that here we have our pruning condition.
                // We don't recurse if the remaining count for that parenthesis is == 0.
                if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                    this.recurse(
                            s,
                            index + 1,
                            leftCount,
                            rightCount,
                            leftRem - (character == '(' ? 1 : 0),
                            rightRem - (character == ')' ? 1 : 0),
                            expression);
                }

                expression.append(character);

                // Simply recurse one step further if the current character is not a parenthesis.
                if (character != '(' && character != ')') {

                    this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

                } else if (character == '(') {

                    // Consider an opening bracket.
                    this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

                } else if (rightCount < leftCount) {

                    // Consider a closing bracket.
                    this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
                }

                // Delete for backtracking.
                expression.deleteCharAt(length);
            }
        }

        public List<String> removeInvalidParentheses(String s) {

            int left = 0, right = 0;

            // First, we find out the number of misplaced left and right parentheses.
            for (int i = 0; i < s.length(); i++) {

                // Simply record the left one.
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    // If we don't have a matching left, then this is a misplaced right, record it.
                    right = left == 0 ? right + 1 : right;

                    // Decrement count of left parentheses because we have found a right
                    // which CAN be a matching one for a left.
                    left = left > 0 ? left - 1 : left;
                }
            }

            this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
            return new ArrayList<String>(this.validExpressions);
        }
    }

}
