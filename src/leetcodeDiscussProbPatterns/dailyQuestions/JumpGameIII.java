package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class JumpGameIII {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        System.out.println(canReach(arr, start));
    }

    static boolean canReach(int[] arr, int start) {
        boolean[] vis = new boolean[arr.length];
        Arrays.fill(vis, false);
        return dfs(arr, start, vis);
    }

    private static boolean dfs(int[] arr, int idx, boolean[] vis) {
        int n = arr.length;
        if (idx < 0 || idx >= n || vis[idx])
            return false;

        if (arr[idx] == 0)
            return true;

        vis[idx] = true;
        return dfs(arr, idx + arr[idx], vis) ||
                dfs(arr, idx - arr[idx], vis);
    }
}
