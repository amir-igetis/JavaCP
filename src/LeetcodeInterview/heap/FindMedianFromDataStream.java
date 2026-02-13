package LeetcodeInterview.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
//
    }

    PriorityQueue<Integer> pq1;
    PriorityQueue<Integer> pq2;

    public FindMedianFromDataStream() {
        pq2 = new PriorityQueue<>();
        pq1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }

    public void addNum(int num) {
        pq1.offer(num);
        pq2.offer(pq1.poll());
        if (pq1.size() < pq2.size())
            pq1.offer(pq2.poll());
    }

    public double findMedian() {
        if (pq1.size() == pq2.size()) {
            return (double) (pq1.peek() + pq2.peek()) / 2;
        } else {
            return (double) pq1.peek();
        }
    }
}
