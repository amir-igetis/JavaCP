package LeetcodeInterview.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWitSmallestSums {
    public static void main(String[] args) {
//
        int[] nums1 = {1, 7, 11}, nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> ans = kSmallestPairs(nums1, nums2, k);
        for (List<Integer> i : ans) {
            for (int j : i)
                System.out.print(j + ", ");
            System.out.println();
        }
    }

    static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return res;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            res.add(Arrays.asList(top[0], top[1]));

            int i = top[2];
            if (i + 1 < nums2.length)
                minHeap.offer(new int[]{top[0], nums2[i + 1], i + 1});
        }

        return res;
    }
}
