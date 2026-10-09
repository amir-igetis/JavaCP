package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinInsertionToBalanceAParenthesesString {
    public static void main(String[] args) {
        String s = "))())(";
        System.out.println(minInsertions(s));
    }

    // greedy

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n).
    ///
    /// We traverse the string once.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a constant amount of extra space is needed.
    static int minInsertions(String s) {
        int insertions = 0;
        int leftCount = 0;
        int length = s.length();
        int index = 0;
        while (index < length) {
            char c = s.charAt(index);
            if (c == '(') {
                leftCount++;
                index++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    insertions++;
                }
                if (index < length - 1 && s.charAt(index + 1) == ')') {
                    index += 2;
                } else {
                    insertions++;
                    index++;
                }
            }
        }
        insertions += leftCount * 2;
        return insertions;
    }
}
