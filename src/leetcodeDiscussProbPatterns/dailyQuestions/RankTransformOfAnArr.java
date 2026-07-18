package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class RankTransformOfAnArr {
    public static void main(String[] args) {
        int[] nums = {40, 10, 20, 30};
        System.out.println(Arrays.toString(arrayRankTransform(nums)));
        System.out.println(Arrays.toString(arrayRankTransformI(nums)));
        System.out.println(Arrays.toString(arrayRankTransformII(nums)));

    }

    // sorting and hash map tc O(N⋅logN) sc O(n + s)
    static int[] arrayRankTransform(int[] arr) {
        // Store the rank for each number in arr
        HashMap<Integer, Integer> numToRank = new HashMap<>();
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        int rank = 1;
        for (int i = 0; i < sortedArr.length; i++) {
            if (i > 0 && sortedArr[i] > sortedArr[i - 1]) {
                rank++;
            }
            numToRank.put(sortedArr[i], rank);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numToRank.get(arr[i]);
        }
        return arr;

    }

    // duplicating with set tc O(n logn) sc O(n)
    static int[] arrayRankTransformI(int[] arr) {
        // Store the rank for each number in arr
        HashMap<Integer, Integer> numToRank = new HashMap<>();
        // Deduplicate and sort arr
        TreeSet<Integer> nums = new TreeSet<>();
        for (int num : arr) nums.add(num);
        int rank = 1;
        for (int num : nums) {
            numToRank.put(num, rank);
            rank++;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numToRank.get(arr[i]);
        }
        return arr;
    }

    // Ordered Map tc O(n logn) sc O(n)
    static int[] arrayRankTransformII(int[] arr) {
        // Store the rank for each number in arr
        TreeMap<Integer, List<Integer>> numToIndices = new TreeMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!numToIndices.containsKey(arr[i])) {
                numToIndices.put(arr[i], new ArrayList<>());
            }
            numToIndices.get(arr[i]).add(i);
        }
        int rank = 1;
        for (int num : numToIndices.keySet()) {
            for (int index : numToIndices.get(num)) {
                arr[index] = rank;
            }
            rank++;
        }
        return arr;
    }
}
