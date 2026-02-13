package striverAToZ.greedyAlgo.mediumHard;

import java.util.Arrays;

public class MinNumOfPlatformsRequiredForRailway {
    public static void main(String[] args) {
        int[] arr = {900, 945, 955, 1100, 1500, 1800};
        int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
        int n = 6;
        System.out.println(countPlatform(n, arr, dep));
    }

    // brute tc O(n^2) & sc O(1)
    static int countPlatform(int n, int[] arr, int[] dep) {
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] >= arr[j] && arr[i] <= dep[j]) ||
                        (arr[j] >= arr[i] && arr[j] <= dep[i]))
                    count++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    // optimal tc O(n logn) & sc O(1);
    static int countPlatformI(int n, int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 1;
        int res = 1;
        int i = 1, j = 0;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else {
                platforms--;
                j++;
            }
            res = Math.max(res, platforms);
        }
        return res;
    }
}
