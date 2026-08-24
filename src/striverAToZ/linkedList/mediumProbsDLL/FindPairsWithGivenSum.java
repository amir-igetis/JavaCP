package striverAToZ.linkedList.mediumProbsDLL;

import java.util.ArrayList;
import java.util.HashSet;

public class FindPairsWithGivenSum {

    public static void main(String[] args) {

    }

    // soln for https://practice.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-pairs-with-given-sum-in-doubly-linked-list
    static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        HashSet<Integer> map = new HashSet<Integer>();
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            map.add(temp.data);
            temp = temp.next;
        }

        while (head != null) {
            int x = target - head.data;
            if (map.contains(x) && x != head.data) {
                map.remove(head.data);
                ArrayList<Integer> arr1 = new ArrayList<>();
                arr1.add(head.data);
                arr1.add(x);
                arr.add(arr1);
                map.remove(x);
            }
            head = head.next;
        }
        return arr;
    } // tc O(n) & O(1)

}
