package striverAToZ.stacksAndQueues.learning;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
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
        Queue<Integer> q = new LinkedList<>();

        void push(int x) {
            q.add(x);
            for (int i = 0; i < q.size(); i++)
                q.add(q.remove());
        }

        int pop() {
            return q.remove();
        }

        int top() {
            if (q.isEmpty())
                return -1;
            return q.peek();
        }

        int size() {
            return q.size();
        }
    }

    private static class Queues {
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        // Function to push an element into stack using two queues.
        void push(int a) {
            // Your code here
            q1.add(a);
        }

        // Function to pop an element from stack using two queues.
        int pop() {
            // Your code here
            int pop = -1;
            if (!q1.isEmpty()) {
                while (q1.size() != 1) {
                    q2.add(q1.poll());
                }
                pop = q1.poll();
                while (!q2.isEmpty()) {
                    q1.add(q2.poll());
                }
            }
            return pop;
        }
    }
}
