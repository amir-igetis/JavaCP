package striverAToZ.stacksAndQueues.learning;

import java.util.Stack;

public class CheckForBalancedParenthesis {
    public static void main(String[] args) {
        //
        String s = "()[{}()]";
        System.out.println(isValid(s));
    }

    static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char it : s.toCharArray()) {
            if (it == '(' || it == '{' || it == '[')
                st.push(it);
            else {
                if (st.isEmpty())
                    return false;
                char ch = st.pop();
                if ((it == ')' && ch == '(') ||
                        (it == '}' && ch == '{') ||
                        (it == ']' && ch == '['))
                    continue;
                else return false;
            }
        }
        return st.isEmpty();
    }
}
