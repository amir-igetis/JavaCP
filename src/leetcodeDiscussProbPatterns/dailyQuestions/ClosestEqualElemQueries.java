package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ClosestEqualElemQueries {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 4, 1, 3, 2}, queries = {0, 3, 5};
        List<Integer> ans = solveQueriesI(nums, queries);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    //    hash table + binary tree tc O(m + m log n) sc O(n)
    static List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        HashMap<Integer, ArrayList<Integer>> numsPos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            numsPos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (ArrayList<Integer> pos : numsPos.values()) {
            int x = pos.get(0);
            int last = pos.get(pos.size() - 1);
            pos.add(0, last - n);
            pos.add(x + n);
        }
        List<Integer> result = new ArrayList<>();
        for (int q : queries) {
            int x = nums[q];
            ArrayList<Integer> posList = numsPos.get(x);

            if (posList.size() == 3) {
                result.add(-1);
                continue;
            }

            int idx = Collections.binarySearch(posList, q);
            if (idx < 0) idx = -idx - 1;

            int dist = Math.min(
                    posList.get(idx + 1) - posList.get(idx),
                    posList.get(idx) - posList.get(idx - 1)
            );
            result.add(dist);
        }
        return result;
    }

    //    Preprocessing Nearest Left and Right Positions + Hash Table tc O(n + m) sc O(n)
    static List<Integer> solveQueriesI(int[] nums, int[] queries) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        HashMap<Integer, Integer> pos = new HashMap<>();
        for (int i = -n; i < n; i++) {
            if (i >= 0) {
                left[i] = pos.getOrDefault(nums[i], i - n);
            }
            pos.put(nums[((i % n) + n) % n], i);
        }

        pos.clear();
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (i < n) {
                right[i] = pos.getOrDefault(nums[i], i + n);
            }
            pos.put(nums[i % n], i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i];
            if (x - left[x] == n) {
                result.add(-1);
            } else {
                result.add(Math.min(x - left[x], right[x] - x));
            }
        }
        return result;
    }


}
