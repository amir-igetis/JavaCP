package LeetcodeInterview.arrOrStr;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
//
        String s = "LVIII";
        System.out.println(romanToInt(s));
    }

    static int romanToInt(String s) {
        if (s.isEmpty())
            return 0;

        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int firstVal = values.get(s.charAt(0));
        int secondVal = (s.length() > 1) ? values.get(s.charAt(1)) : 0;

        if (firstVal < secondVal) {
            return romanToInt(s.substring(1)) - firstVal;
        } else {
            return firstVal + romanToInt(s.substring(1));
        }
    }
}
