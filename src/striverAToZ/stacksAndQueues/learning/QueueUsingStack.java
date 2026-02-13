package striverAToZ.stacksAndQueues.learning;

import java.util.Stack;

public class QueueUsingStack {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.push(3);
        q.push(4);
        System.out.println("The element popped is " + q.pop());
        q.push(5);
        System.out.println("The top element is " + q.peek());
        System.out.println("The size of the queue is " + q.size());
    }

    private static class Queue {
        Stack<Integer> input = new Stack<>();
        Stack<Integer> output = new Stack<>();

        public Queue() {

        }

        void push(int x) {
            while (!input.isEmpty()) {
                output.push(input.peek());
                input.pop();
            }
            System.out.println("The element pushed is" + x);
            input.push(x);
            while (!output.isEmpty()) {
                input.push(output.peek());
                output.pop();
            }
        }

        int pop() {
            if (input.isEmpty()) {
                System.out.println("Stack is empty");
            }
            int val = input.peek();
            input.pop();
            return val;
        }

        int peek() {
            if (input.isEmpty())
                System.out.println("Stack is empty");
            return input.peek();
        }

        int size() {
            return input.size();
        }


    }

    private static class MyStackI {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public void push(int x) {
            s1.push(x);
        }

        public int pop() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            }
            return s2.pop();
        }

        public int peek() {
            if (!s2.isEmpty()) {
                return s2.peek();
            } else {
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            }
            return s2.peek();
        }

        public boolean empty() {
            return s1.empty() && s2.empty();
        }
    }

    // soln for
// https://practice.geeksforgeeks.org/problems/queue-using-stack/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=queue-using-stack
    private static class QueueII {
        Stack<Integer> input = new Stack<Integer>();
        Stack<Integer> output = new Stack<Integer>();

        /* The method pop which return the element poped out of the stack */
        int dequeue() {
            // Your code here
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            int data = -1;
            if (!output.isEmpty()) {
                data = output.pop();
            }
            while (!output.isEmpty()) {
                input.push(output.pop());
            }
            return data;
        }

        /* The method push to push element into the stack */
        void enqueue(int x) {
            // Your code here
            input.push(x);
        }
    }
}
