package striverAToZ.heaps.hardProbs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KMostFreqElems {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFreq(nums, k)));
    }

    //    using maxHeap
    static int[] topKFreq(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : nums)
            mp.put(num, mp.getOrDefault(num, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(mp.entrySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll().getKey();
        }
        return res;
    }
}
