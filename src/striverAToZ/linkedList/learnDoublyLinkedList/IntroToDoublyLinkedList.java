package striverAToZ.linkedList.learnDoublyLinkedList;

public class IntroToDoublyLinkedList {

    /// Before exploring doubly linked lists, let's refresh our knowledge of linked lists. Linked lists are linear data structures consisting of nodes, each containing data and a reference (or pointer) to the next node. This setup allows for dynamic memory allocation and efficient insertions and deletions.
    ///
    /// A significant characteristic of singly linked lists is their unidirectional nature, allowing traversal in only one direction i.e forward. Moving backward, such as going from node at 3rd position to node at 1st position is not possible because each node in a singly linked list holds two pieces of information: the data (an integer value in this case) and a pointer that indicates the address of the next node. This structure enables efficient forward navigation, but the absence of a backward pointer restricts reverse traversal.

    // Node class represents a node in the linked list
    private static class Node {
        int data;      // Data value
        Node next;     // Pointer to next node

        // Constructor with data and next
        Node(int data1, Node next1) {
            data = data1;
            next = next1;
        }

        // Constructor with only data
        Node(int data1) {
            data = data1;
            next = null;
        }
    }

    /// Doubly Linked List
    /// Doubly Linked Lists,  as the name suggests, allows 2-way traversal by introducing two pointers in each node. This enables seamless traversal in both directions, making them a valuable tool for various advanced data structure applications.

    private static class NodeI {
        // Stores data of the node
        int data;

        // Pointer to the next node
        Node next;

        // Pointer to the previous node
        Node prev;

        // Constructor when data, next and prev are provided
        NodeI(int data1, Node next1, Node prev1) {
            data = data1;
            next = next1;
            prev = prev1;
        }

        // Constructor when only data is provided
        NodeI(int data1) {
            data = data1;
            next = null;
            prev = null;
        }
    }

    public static void main(String[] args) {
        // Create an array
        int[] arr = {2, 5, 8, 7};

        // Create first node
        Node y = new Node(arr[0]);

        // Print memory reference of node
        System.out.println(y);

        // Print data stored in node
        System.out.println(y.data);

        // doubley

        // Initializing an array to create nodes
        int[] arrI = {2, 5, 8, 7};

        // Creating the head node of the doubly linked list
        NodeI head = new NodeI(arrI[0]);

        // Printing the memory reference of head
        System.out.println(head);

        // Printing the data stored in head node
        System.out.println(head.data);
    }
}
