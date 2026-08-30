package striverAToZ.linkedList.learnOneDLinkedList;

public class SearchAnElemInLinkedList {

	/// Problem Statement: Given the head of a linked list and an integer value,
	/// find out whether the integer is present in the linked list or not. Return
	/// true if it is present, or else return false.
	///
	public static void main(String[] args) {
		// Creating linked list: 10 -> 20 -> 30
		Node head = new Node(10);
		head.next = new Node(20);
		head.next.next = new Node(30);
		// Search for value
		if (searchValue(head, 20))
			System.out.println("Found");
		else
			System.out.println("Not Found");
	}

	/// Time Complexity: O(N), we traverse the entire linked list once in worst case
	/// to search for the required value.
	/// Space Complexity: O(1) , we use a constant amount of additional space,
	/// regardless of the linked list's length to search for an element.

	static boolean searchValue(Node head, int key) {
		// Pointer to traverse the list
		Node current = head;

		// Traverse until end
		while (current != null) {
			// Check if current node matches key
			if (current.data == key) {
				// Return true if found
				return true;
			}
			// Move to next node
			current = current.next;
		}

		// Return false if not found
		return false;
	}
}
