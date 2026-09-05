package striverAToZ.strings.mediumStringProbs;

public class StringToIntegerATOI {

    /// Question 4
    ///
    /// Problem Statement: Implement the function myAtoi(s) which converts the given string s to a 32-bit signed integer (similar to the C/C++ atoi function).
    ///
    /// Steps to Implement: 1. First, ignore any leading whitespace characters ' ' until the first non-whitespace character is found.
    /// 2. Check the next character to determine the sign. If it’s a '-', the number should be negative. If it’s a '+', the number should be positive. If neither is found, assume the number is positive.
    /// 3. Read the digits and convert them into a number. Stop reading once a non-digit character is encountered or the end of the string is reached. Leading zeros should be ignored during conversion.
    /// 4. The result should be clamped within the 32-bit signed integer range: [-2147483648, 2147483647]. If the computed number is outside this range, return -2147483648 if the number is less than -2147483648, or return 2147483647 if the number is greater than 2147483647.
    /// 5. Finally, return the computed number after applying all the above steps.
    public static void main(String[] args) {
        String s = "   -12345";
        System.out.println(myAtoi(s)); // Output: -12345

    }

    /// Time Complexity: O(n) since each character is processed once.
    ///
    /// Space Complexity: O(n) since the recursion stack grows up to n calls.

    private static final int INT_MIN_VAL = -2147483648;
    private static final int INT_MAX_VAL = 2147483647;

    // Recursive helper
    private static int helper(String s, int i, long num, int sign) {
        // Base case: end or non-digit
        if (i >= s.length() || !Character.isDigit(s.charAt(i)))
            return (int) (sign * num);

        // Update num
        num = num * 10 + (s.charAt(i) - '0');

        // Clamp overflow
        if (sign * num <= INT_MIN_VAL) return INT_MIN_VAL;
        if (sign * num >= INT_MAX_VAL) return INT_MAX_VAL;

        // Recurse
        return helper(s, i + 1, num, sign);
    }

    static int myAtoi(String s) {
        int i = 0;

        // Skip whitespaces
        while (i < s.length() && s.charAt(i) == ' ') i++;

        // Handle sign
        int sign = 1;
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Recursive helper
        return helper(s, i, 0, sign);
    }
}