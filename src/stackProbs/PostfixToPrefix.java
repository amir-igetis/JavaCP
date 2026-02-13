package stackProbs;

import java.util.Stack;

public class PostfixToPrefix {
    public static void main(String[] args) {
        //
    }

    static String postfixToPrefix(String str) {
        Stack<String> st = new Stack<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) {
                st.push(c + "");
            } else {
                String a = st.pop();
                String b = st.pop();
                st.push(c + "" + b + a);
            }
        }
        return st.pop();
    }
}
