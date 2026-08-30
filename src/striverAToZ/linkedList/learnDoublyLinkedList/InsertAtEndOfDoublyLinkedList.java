package striverAToZ.linkedList.learnDoublyLinkedList;

public class InsertAtEndOfDoublyLinkedList {

	/// Problem Statement: Given a doubly linked list, and a value ‘k’, insert a
	/// node having value ‘k’ at the end of the doubly linked list.
	///

	public static void main(String[] args) {
		// Initialize an array of integers
		int[] arr = { 12, 5, 8, 7, 4 };

		// Convert the array to a doubly linked list
		Node head = convertArr2DLL(arr);

		// Print the initially created doubly linked list
		System.out.println("Doubly Linked List Initially: ");
		print(head);

		// Insert a node with value 10 at the end of the doubly linked list
		System.out.println("\nDoubly Linked List After Inserting at the tail with value 10: ");
		head = insertAtTail(head, 10);
		print(head);
	}

	/// Time Complexity: O(n), where n is the number of nodes in the doubly linked
	/// list. This is because we traverse the list to find the tail node before
	/// inserting the new node.
	///
	/// Space Complexity: O(1), as we are only using a constant amount of extra
	/// space for the new node, regardless of the size of the list.

	static Node convertArr2DLL(int[] arr) {
		// Create the head node with the first element of the array
		Node head = new Node(arr[0]);
		Node prev = head; // Initialize 'prev' to the head node

		// Traverse the array to create the doubly linked list
		for (int i = 1; i < arr.length; i++) {
			Node temp = new Node(arr[i], null, prev); // Create a new node
			prev.next = temp; // Set 'next' of the previous node to the new node
			prev = temp; // Move 'prev' to the new node
		}

		return head; // Return the head of the doubly linked list
	}

	// Function to print the elements of the doubly linked list
	private static void print(Node head) {
		// Traverse through the list and print each node's data
		while (head != null) {
			System.out.print(head.data + " "); // Print the data of the current node
			head = head.next; // Move to the next node
		}
		System.out.println(); // New line after printing the list
	}

	// Function to insert a new node at the tail of the doubly linked list
	private static Node insertAtTail(Node head, int k) {
		// Create a new node with data 'k'
		Node newNode = new Node(k);

		// If the list is empty, return the new node as the head
		if (head == null) {
			return newNode;
		}

		// Traverse to the last node of the doubly linked list
		Node tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}

		// Connect the new node to the last node
		tail.next = newNode;
		newNode.back = tail; // Set the 'back' pointer of the new node to the previous node
		return head; // Return the head of the modified list
	}
}