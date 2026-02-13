package striverAToZ.stacksAndQueues.learning;

public class ImplementStackUsingArrays {
    public static void main(String[] args) {

        Stack st = new Stack();
        st.push(5);
        st.push(7);
        st.push(9);
        st.push(1);
        st.pop();
        st.pop();
        st.push(3);
        System.out.println(st.top());
        System.out.println(st.size());

    }

    private static class Stack {
        int size = 10000;
        int[] arr = new int[size];
        int top = -1;

        void push(int x) {
            if (top == 9999)
                size += size / 10;
            top++;
            arr[top] = x;
        }

        int pop() {
            if (top == -1)
                return -1;
            int x = arr[top];
            top--;
            return x;
        }

        int top() {
            return arr[top];
        }

        int size() {
            return top + 1;
        }
    }
}


