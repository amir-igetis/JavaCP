package striverAToZ.strings.mediumStringProbs;

import java.util.*;

public class RomanToInteger {

    /// Question 3
    ///
    /// Problem Statement: Roman numerals are represented by seven different symbols: I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
    /// For example: 2 is written as II, 12 is written as XII, 27 is written as XXVII.
    /// Roman numerals are usually written largest to smallest from left to right. But in six special cases, subtraction is used instead of addition:
    /// I before V or X → 4 and 9,
    /// X before L or C → 40 and 90,
    /// C before D or M → 400 and 900
    /// Given a Roman numeral, convert it to an integer.
    public static void main(String[] args) {
        String s = "MCMXCIV";
        int result = romanToInt(s);
        System.out.println("Integer value: " + result);
    }

    /// Time Complexity: O(n), where n is the length of the input string since we traverse the string once.
    /// Space Complexity: O(1), since we use a fixed-size map for Roman numerals.
    static int romanToInt(String s) {
        int res = 0;

        // Create a map of Roman numerals to integers
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        // Loop through the string, except the last character
        for (int i = 0; i < s.length() - 1; i++) {
            // Subtract if current value is less than next value
            if (roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                res -= roman.get(s.charAt(i));
            } else {
                // Otherwise, add the value
                res += roman.get(s.charAt(i));
            }
        }

        // Add the value of the last character
        return res + roman.get(s.charAt(s.length() - 1));
    }
}
