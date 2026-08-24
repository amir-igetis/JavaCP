package striverAToZ.linkedList.mediumProbs;

public class AddTwoNumbersRepresentedAsLL {

    /// Question 14
    ///
    /// Problem Statement: Add two numbers represented as Linked Lists.
    public static void main(String[] args) {
        int[] num1 = {2, 4, 3}; // represents 342
        int[] num2 = {5, 6, 4}; // represents 465
        Node l1 = createList(num1);
        Node l2 = createList(num2);

        Node result = addTwoNumbers(l1, l2);
        printList(result); // Output: 7 -> 0 -> 8
    }

    /// Time Complexity: O(max(m,n)). Assume that m and n represent the length of l1 and l2 respectively, the algorithm above iterates at most max(m,n) times.
    ///
    /// Space Complexity: O(max(m,n)). The length of the new list is at most max(m,n)+1.

    static Node addTwoNumbers(Node l1, Node l2) {
        //Initialize a dummy node as a new node
        Node dummy = new Node();
        Node temp = dummy;
        int carry = 0;
        //Iterate till the end of both the lists
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;

            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            //Repeat the same process for l2 as l1
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            //Carry gets added to sum in the node (carry=sum/10)
            sum += carry;
            carry = sum / 10;

            Node node = new Node(sum % 10);
            temp.next = node;
            temp = temp.next;
        }

        return dummy.next;
    }

    private static Node createList(int[] arr) {
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
