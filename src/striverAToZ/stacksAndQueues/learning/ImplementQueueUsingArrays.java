package striverAToZ.stacksAndQueues.learning;

public class ImplementQueueUsingArrays {
    public static void main(String[] args) {
        Queue q = new Queue(6);
        q.push(4);
        q.push(14);
        q.push(24);
        q.push(34);
        System.out.println("The peek of the queue before deleting any element " + q.top());
        System.out.println("The size of the queue before deletion " + q.size());
        System.out.println("The first element to be deleted " + q.pop());
        System.out.println("The peek of the queue after deleting an element " + q.top());
        System.out.println("The size of the queue after deleting an element " + q.size());
    }

    private static class Queue {
        int[] arr;
        int start, end, currSize, maxSize;

        Queue() {
            arr = new int[16];
            start = -1;
            end = -1;
            currSize = 0;
        }

        Queue(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            start = -1;
            end = -1;
            currSize = 0;
        }

        void push(int x) {
            if (currSize == maxSize) {
                System.out.println("Queue is full!!!");
                System.exit(1);
            }
            if (end == -1) {
                start = 0;
                end = 0;
            } else {
                end = (end + 1) % maxSize;
            }
            arr[end] = x;
            System.out.println("The element pushed is " + x);
            currSize++;
        }

        int pop() {
            if (start == -1) {
                System.out.println("Queue is empty");
                System.exit(1);
            }
            int popped = arr[start];
            if (currSize == 1) {
                start = -1;
                end = -1;
            } else {
                start = (start + 1) % maxSize;
            }
            currSize--;
            return popped;
        }

        int top() {
            if (start == -1) {
                System.out.println("Queue is Empty");
                System.exit(1);
            }
            return arr[start];
        }

        int size() {
            return currSize;
        }


    }
}
