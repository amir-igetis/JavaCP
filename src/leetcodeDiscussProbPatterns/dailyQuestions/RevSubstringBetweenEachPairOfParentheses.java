package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class RevSubstringBetweenEachPairOfParentheses {
    public static void main(String[] args) {
        String s = "(abcd)";
        System.out.println(reverseParentheses(s));
        System.out.println(reverseParenthesesI(s));
    }

    // Straightforward Way

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n^2)
    ///
    /// The algorithm iterates through each character of the input string once. For each character, we have three cases:
    ///
    /// If it's (, we push its index or the starting position where the reversal takes place to the stack. This is O(1).
    /// If it's ), we pop from the stack and reverse a portion of the result string. Popping is O(1).
    /// The reverse operation can take up to O(n) time in the worst case (when we reverse the entire string).
    /// For other characters, we append to the result string, which is typically O(1) (amortized).
    /// The worst-case scenario occurs when we have to reverse large portions of the string multiple times. In the worst case, we might end up reversing the entire string for each closing parenthesis. Therefore, the overall time complexity is O(n^2) in the worst case.
    ///
    /// Space complexity: O(n)
    ///
    /// The algorithm uses a stack to store the indices of opening parentheses. In the worst case (when all characters are opening parentheses), this could take O(n) space. The reverse function typically doesn't use extra space proportional to the input size. Therefore, the overall space complexity is O(n).
    static String reverseParentheses(String s) {
        Stack<Integer> openParenthesesIndices = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char currentChar : s.toCharArray()) {
            if (currentChar == '(') {
                // Store the current length as the start index for future reversal
                openParenthesesIndices.push(result.length());
            } else if (currentChar == ')') {
                int start = openParenthesesIndices.pop();
                // Reverse the substring between the matching parentheses
                reverse(result, start, result.length() - 1);
            } else {
                // Append non-parenthesis characters to the processed string
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    private static void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }

    // Wormhole Teleportation technique

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n)
    ///
    /// We iterate through the string once to pair up parentheses using a stack. Each character is processed once, resulting in O(n) time complexity.
    ///
    /// After pairing, we iterate through the string again to construct the final result string. During this pass, each character is processed once, and we navigate through pairs in constant time. This results in another O(n) time complexity.
    ///
    /// Converting a StringBuilder to a String in Java using toString() takes O(n) time, where n is the length of the StringBuilder. Joining elements of a list into a string in Python using ''.join() also takes O(n) time. Combined, the total time complexity is O(n).
    ///
    /// Space complexity: O(n)
    ///
    /// We use a stack to track indices of opening parentheses. In the worst case, the stack may hold up to O(n/2) elements (when all are opening parentheses), resulting in O(n) space complexity. An array pair of size n is used to store indices of matching parentheses. This contributes O(n) space complexity.
    ///
    /// Converting a StringBuilder to a String in Java generally does not increase space complexity beyond the size of the resulting string itself. However, StringBuilder internally manages a character array whose size might be slightly larger than the resulting string due to its capacity management strategy. The additional space complexity for ''.join() in Python is O(n), accounting for the space needed to store the new string object.
    ///
    /// Therefore, the total space complexity is O(n).
    static String reverseParenthesesI(String s) {
        int n = s.length();
        Stack<Integer> openParenthesesIndices = new Stack<>();
        int[] pair = new int[n];

        // First pass: Pair up parentheses
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                openParenthesesIndices.push(i);
            }
            if (s.charAt(i) == ')') {
                int j = openParenthesesIndices.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        // Second pass: Build the result string
        StringBuilder result = new StringBuilder();
        for (
                int currIndex = 0, direction = 1;
                currIndex < n;
                currIndex += direction
        ) {
            if (s.charAt(currIndex) == '(' || s.charAt(currIndex) == ')') {
                currIndex = pair[currIndex];
                direction = -direction;
            } else {
                result.append(s.charAt(currIndex));
            }
        }

        return result.toString();
    }
}
