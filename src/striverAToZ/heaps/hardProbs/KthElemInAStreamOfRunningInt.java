package striverAToZ.heaps.hardProbs;

import java.util.PriorityQueue;

public class KthElemInAStreamOfRunningInt {
    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        Solution kthLargest = new Solution(3, nums);
        System.out.println(kthLargest.add(3));   // Output: 4
        System.out.println(kthLargest.add(5));   // Output: 5
        System.out.println(kthLargest.add(10));  // Output: 5
        System.out.println(kthLargest.add(9));   // Output: 8
        System.out.println(kthLargest.add(4));   // Output: 8
    }

    private static class Solution {
        // Min-heap to store top k elements
        PriorityQueue<Integer> minHeap;
        int size;

        // Constructor to initialize heap with initial elements
        public Solution(int k, int[] nums) {
            size = k;
            minHeap = new PriorityQueue<>();

            // Add numbers to heap
            for (int num : nums) {
                minHeap.offer(num);

                // If heap exceeds k, remove the smallest
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        // Adds new value to stream and returns k-th largest
        public int add(int val) {
            // Add new element
            minHeap.offer(val);

            // Maintain size of k
            if (minHeap.size() > size) {
                minHeap.poll();
            }

            // Return root (k-th largest)
            return minHeap.peek();

//            ctrl + shift + f
        }
    }
}
