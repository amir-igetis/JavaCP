package LeetcodeInterview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
//
        int n = 3;
        List<String> ans = generateParenthesis(n);
        for (String i : ans) {
            System.out.print(i + ", ");
        }
        System.out.println();

    }

    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    static void backtrack(List<String> res, String curr, int open, int close, int max) {
        if (curr.length() == max * 2) {
            res.add(curr);
            return;
        }

        if (open < max) {
            backtrack(res, curr + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(res, curr + ")", open, close + 1, max);
        }
    }
}
