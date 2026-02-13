package LeetcodeInterview.stack;

import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] args) {
//
        String path = "/home/";
        System.out.println(simplifyPath(path));
    }

    static String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.equals("..")) {
                if (!st.isEmpty())
                    st.pop();
            } else if (!part.equals("") && !part.equals(".")) {
                st.push(part);
            }
        }

        StringBuilder res = new StringBuilder();
        for (String dir : st) {
            res.append("/").append(dir);
        }
        return res.length() == 0 ? "/" : res.toString();
    }
}
