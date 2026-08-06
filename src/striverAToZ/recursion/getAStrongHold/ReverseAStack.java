package striverAToZ.recursion.getAStrongHold;

import java.util.Stack;

public class ReverseAStack {

    /// Problem Statement: You are given a stack of integers. Your task is to reverse the stack using recursion. You may only use standard stack operations (push, pop, top/peek, isEmpty). You are not allowed to use any loop constructs or additional data structures like arrays or queues.
    ///
    /// Your solution must modify the input stack in-place to reverse the order of its elements.


    public static void main(String[] args) {
    // Create a sample stack
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(1);
        st.push(3);
        st.push(2);

        // Reverse the stack
        reverseStack(st);

        // Print the reversed stack
        System.out.print("Reversed Stack: ");
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }

    /// Time Complexity: O(n²), as each element is popped and inserted at the bottom (O(n) per element).
    ///
    /// Space Complexity: O(n), as only the recursion stack is used.
    // Function to reverse the stack
    static void reverseStack(Stack<Integer> st) {
        // Base case: If stack is empty, return
        if (st.isEmpty()) return;

        // Pop the top element
        int topVal = st.pop();

        // Recursively reverse the remaining stack
        reverseStack(st);

        // Insert the popped element at the bottom
        insertAtBottom(st, topVal);
    }


    private static void insertAtBottom(Stack<Integer> st, int val) {
        // If stack is empty, push the value
        if (st.isEmpty()) {
            st.push(val);
            return;
        }

        // Pop the top element
        int topVal = st.pop();

        // Recurse for the rest of the stack
        insertAtBottom(st, val);

        // Push the popped element back
        st.push(topVal);
    }
}
