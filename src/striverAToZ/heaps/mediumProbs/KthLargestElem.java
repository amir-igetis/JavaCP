package striverAToZ.heaps.mediumProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElem {
    public static void main(String[] args) {
        int n = 5;
        int[] arr = {12, 5, 787, 1, 23};
        int k = 2;
        System.out.println(kLargest(arr, n, k));
        System.out.println(findKthLargest(arr, k));
    }

    // soln for https://practice.geeksforgeeks.org/problems/k-largest-elements3736/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=k-largest-elements
    static List<Integer> kLargest(int arr[], int n, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : arr)
            q.add(x);

        for (int i = 0; i < k; i++) {
            res.add(q.remove());
        }
        return res;
    }

    // soln for https://leetcode.com/problems/kth-largest-element-in-an-array/description/
    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            q.add(nums[i]);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
    }
}
