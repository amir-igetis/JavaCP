package striverAToZ.heaps.hardProbs;

import java.util.PriorityQueue;

public class ConnectNRopesWithMinCost {
    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println(minCost(ropes));
    }

    //    greedy + minHeap
    static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : ropes)
            minHeap.add(rope);

        int totalCost = 0;
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int cost = first + second;
            totalCost += cost;

            minHeap.offer(cost);
        }

        return totalCost;
    }
}
