package striverAToZ.stacksAndQueues.learning;

public class QueueUsingLinkedList {
    public static void main(String[] args) {
        //
        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.dequeue();
        System.out.println("The size of the Queue is " + q.size);
        System.out.println("The peek element of the queue is " + q.peek());
    }

    private static class Queue {
        QueueNode front = null, rear = null;
        int size = 0;

        boolean empty() {
            return front == null;
        }

        int peek() {
            if (empty()) {
                System.out.println("Queue is Empty");
                return -1;
            } else {
                return front.val;
            }
        }

        void enqueue(int value) {
            QueueNode temp;
            temp = new QueueNode(value);
            if (temp == null) {
                System.out.println("Queue is full");
            } else {
                if (front == null) {
                    front = temp;
                    rear = temp;
                } else {
                    rear.next = temp;
                    rear = temp;
                }
                System.out.println(value + " Inserted into Queue");
                size++;
            }
        }

        void dequeue() {
            if (front == null) {
                System.out.println("Queue is empty");
            } else {
                System.out.println(front.val + " Rempved from queue");
                QueueNode temp = front;
                front = front.next;
                size--;
            }
        } // tc O(1) & sc O(1)
    }

    // striver code
    private static class QueueNode {
        int val;
        QueueNode next;

        QueueNode(int data) {
            val = data;
            next = null;
        }
    }

// soln for https://practice.geeksforgeeks.org/problems/implement-queue-using-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-queue-using-linked-list
// The structure of the node of the queue is
// class QueueNode {
// 	int data;
// 	QueueNode next;
// 	QueueNode(int a)
// 	{
// 	    data = a;
// 	    next = null;
// 	}
// }

// class MyQueue {
//     QueueNode front, rear;

//     //Function to push an element into the queue.
// 	void push(int a) {
//         // Your code here
//         QueueNode node = new QueueNode(a);
//         if (front == null) {
//             front = rear = node;
//         } else {
//             rear.next = node;
//             rear = node;
//         }
// 	}

//     //Function to pop front element from the queue.
// 	int pop() {
//         // Your code here
//         if (front == null) {
//             return -1;
//         } else if (front.next == null) {
//             int val = front.data;
//             front = rear = null;
//             return val;
//         } else {
//             QueueNode fwd = front.next;
//             int val = front.data;
//             front.next = null;
//             front = fwd;
//             return val;
//         }
// 	}
// }

// another soln to https://practice.geeksforgeeks.org/problems/implement-queue-using-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-queue-using-linked-list

// class MyQueue {
//     QueueNode front, rear;

//     //Function to push an element into the queue.
// 	void push(int a) {
//         // Your code here
//         QueueNode node = new QueueNode(a);
//         if (front == null) {
//             front = rear = node;
//         } else {
//             rear.next = node;
//             rear = node;
//         }
// 	}

//     //Function to pop front element from the queue.
// 	int pop() {
//         // Your code here
//         int ans;
//         if (front == null) {
//             ans =- 1;
//         } else {
//             ans = front.data;
//             front = front.next;
//         }
//         return ans;
// 	}
// }
}

