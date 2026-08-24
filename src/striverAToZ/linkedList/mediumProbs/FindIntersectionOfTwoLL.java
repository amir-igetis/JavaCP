package striverAToZ.linkedList.mediumProbs;

import java.util.HashSet;
import java.util.Set;

public class FindIntersectionOfTwoLL {

    ///  Question 13
    ///
    /// Problem Statement: Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

    public static void main(String[] args) {
// Creation of both lists
        Node head = new Node(1);
        insertNode(head, 3);
        insertNode(head, 1);
        insertNode(head, 2);
        insertNode(head, 4);
        Node head1 = head;
        head = head.next.next.next; // Intersection point
        Node headSec = new Node(3);
        Node head2 = headSec;
        headSec.next = head; // Creating intersection

        // Printing of the lists
        System.out.print("List1: ");
        printList(head1);
        System.out.print("List2: ");
        printList(head2);

        // Checking if intersection is present
        Node answerNode = intersectionPresent(head1, head2);
        if (answerNode == null) {
            System.out.println("No intersection");
        } else {
            System.out.println("The intersection point is " + answerNode.data);
        }
    }

    // brute

    /// Time Complexity: O(m × n), For each node in list 2, the entire list 1 is iterated, resulting in nested iterations.
    ///
    /// Space Complexity: O(1), No extra space is used; the comparison is done in-place.

    private static void insertNode(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Utility function to check presence of intersection
    static Node intersectionPresent(Node head1, Node head2) {
        while (head2 != null) {
            Node temp = head1;
            while (temp != null) {
                if (temp == head2) return head2;
                temp = temp.next;
            }
            head2 = head2.next;
        }
        return null; // No intersection found
    }

    // Utility function to print linked list
    private static void printList(Node head) {
        while (head != null && head.next != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        if (head != null) {
            System.out.print(head.data);
        }
        System.out.println();
    }

    // better

    /// Time Complexity: O(n + m), Iterating through list 1 first takes O(n), then iterating through list 2 takes O(m).
    ///
    /// Space Complexity: O(n), Storing list 1 node addresses in an unordered_set.


    // Utility function to check presence of intersection
    static Node intersectionPresentI(Node head1, Node head2) {
        Set<Node> st = new HashSet<>();  // Set to store visited nodes from the first list
        while (head1 != null) {
            st.add(head1);  // Add nodes of the first list to the set
            head1 = head1.next;
        }
        while (head2 != null) {
            if (st.contains(head2)) return head2;  // If node is found in set, it's the intersection point
            head2 = head2.next;
        }
        return null;  // Return null if no intersection is found
    }


    // optimal 1

    /// Time Complexity: O(2 × max(length of list1, length of list2)) + O(abs(length of list1 − length of list2)) + O(min(length of list1, length of list2)), Finding the length of both lists takes O(max) time since it's done simultaneously, then moving one pointer by the difference in lengths, and finally searching for the intersection.
    ///
    /// Space Complexity: O(1), No extra space is used.

    private static int getDifference(Node head1, Node head2) {
        int len1 = 0, len2 = 0;
        while (head1 != null || head2 != null) {
            if (head1 != null) {
                len1++;
                head1 = head1.next;
            }
            if (head2 != null) {
                len2++;
                head2 = head2.next;
            }
        }
        return len1 - len2;  // If negative, length of list2 > length of list1, else vice-versa
    }

    static Node intersectionPresentII(Node head1, Node head2) {
        int diff = getDifference(head1, head2);

        if (diff < 0) {
            while (diff++ != 0) head2 = head2.next;
        } else {
            while (diff-- != 0) head1 = head1.next;
        }

        // Traverse both lists and compare node by node
        while (head1 != null) {
            if (head1 == head2) return head1;  // Intersection point found
            head2 = head2.next;
            head1 = head1.next;
        }
        return null;  // Return null if no intersection
    }

    // optimal 2

    /// Time Complexity: O(2 × max(length of list1, length of list2)), Uses the same concept of difference of lengths of two lists.
    ///
    /// Space Complexity: O(1), No extra data structure is used.

    // Utility function to check presence of intersection
    static Node intersectionPresentIII(Node head1, Node head2) {
        Node d1 = head1;
        Node d2 = head2;

        // Traverse both lists, when one reaches the end, redirect it to the head of the other list
        while (d1 != d2) {
            d1 = d1 == null ? head2 : d1.next;
            d2 = d2 == null ? head1 : d2.next;
        }

        return d1;  // If they meet, return the intersection node, otherwise NULL
    }

}
