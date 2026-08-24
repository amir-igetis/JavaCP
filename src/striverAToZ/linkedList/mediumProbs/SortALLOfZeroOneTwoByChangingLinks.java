package striverAToZ.linkedList.mediumProbs;

public class SortALLOfZeroOneTwoByChangingLinks {

    /// Question 12
    ///
    /// Problem Statement: Given a linked list containing only 0's, 1's, and 2's, sort the linked list by rearranging the links (not by changing the data values).
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Inserting nodes into linked list
        ll.insert(1);
        ll.insert(2);
        ll.insert(0);
        ll.insert(1);
        ll.insert(2);
        ll.insert(0);

        System.out.println("Original List:");
        ll.print();

        // Sorting the list
        sortZeroOneTwo(ll);

        System.out.println("Sorted List:");
        ll.print();
    }

    /// Time Complexity: O(n), We traverse the entire list once.
    /// Space Complexity: O(1), Only dummy nodes and pointers are used (constant space).


    // LinkedList class to manage list operations
    private static class LinkedList {
        Node head;

        // Constructor to initialize an empty list
        LinkedList() {
            head = null;
        }

        // Function to insert a new node at the end
        void insert(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = newNode;
                return;
            }
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }

        // Function to print the entire linked list
        void print() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data);
                if (temp.next != null) System.out.print(" -> ");
                temp = temp.next;
            }
            System.out.println(" -> NULL");
        }
    }

    static void sortZeroOneTwo(LinkedList ll) {
        // Create dummy nodes for 0s, 1s, and 2s
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        // Create tail pointers to add new nodes in respective lists
        Node zeroTail = zeroDummy;
        Node oneTail = oneDummy;
        Node twoTail = twoDummy;

        Node curr = ll.head;

        // Traverse the original list
        while (curr != null) {
            if (curr.data == 0) {
                zeroTail.next = curr;
                zeroTail = zeroTail.next;
            } else if (curr.data == 1) {
                oneTail.next = curr;
                oneTail = oneTail.next;
            } else {
                twoTail.next = curr;
                twoTail = twoTail.next;
            }
            curr = curr.next;
        }

        // Connect 0s list to 1s, and 1s to 2s
        zeroTail.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        oneTail.next = twoDummy.next;
        twoTail.next = null;

        // Update original list head
        ll.head = zeroDummy.next;
    }
}
