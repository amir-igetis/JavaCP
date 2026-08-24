package striverAToZ.linkedList.mediumProbs;

import java.util.HashMap;

public class LengthOfLoopInLL {

    /// Question 6
    ///
    /// Problem Statement: Given the head of a linked list, determine the length of a loop present in the linked list. If there's no loop present, return 0.

    public static void main(String[] args) {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        // Linking the nodes
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        // Creating a loop from fifth to second
        fifth.next = second;

//        int loopLength = lengthOfLoop(head);
        int loopLength = lengthOfLoopI(head);

        // Printing the result
        if (loopLength > 0) {
            System.out.println("Length of the loop: " + loopLength);
        } else {
            System.out.println("No loop found in the linked list.");
        }
    }

    // brute

    /// Time Complexity: O(N), we traverse the entire linked list atleast once to find the length of the loop.
    /// Space Complexity: O(N) , we use a map to store the timers for the nodes in the linked list.

    static int lengthOfLoop(Node head) {
        // Hashmap to store visited nodes and their timer values
        HashMap<Node, Integer> visitedNodes = new HashMap<>();

        // Pointer to traverse the linked list
        Node temp = head;

        // Timer to track visited nodes
        int timer = 0;

        // Traverse the linked list till temp reaches null
        while (temp != null) {
            // If revisiting a node, return the difference of timer values
            if (visitedNodes.containsKey(temp)) {
                // Calculate the length of the loop
                int loopLength = timer - visitedNodes.get(temp);

                // Return the length of the loop
                return loopLength;
            }

            // Store the current node and its timer value
            visitedNodes.put(temp, timer);

            // Move to the next node
            temp = temp.next;

            // Increment the timer
            timer++;
        }

        // If traversal is completed and we reach the end of the list
        // means there is no loop
        return 0;
    }

    // optimal

    /// Time Complexity: O(N), we traverse the entire linked list atleast once to find the length of the loop.
    /// Space Complexity: O(1) , we use a constant amount of additional space, regardless of the linked list's length to find the length of the loop.

    static int lengthOfLoopI(Node head) {
        // Initialize slow and fast pointers
        Node slow = head;
        Node fast = head;

        // Loop until fast and slow meet
        while (fast != null && fast.next != null) {
            // Move slow by one step
            slow = slow.next;

            // Move fast by two steps
            fast = fast.next.next;

            // If slow and fast meet, loop detected
            if (slow == fast) {
                // Count the length of the loop
                return countLoopLength(slow);
            }
        }

        // No loop found
        return 0;
    }

    // Function to count loop length
    private static int countLoopLength(Node meetingPoint) {
        // Start from meeting point
        Node temp = meetingPoint;
        int length = 1;

        // Move until we meet again
        while (temp.next != meetingPoint) {
            temp = temp.next;
            length++;
        }
        return length;
    }
}
