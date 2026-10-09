package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        int n = 3;
        List<String> ans = generateParenthesis(n);
        for (String i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static char op = '(';

    static List<String> generateParenthesis(int n) {
        if (n-- == 1)
            return List.of("()");

        List<String> res = new ArrayList<>();
        int sz = n << 1;
        int mask = (1 << n) - 1;

        while (mask < 1 << sz) {
            String s = "(";
            int bal = 1;

            for (int i = 0; i < sz; i++) {
                int b = (mask >> i) & 1;
                bal += 1 - (b << 1);

                if (bal < 0)
                    break;

                s += (char) (op | b);
            }

            if (s.length() - 1 == sz)
                res.add(s + ")");

            int c = mask & -mask;
            int r = mask + c;
            mask = (((r ^ mask) >> 2) / c) | r;
        }
        return res;
    }
}
