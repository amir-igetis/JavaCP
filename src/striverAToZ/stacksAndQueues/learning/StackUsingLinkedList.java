package striverAToZ.stacksAndQueues.learning;

public class StackUsingLinkedList {
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(3);
        s.push(2);
        s.push(4);
        s.push(1);
        System.out.println("Top of the stack: " + s.top());
        System.out.println("Size of the stack before removing element: " + s.size());
        System.out.println("The deleted element is: " + s.pop());
        System.out.println("Top of the stack after removing element: " + s.top());
        System.out.println("Size of the stack after removing element: " + s.size());

    }

    private static class Stack {
        StackNode top;
        int count = 0;

        void push(int a) {
            StackNode temp = new StackNode(a);
            temp.next = top;
            top = temp;
            count++;
        }

        int pop() {
            if (top == null)
                return -1;

            int res = top.data;
            top = top.next;
            count--;
            return res;
        }

        int top() {
            if (top == null)
                return -1;
            return top.data;
        }

        int size() {
            return count;
        }
    }

    private static class StackNode {
        int data;
        StackNode next;

        StackNode(int a) {
            data = a;
            next = null;
        }
    }
}
