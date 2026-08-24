package striverAToZ.linkedList.mediumProbs;

public class DeleteTheMiddleNodeOfTheLinkedList {

    ///  Question 10
    ///
    /// Problem Statement: Given the head of a linked list of integers, delete the middle node of the linked list and return the modified head. However, if the linked list has an even number of nodes, delete the second middle node.
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Display the original linked list
        System.out.print("Original Linked List: ");
        printLL(head);

        // Deleting the middle node
        head = deleteMiddleI(head);

        // Displaying the updated linked list
        System.out.print("Updated Linked List: ");
        printLL(head);
    }

    private static void printLL(Node head) {
        // Initialize a temporary pointer
        Node temp = head;
        // Traverse the linked list and print data
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        // Print a newline after the list
        System.out.println();
    }

    // brute

    /// Time Complexity: O(N + N/2), we traverse the entire linked list once to count the number of nodes and then traverse again to delete the middle node.
    /// Space Complexity: O(1) , we have fixed number of pointers and variables to delete the Kth node.
    // Node class represents a node in a linked list
    static Node deleteMiddle(Node head) {
        // Initialize a temporary node to traverse the linked list
        Node temp = head;
        // Variable to hold the number of nodes in the linked list
        int n = 0;
        // Loop to count the number of nodes in the linked list
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        // Calculate the index of the middle node
        int res = n / 2;
        // Reset the temporary node to the beginning of the linked list
        temp = head;
        // Loop to find the middle node to delete
        while (temp != null) {
            res--;
            // If the middle node is found
            if (res == 0) {
                // Create a pointer to the middle node
                Node middle = temp.next;
                // Adjust pointers to skip the middle node
                temp.next = temp.next.next;
                // Exit the loop after deleting the middle node
                break;
            }
            // Move to the next node in the linked list
            temp = temp.next;
        }
        // Return the head of the modified linked list
        return head;
    }

    // optimal

    /// Time Complexity: O(N/2), we traverse the entire linked list using slow and fast pointers, effectively covering about half the list before reaching the midpoint.
    /// Space Complexity: O(1) , we have fixed number of pointers and variables to delete the Kth node.
    static Node deleteMiddleI(Node head) {
        // If list has only one node, delete it
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize slow pointer to head
        Node slow = head;

        // Initialize fast pointer two steps ahead
        Node fast = head.next.next;

        // Traverse until fast reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Bypass the middle node
        slow.next = slow.next.next;

        // Return head of updated list
        return head;
    }

}
