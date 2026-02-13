package stackProbs;

import java.util.LinkedList;
import java.util.Queue;

public class StackImp {
    public static void main(String[] args) {
        StackCl st = new StackCl();
    }
}


class StackCl {
    int size = 1000;
    int[] arr = new int[size];
    int top = -1;

    void push(int x) {
        top++;
        arr[top] = x;
    }

    int pop() {
        if (top == -1) return -1;
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

class StackLL {
    StackNode top;
    int size = 0;

    void push(int val) {
        StackNode temp = new StackNode(val);
        temp.next = top;
        top = temp;
        size++;
    }

    int pop() {
        if (top == null) return -1;
        int res = top.val;
        top = top.next;
        size--;
        return res;
    }

    int top() {
        if (top == null) return -1;
        return top.val;
    }

    int size() {
        return size;
    }
}

class StackNode {
    int val;
    StackNode next;

    StackNode(int val) {
        this.val = val;
        this.next = null;
    }
}


class StackQ {
    Queue<Integer> q = new LinkedList<>();

    void push(int x) {
        q.add(x);
        int size = q.size();
        for (int i  =0; i < q.size(); i++)
            q.add(q.remove());
    }

    int pop() {
        return q.remove();
    }

    int top() {
        return q.peek();
    }

    boolean isEmpty() {
        return q.isEmpty();
    }

    int size() {
        return q.size();
    }
}

class StackDoubleQ {
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    void push(int val) {
        q2.add(val);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    int pop() {
        if (q1.isEmpty())
            return -1;
        return q1.remove();
    }

    int top() {
        if (q1.isEmpty()) return -1;
        return q1.peek();
    }

    boolean isEmpty() {
        return q1.isEmpty();
    }

    int size() {
        return q1.size();
    }
}