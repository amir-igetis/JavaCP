package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class CountTheNumOfSubstrWithDominantOnes {
    public static void main(String[] args) {
        String s = "00011";
        System.out.println(numberOfSubstrings(s));
    }

    static int numberOfSubstrings(String s) {
        int n = s.length();
        List<Integer> zero = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (s.charAt(i) == '0')
                zero.add(i);

        int ones = n - zero.size();
        zero.add(n);

        long res = 0;
        int sid = 0;

        for (int left = 0; left < n; left++) {
            for (int id = sid; id < zero.size() - 1; id++) {
                int cnt0 = id - sid + 1;
                if (cnt0 * cnt0 > ones) break;

                int p = zero.get(id);
                int q = zero.get(id + 1);

                int cnt1 = zero.get(id) - left - (id - sid);

                if (cnt1 >= cnt0 * cnt0) {
                    res += (q - p);
                } else {
                    res += Math.max(q - p - (cnt0 * cnt0 - cnt1), 0);
                }
            }

            if (s.charAt(left) == '0') {
                sid++;
            } else {
                res += (zero.get(sid) - left);
                ones--;
            }
        }
        return (int) res;
    }
}
