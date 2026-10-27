package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinOpsToSortAString {
    public static void main(String[] args) {
        String s = "dog";
        System.out.println(minOperations(s));
    }

    // case analysis

    /// Let n be the length of the string s.
    ///
    /// Time complexity: O(n).
    ///
    /// Checking whether s is sorted and finding the minimum and maximum characters takes O(n) time. Scanning the middle of the string takes at most O(n) time.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a constant number of auxiliary variables are used.
    static int minOperations(String s) {
        int n = s.length();
        char mx = 'a',
                mn = 'z';
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            mx = (char) Math.max(mx, s.charAt(i));
            mn = (char) Math.min(mn, s.charAt(i));
            if (i > 0 && s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
            }
        }
        if (sorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == mn || s.charAt(i) == mx) {
                return 2;
            }
        }
        return 3;

    }
}
