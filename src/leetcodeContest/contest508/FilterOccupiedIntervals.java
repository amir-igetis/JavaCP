package leetcodeContest.contest508;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterOccupiedIntervals {
    public static void main(String[] args) {
        int[][] occupiedIntervals = {
                {2, 6}, {4, 8}, {10, 10}, {10, 12}, {14, 16}
        };
        int freeStart = 7, freeEnd = 11;
        List<List<Integer>>
                ans = filterOccupiedIntervals(occupiedIntervals, freeStart, freeEnd);
        for (List<Integer> i : ans) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();

    }

    static List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        List<List<Integer>> ans = new ArrayList<>();
        if (occupiedIntervals == null || occupiedIntervals.length == 0)
            return ans;

        Arrays.sort(occupiedIntervals, (a, b) -> a[0] - b[0]);
        List<int[]> merge = new ArrayList<>();
        merge.add(occupiedIntervals[0]);
        for (int i = 1; i < occupiedIntervals.length; i++) {
            int[] prev = merge.get(merge.size() - 1);
            int[] curr = occupiedIntervals[i];
            if (curr[0] <= prev[1] + 1)
                prev[1] = Math.max(prev[1], curr[1]);
            else merge.add(curr);
        }

        for (int[] i : merge) {
            int s = i[0], e = i[1];
            if (e < freeStart)
                ans.add(Arrays.asList(s, e));
            else if (s > freeEnd)
                ans.add(Arrays.asList(s, e));
            else {
                if (s < freeStart)
                    ans.add(Arrays.asList(s, freeStart - 1));
                if (e > freeEnd)
                    ans.add(Arrays.asList(freeEnd + 1, e));
            }
        }

        return ans;
    }

}
