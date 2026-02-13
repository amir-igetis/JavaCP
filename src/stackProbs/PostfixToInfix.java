package stackProbs;

import java.util.Stack;

public class PostfixToInfix {
    public static void main(String[] args) {
        //
    }

    static String postfixToInfix(String str) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) {
                st.push(c + "");
            } else {
                String a = st.pop();
                st.push("(" + st.pop() + "" + c + a + ")");
            }
        }
        return st.pop();
    }
}
