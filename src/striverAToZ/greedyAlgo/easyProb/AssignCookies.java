package striverAToZ.greedyAlgo.easyProb;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        int N = 3;
        int M = 2;
        int[] greed = {1, 2, 3};
        int[] sz = {1, 1};
        System.out.println(maxChildren(N, M, greed, sz));
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/assign-cookies/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=assign-cookies
    static int maxChildren(int N, int M, int[] greed, int[] sz) {
        int count = 0;
        Arrays.sort(greed);
        Arrays.sort(sz);
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (greed[i] <= sz[j]) {
                count++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return count;
    }

    // soln for https://leetcode.com/problems/assign-cookies/
    static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        for (int j = 0; i < g.length && j < s.length; ++j)
            if (g[i] <= s[j])
                ++i;

        return i;
    }
}
