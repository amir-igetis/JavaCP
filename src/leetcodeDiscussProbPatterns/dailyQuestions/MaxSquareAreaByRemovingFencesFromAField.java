package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MaxSquareAreaByRemovingFencesFromAField {
    public static void main(String[] args) {
        int m = 4, n = 3;
        int[] hFences = {2, 3}, vFences = {2};
        System.out.println(maximizeSquareArea(m, n, hFences, vFences));
    }

    //    enumeration
    static int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int MOD = (int) 1e9 + 7;
        Set<Integer> hEdges = getEdges(hFences, m);
        Set<Integer> vEdges = getEdges(vFences, n);
        long res = 0;
        for (int e : hEdges) {
            if (vEdges.contains(e))
                res = Math.max(res, e);
        }
        if (res == 0)
            return -1;
        else return (int) ((res * res) % MOD);
    }

    private static Set<Integer> getEdges(int[] fences, int border) {
        Set<Integer> st = new HashSet<>();
        List<Integer> arr = new ArrayList<>();
        for (int fence : fences)
            arr.add(fence);

        arr.add(1);
        arr.add(border);
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++)
                st.add(arr.get(j) - arr.get(i));

        }
        return st;
    }

}
