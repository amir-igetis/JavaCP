package striverAToZ.linkedList.learnOneDLinkedList;

public class FindTheLengthOfALinkedList {

    /// Problem Statement: Given the head of a linked list, print the length of the linked list.
    public static void main(String[] args) {
// Creating a sample linked list
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);


        // Find and print the length of linked list
        System.out.println("Length of Linked List: "
                + lengthOfLinkedList(head));
    }

    /// Time Complexity: O(N), we traverse the entire linked list once to find the total number of nodes.
    /// Space Complexity: O(1) , we use fixed number of pointers and variables to find the length of linked list.

    static int lengthOfLinkedList(Node head) {
        // Initialize counter to 0
        int count = 0;

        // Initialize a temporary pointer to head
        Node temp = head;

        // Traverse the linked list
        while (temp != null) {
            // Increment count for each node
            count++;

            // Move to the next node
            temp = temp.next;
        }

        // Return the total count
        return count;
    }
}
