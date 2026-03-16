package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;
import java.util.Set;

public class CheckIfAStrContainsAllBinaryCodesOfSizeK {
    public static void main(String[] args) {
        String s = "00110110";
        int k = 2;
        System.out.println(hasAllCodes(s, k));
    }

    static boolean hasAllCodes(String s, int k) {
        if (k > s.length())
            return false;
        Set<String> st = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++)
            st.add(s.substring(i, i + k));

//        return st.size() == Math.pow(2, k);
        return st.size() == (1 << k);
    }
}
