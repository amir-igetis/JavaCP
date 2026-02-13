package striverAToZ.heaps.mediumProbs;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElem {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int l = 0;
        int r = arr.length - 2;
        int k = 5;
        System.out.println(kthSmallest(arr, l, r, k));
    }

    static int kthSmallest(int[] arr, int l, int r, int k) {
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = l; i <= r; i++) {
            q.add(arr[i]);
            while (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
    }
}
