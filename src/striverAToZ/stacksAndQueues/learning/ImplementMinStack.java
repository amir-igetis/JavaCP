package striverAToZ.stacksAndQueues.learning;

import java.util.Stack;

public class ImplementMinStack {
    public static void main(String[] args) {
        //
        ImplMinStack st = new ImplMinStack();
        st.push(1);
        st.push(5);
        st.push(12);
        st.push(3);
        System.out.println(st.top());
        System.out.println(st.getMin());
    }

    private static class ImplMinStack {
        // striver optimized code
        Stack<Long> st = new Stack<Long>();
        Long mini;

        /**
         * initialize your data structure here.
         */
        ImplMinStack() {
            mini = Long.MAX_VALUE;
        }

        void push(int value) {
            Long val = Long.valueOf(value);
            if (st.isEmpty()) {
                mini = val;
                st.push(val);
            } else {
                if (val < mini) {
                    st.push(2 * val - mini);
                    mini = val;
                } else {
                    st.push(val);
                }
            }
        }

        void pop() {
            if (st.isEmpty())
                return;

            Long val = st.pop();
            if (val < mini) {
                mini = 2 * mini - val;
            }
        }

        int top() {
            Long val = st.peek();
            if (val < mini) {
                return mini.intValue();
            }
            return val.intValue();
        }

        int getMin() {
            return mini.intValue();
        } // tc o(1) & O(n)
    }

// Say, we have to currently push x and current minElement is minEle
// Now, we are going to use approach in which instead of pushing and popping
// actual elements, we would perform some operations before pushing and popping.
// The aim is to somehow obtain the last minEle when an item would be popped
// without scanning whole current stack.
// So, let's say, y is being pushed actually into stack instead of x and current
// minElement is minEle.
// Now, following conditions arises,
// while pushing
// * y=x-minEle //only difference is pushed as if while popping, we can check if
// y<0 => minEle would be changed else would be same as current
// * minEle=min(minEle,x) //updating current minEle
// while Popping
// y=top element of stack
// if(y≥0):
// * there would be no change as element which was pushed currently was greater
// than minEle, so no change in minEle. But x=minEle+y
// if(y<0):
// * x=minEle //imagine this if x was being pushed and it was less than current
// minEle, minEle would be updated
// * minEle=minEle-y //minEle updated to last minEle at time of last element in
// stack was pushed
// If not following, just observe how elements would be pushed according to
// formula y=x-minEle and track all three variables x,y,minEle at each push
// operation
// Apply it on this test case,
// push(2)
// push(3)
// push(1)
// push(6)
// push(0)
// Now, start popping each one and try to calculate last minEle, x from y and
// current minEle.
// Hopefully, you will be able to understand it.

    // soln for
// https://practice.geeksforgeeks.org/problems/get-minimum-element-from-stack/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=get-minimum-element-from-stack
    private static class GfG {
        int minEle;
        // Stack<Integer> s;
        Stack<Integer> s = new Stack<>();

        /* returns min element from stack */
        int getMin() {
            // Your code here
            if (s.isEmpty()) {
                return -1;
            } else {
                return minEle;
            }
        }

        /* returns poped element from stack */
        int pop() {
            // Your code here
            if (s.isEmpty()) {
                return -1;
            }
            int y = s.peek();
            s.pop();
            int x;
            if (y < 0) {
                x = minEle;
                minEle = minEle - y;
            } else {
                x = minEle + y;
            }
            return x;
        }

        /* push element x into the stack */
        void push(int x) {
            // Your code here
            if (s.isEmpty() == true) {
                minEle = x;
                s.push(0);
            } else {
                s.push(x - minEle);
                minEle = Math.min(minEle, x);
            }
        }
    }

    // soln for https://leetcode.com/problems/min-stack/
    private static class MinStackI {
        private Stack<Integer> data;
        private Stack<Integer> mins;

        /**
         * initialize your data structure here.
         */
        MinStackI() {
            data = new Stack<Integer>();
            mins = new Stack<Integer>();
        }

        void push(int x) {
            data.push(x);
            if (mins.isEmpty()) {
                mins.push(x);
            } else {
                mins.push(Math.min(x, mins.peek()));
            }
        }

        void pop() {
            data.pop();
            mins.pop();

        }

        int top() {
            return data.peek();
        }

        int getMin() {
            return mins.peek();
        }
    }
}
