package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        int numerator = 1, denominator = 2;
        System.out.println(fractionToDecimal(numerator, denominator));
    }

    static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";

        StringBuilder res = new StringBuilder();
        if (numerator < 0 ^ denominator < 0)
            res.append("-");

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        res.append(num / den);
        long remainder = num % den;

        if (remainder == 0)
            return res.toString();

        res.append(".");

        Map<Long, Integer> mp = new HashMap<>();
        while (remainder != 0) {
            if (mp.containsKey(remainder)) {
                res.insert(mp.get(remainder), "(");
                res.append(")");
                break;
            }
            mp.put(remainder, res.length());
            remainder *= 10;
            res.append(remainder / den);
            remainder %= den;
        }
        return res.toString();
    }
}
