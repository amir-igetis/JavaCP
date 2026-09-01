package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class FindMinAndMaxNumOfNodesBetweenCritPoints {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(head)));
    }

    // one pass

    /// Let n be the the length of the linked list.
    ///
    /// Time complexity: O(n)
    ///
    /// The algorithm traverses the list only once, making the time complexity O(n).
    ///
    /// Space complexity: O(1)
    ///
    /// The algorithm has a constant space complexity since it does not utilize any additional data structures.
    static int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = {-1, -1};

        // Initialize minimum distance to the maximum possible value
        int minDistance = Integer.MAX_VALUE;

        // Pointers to track the previous node, current node, and indices
        ListNode previousNode = head;
        ListNode currentNode = head.next;
        int currentIndex = 1;
        int previousCriticalIndex = 0;
        int firstCriticalIndex = 0;

        while (currentNode.next != null) {
            // Check if the current node is a local maxima or minima
            if ((currentNode.val < previousNode.val && currentNode.val < currentNode.next.val)
                    || (currentNode.val > previousNode.val && currentNode.val > currentNode.next.val)) {
                // If this is the first critical point found
                if (previousCriticalIndex == 0) {
                    previousCriticalIndex = currentIndex;
                    firstCriticalIndex = currentIndex;
                } else {
                    // Calculate the minimum distance between critical points
                    minDistance = Math.min(minDistance, currentIndex - previousCriticalIndex);
                    previousCriticalIndex = currentIndex;
                }
            }

            // Move to the next node and update indices
            currentIndex++;
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // If at least two critical points were found
        if (minDistance != Integer.MAX_VALUE) {
            int maxDistance = previousCriticalIndex - firstCriticalIndex;
            result = new int[]{minDistance, maxDistance};
        }

        return result;

    }
}
