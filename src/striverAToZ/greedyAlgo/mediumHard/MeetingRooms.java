package striverAToZ.greedyAlgo.mediumHard;

import java.util.*;

public class MeetingRooms {
    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        List<Integer> ans = maxMeetings(start, end);
        for (int i : ans) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    static List<Integer> maxMeetings(int[] start, int[] end) {
        int n = start.length;
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            arr.add(new int[]{end[i], start[i], i + 1});

//        Collections.sort(arr, (a, b) -> (a[1] - b[1]));
        arr.sort(Comparator.comparingInt(a -> a[0]));

        List<Integer> res = new ArrayList<>();
        int lastEnd = -1;
        for (int[] m : arr) {
            if (m[1] > lastEnd) {
                res.add(m[2]);
                lastEnd = m[0];
            }
        }
        return res;
    }
}
