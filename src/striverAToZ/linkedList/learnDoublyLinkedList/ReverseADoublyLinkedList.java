package striverAToZ.linkedList.learnDoublyLinkedList;

import java.util.Stack;

public class ReverseADoublyLinkedList {

	/// Question 3
	/// 
	/// Problem Statement: Given a doubly linked list of size ‘N’ consisting of
	/// positive integers, your task is to reverse it and return the head of the
	/// modified doubly linked list.

	public static void main(String[] args) {
		// Input array
		int[] arr = { 12, 5, 8, 7, 4 };

		// Convert to DLL
		Node head = convertArr2DLL(arr);

		System.out.println("Doubly Linked List Initially:");
		print(head);

		// Reverse DLL
		head = reverseDLL(head);

		System.out.println("\nDoubly Linked List After Reversing:");
		print(head);
		System.out.println();

		/// optimal
		// Sample array input
		int[] arrI = { 1, 2, 3, 4, 5 };

		// Convert array to DLL
		Node headI = convertArr2DLL(arrI);

		// Print the original DLL
		System.out.println("Original DLL: ");
		print(headI);
		System.out.println();
		// Reverse the DLL
		Node reversed = reverseDLLI(headI);

		// Print the reversed DLL
		System.out.println("Reversed DLL: ");
		print(reversed);

	}

	// brute

	/// Time Complexity : O(2N), During the first traversal, each node's value is
	/// pushed into the stack once, which requires O(N) time. Then, during the
	/// second iteration, the values are popped from the stack and used to update
	/// the nodes.
	///
	/// Space Complexity : O(N), This is because we are using an external stack data
	/// structure. At the end of the first iteration, the stack will hold all N
	/// values of the doubly linked list therefore the space required for stack is
	/// directly proportional to the size of the input doubly linked list.

	private static Node convertArr2DLL(int[] arr) {
		// Create the head node
		Node head = new Node(arr[0]);

		// Initialize previous pointer
		Node prev = head;

		// Traverse remaining array elements
		for (int i = 1; i < arr.length; i++) {
			// Create a new node and link back to previous
			Node temp = new Node(arr[i], null, prev);
			prev.next = temp;
			prev = temp;
		}

		// Return the head of DLL
		return head;
	}

	// Function to print the doubly linked list
	private static void print(Node head) {
		// Traverse the list and print data
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	// Function to reverse the DLL using stack
	static Node reverseDLL(Node head) {
		// If list is empty or has one element, return it
		if (head == null || head.next == null) {
			return head;
		}

		// Stack to store node values
		Stack<Integer> st = new Stack<>();

		// Pointer to traverse list
		Node temp = head;

		// Push all node data to stack
		while (temp != null) {
			st.push(temp.data);
			temp = temp.next;
		}

		// Reset temp to head
		temp = head;

		// Replace node values from stack
		while (temp != null) {
			temp.data = st.pop();
			temp = temp.next;
		}

		// Return updated head
		return head;
	}

	// optimal

	/// Time Complexity : O(N) We only have to traverse the doubly linked list once,
	/// hence our time complexity is O(N).
	///
	/// Space Complexity : O(1), as the reversal is done in place.

	// Function to reverse the doubly linked list
	static Node reverseDLLI(Node head) {

		// Pointer to traverse the list
		Node current = head;

		// Variable to eventually store new head after reversal
		Node last = null;

		// Traverse the entire list
		while (current != null) {

			// Swap next and back pointers of current node
			Node temp = current.next;
			current.next = current.back;
			current.back = temp;

			// Move last pointer to current (this will become new head)
			last = current;

			// Move to next node (originally current.next but now is back due to swap)
			current = temp;
		}

		// Return the new head (was the last node in original list)
		return last;
	}
}
