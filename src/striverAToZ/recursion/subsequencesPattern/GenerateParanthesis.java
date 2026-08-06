package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {

    /// Problem Statement: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }

    /// Time Complexity: O(2^(2n) * n) due to the generation and validation of all 2^(2n) sequences.
    ///
    /// Space Complexity: O(n) space required per sequence.
    // brute force
    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateAll("", n, res);
        return res;
    }

    private static boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    private static void generateAll(String curr, int n, List<String> res) {
        if (curr.length() == 2 * n) {
            if (isValid(curr)) res.add(curr);
            return;
        }
        generateAll(curr + "(", n, res);
        generateAll(curr + ")", n, res);
    }

    /// Time Complexity: O(2^n) (Catalan number): C(n) = (2n)! / (n!(n+1)!) is the number of valid sequences.
    /// Each sequence takes O(n) to build.
    /// So, total complexity: O(C(n) × n)
    ///
    /// Space Complexity: O(n) recursion depth.
    /// O(C(n) × n) to store results.
    // optimal approach
    static List<String> generateParenthesisI(int n) {
        List<String> res = new ArrayList<>();
        backtrack("", 0, 0, n, res);
        return res;
    }

    private static void backtrack(String curr, int open, int close, int n, List<String> res) {
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }
        if (open < n) backtrack(curr + "(", open + 1, close, n, res);
        if (close < open) backtrack(curr + ")", open, close + 1, n, res);
    }
}


