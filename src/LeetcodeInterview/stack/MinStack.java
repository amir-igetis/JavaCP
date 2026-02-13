package LeetcodeInterview.stack;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
//

    }

    private static Stack<Integer> st;
    private static Stack<Integer> extra;

    static void MinStack() {
        st = new Stack<>();
        extra = new Stack<>();
    }

    static void push(int val) {
        extra.push(val);
        if (st.isEmpty() || val <= st.peek()) {
            st.push(val);
        } else {
            st.push(st.peek()); // Push the previous min again to maintain sync
        }
    }

    static void pop() {
        if (!extra.isEmpty()) {
            extra.pop();
            st.pop();
        }
    }

    static int top() {
        return extra.peek();
    }

    static int getMin() {
        return st.peek();
    }
}
