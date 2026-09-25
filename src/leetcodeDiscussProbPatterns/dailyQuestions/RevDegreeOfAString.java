package leetcodeDiscussProbPatterns.dailyQuestions;

public class RevDegreeOfAString {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(reverseDegree(s));
    }

    // simulation

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n).
    ///
    /// We traverse the string once.
    ///
    /// Space complexity: O(1).
    static int reverseDegree(String s) {
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            ans += (26 - (s.charAt(i - 1) - 'a')) * i;
        }
        return ans;
    }
}
