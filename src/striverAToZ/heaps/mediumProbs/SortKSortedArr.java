package striverAToZ.heaps.mediumProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SortKSortedArr {
    public static void main(String[] args) {
        List<Integer> arr = List.of(6, 5, 3, 2, 8, 10, 9);
        int k = 3;
        List<Integer> ans = sortNearlySortedArrayI(arr, k);
        for (int i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Integer> sortNearlySortedArray(List<Integer> arr, int k) {
        // Sort the entire list
        List<Integer> ans = new ArrayList<>(arr);
        Collections.sort(ans);

        // Return the sorted list
        return ans;
    }

    //    using pq
    static List<Integer> sortNearlySortedArrayI(List<Integer> arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= k && i < arr.size(); i++) {
            pq.add(arr.get(i));
        }

        for (int i = k + 1; i < arr.size(); i++) {
            res.add(pq.poll());
            pq.add(arr.get(i));
        }
        while (!pq.isEmpty())
            res.add(pq.poll());

        return res;
    }
}
