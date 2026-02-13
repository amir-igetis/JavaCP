package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class DeleteColumnsToMakeSortedII {
    public static void main(String[] args) {
        String[] strs = {"ca", "bb", "ac"};
        System.out.println(minDeletionSizeI(strs));
    }

    //    greedy optimized
    static int minDeletionSizeI(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        boolean[] sorted = new boolean[n - 1];
        int deletions = 0;

        for (int col = 0; col < m; col++) {
            boolean deleteCol = false;

            // Check if this column breaks order
            for (int i = 0; i < n - 1; i++) {
                if (!sorted[i] && strs[i].charAt(col) > strs[i + 1].charAt(col)) {
                    deleteCol = true;
                    break;
                }
            }

            if (deleteCol) {
                deletions++;
                continue;
            }

            // Update sorted pairs
            for (int i = 0; i < n - 1; i++) {
                if (!sorted[i] && strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                    sorted[i] = true;
                }
            }
        }

        return deletions;
    }

    //  greedy approach
    static int minDeletionSize(String[] strs) {
        int n = strs.length;
        int w = strs[0].length();
        int ans = 0;

        String[] curr = new String[n];
        for (int j = 0; j < w; j++) {
            String[] curr2 = Arrays.copyOf(curr, n);
            for (int i = 0; i < n; i++)
                curr2[i] += strs[i].charAt(j);

            if (isSorted(curr2))
                curr = curr2;
            else ans++;
        }
        return ans;
    }

    private static boolean isSorted(String[] strs) {
        for (int i = 0; i < strs.length - 1; i++) {
            if (strs[i].compareTo(strs[i + 1]) > 0)
                return false;
        }
        return true;
    }
}
