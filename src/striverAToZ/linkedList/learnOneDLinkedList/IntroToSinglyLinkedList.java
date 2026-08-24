package striverAToZ.linkedList.learnOneDLinkedList;

public class IntroToSinglyLinkedList {

    /// What is a Linked List?
    ///
    /// Imagine you are given a task where you have to maintain a data entry of cars entering a parking lot. Since the number of cars entering will be different and may change daily, constructing a fixed-sized data structure like an array might not be helpful. This is when linked lists come into the picture, which allows us to add and remove cars easily. Linked List is a linear data structure that can be visualized as a chain with different nodes connected, where each node represents a different element. The difference between arrays and linked lists is that, unlike arrays, the elements are not stored at a contiguous location.
    ///
    /// For any element to be added in an array, we need the exact next memory location to be empty and it is impossible to guarantee that it is possible. Hence adding elements to an array is not possible after the initial assignment of size.
    ///
    ///
    /// A linked list is a data structure containing two crucial pieces of information, the first being the data and the other being the pointer to the next element. The ‘head’ is the first node, and the ‘tail’ is the last node in a linked list.


    private // Node class represents a node in the linked list
    static class Node {
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

    public static void main(String[] args) {
        // Create an array
        int[] arr = {2, 5, 8, 7};

        // Create first node
        Node y = new Node(arr[0]);

        // Print memory reference of node
        System.out.println(y);

        // Print data stored in node
        System.out.println(y.data);
    }

}
