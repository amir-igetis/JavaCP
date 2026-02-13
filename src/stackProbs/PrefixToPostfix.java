package stackProbs;

import java.util.Stack;

public class PrefixToPostfix {
    public static void main(String[] args) {
        //
    }

    static String prefixToPostfix(String str) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < str.length() - 1; i++) {
            char c = str.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c<= 90)) {
                st.push(c + "");
            } else {
                st.push(st.pop() +  "" + st.pop() + c);
            }
        }
        return st.pop();
    }
}
