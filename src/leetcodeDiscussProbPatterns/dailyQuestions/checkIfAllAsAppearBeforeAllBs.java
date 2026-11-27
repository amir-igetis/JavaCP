package leetcodeDiscussProbPatterns.dailyQuestions;

public class checkIfAllAsAppearBeforeAllBs {
    public static void main(String[] args) {
        String s = "aaabbb";
        System.out.println(checkString(s));
        System.out.println(checkStringI(s));
    }

    // Comparing Indices

    /// Let n be the length of s.
    ///
    /// Time complexity: O(n).
    ///
    /// This is the time required to traverse the string and maintain the indices.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few variables are used, which requires constant extra space.
    static boolean checkString(String s) {
        int n = s.length();
        int lasta = -1; // Index of the last occurrence of 'a'
        int firstb = n; // Index of the first occurrence of 'b'
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                lasta = Math.max(lasta, i);
            } else {
                firstb = Math.min(firstb, i);
            }
        }
        return lasta < firstb;

    }

    // Searching for a Substring

    /// Let n be the length of s.
    ///
    /// Time complexity: O(n).
    ///
    /// This is the time required to traverse the string and search for a substring of length 2.
    ///
    /// Space complexity: O(1).
    ///
    /// Only constant extra space is used for the substring search.
    static boolean checkStringI(String s) {
        return !s.contains("ba");
    }
}
