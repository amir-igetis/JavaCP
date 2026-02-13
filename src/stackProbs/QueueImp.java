package stackProbs;

import java.util.Stack;

public class QueueImp {
    public static void main(String[] args) {

    }
}

class QueueArr {
    int[] arr;
    int front, rear, currSize, maxSize;

    public QueueArr(int size) {
        this.arr = new int[maxSize];
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = -1;
    }

    public void enqueue(int val) {
        rear = (rear + 1) % maxSize;
        arr[rear] = val;
        currSize++;
    }

    int dequeue() {
        int deqElem = arr[front];
        front = (front + 1) % maxSize;
        currSize--;
        return deqElem;
    }

    boolean isEmpty() {
        return currSize == 0;
    }

    boolean isFull() {
        return currSize == maxSize;
    }

    int peek() {
        return arr[front];
    }

    int size() {
        return currSize;
    }
}


class QueueLL {
    QueueNode front = null, rear = null;
    int size = 0;

    void enqueue(int data) {
        QueueNode temp = new QueueNode(data);
        if (front == null) {
        front = temp;
        rear = temp;
        } else {
            rear.next = temp;
            rear = temp;
        }
        size++;
    }

    int dequeue() {
        int elem = front.val;
        front = front.next;
        if (front == null)
            rear =null;
        size--;
        return elem;
    }

    boolean isEmpty() {
        return front == null;
    }

    int size() {
        return size;
    }

    int peek() {
        return front.val;
    }
}

class QueueNode {
    int val;
    QueueNode next;

    QueueNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class QueueSt {
    Stack<Integer> inp = new Stack<>();
    Stack<Integer> oup = new Stack<>();

    void push(int val) {
        while (inp.empty() == false) {
            oup.push(inp.peek());
            inp.pop();
        }
        inp.push(val);
        while (oup.empty() == false) {
            inp.push(oup.peek());
            oup.pop();
        }
    }

    int pop() {
        if (inp.empty()) {
            return -1;
        }

        int val = inp.peek();
        inp.pop();
        return val;
    }

    int peek() {
        return inp.peek();
    }
    int size() {
        return inp.size();
    }
}

class QueueSingleSt {
    Stack<Integer> stack = new Stack<>();

    // Enqueue operation: Push the element onto the stack
    public void enqueue(int element) {
        stack.push(element);  // Push the new element onto the stack
        System.out.println("Enqueued " + element);
    }

    // Dequeue operation: Remove the front element of the queue using recursion
    public int dequeue() {
        if (stack.isEmpty()) {  // If the stack is empty, the queue is empty
            System.out.println("Queue is empty!");
            return -1;  // Return -1 to indicate an empty queue
        }

        // Pop the top element from the stack
        int topElement = stack.pop();

        // If the stack becomes empty after popping, it means we found the first inserted element
        if (stack.isEmpty()) {
            System.out.println("Dequeued " + topElement);
            return topElement;  // This is the front element of the queue (FIFO)
        }

        // Otherwise, recursively call dequeue to reach the bottom element
        int dequeuedElement = dequeue();

        // Push the current top element back onto the stack after the recursive call
        stack.push(topElement);

        return dequeuedElement;  // Return the dequeued element
    }

    // Peek operation: Get the front element without removing it
    public int peek() {
        if (stack.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        int topElement = stack.pop();

        if (stack.isEmpty()) {
            stack.push(topElement);  // Restore the element
            return topElement;       // This is the front element of the queue
        }

        int frontElement = peek();   // Recursively find the front element
        stack.push(topElement);      // Restore the element after recursion

        return frontElement;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
