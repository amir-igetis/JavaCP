package striverAToZ.greedyAlgo.easyProb;

import java.util.Stack;

public class ValidParentChecker {
    public static void main(String[] args) {
        String x = "{([])}";
        System.out.println(ispar(x));
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/parenthesis-checker2744/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=parenthesis-checker
    static boolean ispar(String x) {
        // add your code here
        Stack<Character> st = new Stack<>();
        for (char it : x.toCharArray()) {
            if (it == '(' || it == '[' || it == '{') {
                st.push(it);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                char ch = st.pop();
                if ((it == ')' && ch == '(') || (it == ']' && ch == '[') || (it == '}' && ch == '{')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    // Another soln
    static boolean isparI(String x) {
        int len = Integer.MAX_VALUE;
        while (len > x.length()) {
            len = x.length();
            x = x.replace("()", "");
            x = x.replace("{}", "");
            x = x.replace("[]", "");
        }
        if (x.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    // soln for https://leetcode.com/problems/valid-parenthesis-string/description/
    static boolean checkValidString(String s) {
        int start = 0, end = 0;
        char[] str = s.toCharArray();
        int len = str.length - 1;
        for (int i = 0; i <= len; i++) {
            if (str[i] == '(' || str[i] == '*')
                start++;
            else
                start--;
            if (str[len - i] == ')' || str[len - i] == '*')
                end++;
            else
                end--;
            if (start < 0 || end < 0)
                return false;
        }
        return true;
    }

    // Another soln using greedy algo
    // https://leetcode.com/problems/valid-parenthesis-string/description/
    static boolean checkValidStringI(String s) {
        int low = 0;
        int high = 0;
        char[] str = s.toCharArray();
        for (int i : str) {
            if (i == '(') {
                low += 1;
                high += 1;
            } else if (i == ')') {
                low -= 1;
                high -= 1;
            } else if (i == '*') {
                low -= 1;
                high += 1;
            }
            low = Math.max(low, 0);
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }

}
