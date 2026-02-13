package striverAToZ.greedyAlgo.mediumHard;

import java.util.Arrays;

public class JobSeqProb {
    public static void main(String[] args) {
        int n = 4;

        // Define the edges (source, destination, weight)
        Job[] arr = new Job[]{
                new Job() {{
                    id = 1;
                    dead = 4;
                    profit = 20;
                }},
                new Job() {{
                    id = 2;
                    dead = 1;
                    profit = 10;
                }},
                new Job() {{
                    id = 3;
                    dead = 2;
                    profit = 40;
                }},
                new Job() {{
                    id = 4;
                    dead = 2;
                    profit = 30;
                }}
        };

        // Call the JobScheduling function
        Pair<Integer, Integer> ans = JobScheduling(arr, n);

        // Output the result
        System.out.println(ans.a + " " + ans.b);
    }

    static Pair<Integer, Integer> JobScheduling(Job[] arr, int n) {
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);
        int maxi = arr[0].dead;
        for (int i = 1; i < n; i++) {
            maxi = Math.max(maxi, arr[i].dead);
        }
        int[] slot = new int[maxi + 1];
        Arrays.fill(slot, -1);
        int countJobs = 0, jobProfit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].dead; j > 0; j--) {
                if (slot[j] == -1) {
                    slot[j] = i;
                    countJobs++;
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }
        return new Pair<>(countJobs, jobProfit);
    }

    private static boolean comparison(Job a, Job b) {
        return a.profit > b.profit;
    }

    private static class Pair<I extends Integer, I1 extends Integer> {
        int a;
        int b;

        Pair(Integer _a, Integer _b) {
            a = _a;
            b = _b;
        }
    }

    private static class Job {
        int id;
        int dead;
        int profit;
    }
}
