package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class BraceExpansionII {
    public static void main(String[] args) {
        String expression = "{a,b}{c,{d,e}}";
        Solution sol = new Solution();
        List<String> ans = sol.braceExpansionII(expression);
        for (String i : ans)
            System.out.print(i + ", ");
        System.out.println();
    }

    // recursive parsing

    /// Let n be the length of expression, and M be the maximum number of words in any set (M≤2
    /// n/5
    ///   in the worst case).
    ///
    /// Time complexity: O(n⋅M).
    ///
    /// Traversal takes O(n) time, while intermediate Cartesian products and unions process up to M strings of length up to O(n). With n≤60, M≤4096, easily running within time limits.
    ///
    /// Space complexity: O(n⋅M).
    ///
    /// Dominated by storing intermediate and final sets of strings of length up to O(n), alongside O(n) recursion stack space.
    private static class Solution {

        String expression;
        int idx;

        public List<String> braceExpansionII(String expression) {
            this.expression = expression;
            this.idx = 0;
            Set<String> ret = expr();
            return new ArrayList<String>(ret);
        }

        // item -> letter | { expr }
        private Set<String> item() {
            Set<String> ret = new TreeSet<String>();
            if (expression.charAt(idx) == '{') {
                idx++;
                ret = expr();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(idx));
                ret.add(sb.toString());
            }
            idx++;
            return ret;
        }

        // term -> item | item term
        private Set<String> term() {
            // Initialize an empty set and take its Cartesian product with subsequent results
            Set<String> ret = new TreeSet<String>() {
                {
                    add("");
                }
            };
            // An item starts with { or a lowercase letter; continue matching only when this condition is met
            while (
                    idx < expression.length() &&
                            (expression.charAt(idx) == '{' ||
                                    Character.isLetter(expression.charAt(idx)))
            ) {
                Set<String> sub = item();
                Set<String> tmp = new TreeSet<String>();
                for (String left : ret) {
                    for (String right : sub) {
                        tmp.add(left + right);
                    }
                }
                ret = tmp;
            }
            return ret;
        }

        // expr -> term | term, expr
        private Set<String> expr() {
            Set<String> ret = new TreeSet<String>();
            while (true) {
                // Take the union with the result of term()
                ret.addAll(term());
                // Continue if a comma is matched; otherwise, stop matching
                if (idx < expression.length() && expression.charAt(idx) == ',') {
                    idx++;
                    continue;
                } else {
                    break;
                }
            }
            return ret;
        }
    }

    // stack

    /// Let n be the length of expression, and M be the maximum number of words in any set (M≤2
    /// n/5
    ///   in the worst case).
    ///
    /// Time complexity: O(n⋅M)
    ///
    /// Stack operations take O(n) time, while evaluating intermediate Cartesian products and unions processes up to M strings of length up to O(n). Because n≤60, M remains small, ensuring fast execution.
    ///
    /// Space complexity: O(n⋅M)
    ///
    /// Dominated by storing sets of strings on the operand stack, along with O(n) space for the stack structures themselves.
    static List<String> braceExpansionII_I(String expression) {
        Deque<Character> op = new ArrayDeque<Character>();
        List<Set<String>> stk = new ArrayList<Set<String>>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ',') {
                // Keep popping operators from the top of the stack until the stack is empty or its top is not a multiplication sign
                while (!op.isEmpty() && op.peek() == '*') {
                    ope(op, stk);
                }
                op.push('+');
            } else if (expression.charAt(i) == '{') {
                // First determine whether a multiplication sign needs to be added, then push { onto the operator stack
                if (
                        i > 0 &&
                                (expression.charAt(i - 1) == '}' ||
                                        Character.isLetter(expression.charAt(i - 1)))
                ) {
                    op.push('*');
                }
                op.push('{');
            } else if (expression.charAt(i) == '}') {
                // Keep popping operators from the top of the stack until its top is {
                while (!op.isEmpty() && op.peek() != '{') {
                    ope(op, stk);
                }
                op.pop();
            } else {
                // First determine whether a multiplication sign needs to be added, then push the newly constructed set onto the set stack
                if (
                        i > 0 &&
                                (expression.charAt(i - 1) == '}' ||
                                        Character.isLetter(expression.charAt(i - 1)))
                ) {
                    op.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                stk.add(
                        new TreeSet<String>() {
                            {
                                add(sb.toString());
                            }
                        }
                );
            }
        }

        while (!op.isEmpty()) {
            ope(op, stk);
        }
        return new ArrayList<String>(stk.get(stk.size() - 1));
    }

    // Pop the operator at the top of the stack and perform the calculation
    private static void ope(Deque<Character> op, List<Set<String>> stk) {
        int l = stk.size() - 2,
                r = stk.size() - 1;
        if (op.peek() == '+') {
            stk.get(l).addAll(stk.get(r));
        } else {
            Set<String> tmp = new TreeSet<String>();
            for (String left : stk.get(l)) {
                for (String right : stk.get(r)) {
                    tmp.add(left + right);
                }
            }
            stk.set(l, tmp);
        }
        op.pop();
        stk.remove(stk.size() - 1);
    }
}