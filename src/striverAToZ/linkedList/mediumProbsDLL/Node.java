package striverAToZ.linkedList.mediumProbsDLL;

public class Node {
    int data;
    Node next;
    Node back;

    // Constructor for a Node with both data, next, and back references
    public Node(int data1, Node next1, Node back1) {
        data = data1;
        next = next1;
        back = back1;
    }

    // Constructor for a Node with only data, no next or back references (end of the list)
    public Node(int data1) {
        data = data1;
        next = null;
        back = null;
    }
}
