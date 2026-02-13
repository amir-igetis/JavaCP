package striverAToZ.heaps.mediumProbs;

import java.util.*;

public class ReplaceElementsByItsRankInTheArr {
    public static void main(String[] args) {
        int[] arr = {20, 15, 26, 2, 98, 6};
        List<Integer> result = replaceWithRank(arr);
        System.out.println(Arrays.toString(replaceWithRankI(arr)));
        for (int r : result) {
            System.out.print(r + " ");
        }
    }

    // pq

    static int[] replaceWithRankI(int[] arr) {
        // Create a copy of the original array
        int[] sortedArr = arr.clone();

        // Sort the copied array
        Arrays.sort(sortedArr);

        // Map to store rank of each unique number
        HashMap<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;

        // Assign rank to each unique number
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }

        // Replace elements in original array with their ranks
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }

    // brute force
    static List<Integer> replaceWithRank(int[] arr) {
        List<Integer> rankList = new ArrayList<>();

        // Loop through each element
        for (int i = 0; i < arr.length; i++) {
            // Set to store unique elements smaller than current
            Set<Integer> smaller = new HashSet<>();

            // Compare with all elements
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    smaller.add(arr[j]);
                }
            }

            // Rank = count of unique smaller elements + 1
            int rank = smaller.size() + 1;
            rankList.add(rank);
        }

        return rankList;
    }
}

