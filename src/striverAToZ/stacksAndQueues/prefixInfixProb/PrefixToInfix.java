package striverAToZ.stacksAndQueues.prefixInfixProb;

import java.util.Stack;

public class PrefixToInfix {
    public static void main(String[] args) {
//
        String exp = "*+pq-mn";
        System.out.println("prefix exp: " + exp);
        System.out.println("Infix exp: " + preToInfix(exp));

    }

    static String preToInfix(String pre) {
        Stack<String> st = new Stack<>();
        for (int i = pre.length() - 1; i >= 0; i--) {
            char c = pre.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90))
                st.push(c + "");
            else {
                String a = st.pop();
                String b = st.pop();
                st.push("(" + (a + "" + c + "" + b) + ")");
            }
        }
        return st.pop();
    }
}
