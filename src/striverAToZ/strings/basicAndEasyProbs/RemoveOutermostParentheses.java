package striverAToZ.strings.basicAndEasyProbs;

public class RemoveOutermostParentheses {

    /// Question 1
    ///
    /// Remove Outermost Parentheses
    ///
    /// Problem Statement: A valid parentheses string is defined by the following rules:
    ///
    /// It is the empty string "".
    /// If A is a valid parentheses string, then so is "(" + A + ")".
    /// If A and B are valid parentheses strings, then A + B is also valid.
    ///
    /// A primitive valid parentheses string is a non-empty valid string that cannot be split into two or more non-empty valid parentheses strings.
    ///
    /// Given a valid parentheses string s, your task is to remove the outermost parentheses from every primitive component of s and return the resulting string.
    public static void main(String[] args) {
        String s = "(()())(())";  // Example input string
        String ans = removeOuterParentheses(s);

        // Print result
        System.out.println("The result is: " + ans);

    }

    /// Time Complexity: O(n), since we are performing a single traversal of the string.
    ///
    /// Space Complexity: O(1), since we are using a few variables to track the current state.
    static String removeOuterParentheses(String s) {
            // Initialize result string
            StringBuilder result = new StringBuilder();
            // Initialize nesting level counter
            int level = 0;

            // Traverse the string
            for (char ch : s.toCharArray()) {
                // If we encounter '(', increase the level
                if (ch == '(') {
                    // If we're inside a primitive, add '(' to result
                    if (level > 0) result.append(ch);
                    // Increase the nesting level for '('
                    level++;
                }
                // If we encounter ')', decrease the level
                else if (ch == ')') {
                    // Decrease the nesting level for ')'
                    level--;
                    // If we're inside a primitive, add ')' to result
                    if (level > 0) result.append(ch);
                }
            }

            // Return the result as a string after removing the outer parentheses
            return result.toString();
        }
    }

