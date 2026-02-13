package striverAToZ.heaps.hardProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinderI mf = new MedianFinderI();

        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);

        System.out.println(mf.findMedian()); // Output: 2.0
    }

    // tc and sc O(NlogN) & O(N)
    private static class MedianFinder {
        // List to store all the numbers
        private List<Integer> nums;

        // Constructor to initialize the object
        public MedianFinder() {
            nums = new ArrayList<>();
        }

        // Function to add a number to the list
        public void addNum(int num) {
            // Add number to the list
            nums.add(num);
        }

        // Function to return the median
        public double findMedian() {
            // Sort the list
            Collections.sort(nums);

            // Get size
            int n = nums.size();

            // If odd, return middle
            if (n % 2 == 1) {
                return nums.get(n / 2);
            }

            // If even, return average of two middle
            return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;
        }
    }

    // optimized approach tc & sc O(NlogN + M) & O(N)

    private static class MedianFinderI {
        // Max-heap for smaller half
        private PriorityQueue<Integer> maxHeap;

        // Min-heap for larger half
        private PriorityQueue<Integer> minHeap;

        // Constructor
        public MedianFinderI() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        // Function to add a number to the data structure
        public void addNum(int num) {
            // Step 1: Add to maxHeap
            maxHeap.offer(num);

            // Step 2: Move the largest of maxHeap to minHeap
            minHeap.offer(maxHeap.poll());

            // Step 3: Balance the heaps if needed
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        // Function to return the current median
        public double findMedian() {
            // If equal sizes, return average
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }

            // Otherwise, return top of maxHeap
            return maxHeap.peek();
        }
    }

}
