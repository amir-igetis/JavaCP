package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
    public static void main(String[] args) {
        String s = "11011000";
        System.out.println(makeLargestSpecial(s));
    }

    static String makeLargestSpecial(String s) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '1')
                count++;
            else count--;
            if (count == 0) {
                res.add('1' + makeLargestSpecial(s.substring(i + 1, j)) + '0');
                i = j + 1;
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }
}
