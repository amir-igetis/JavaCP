package LeetcodeInterview.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class IPO {
    public static void main(String[] args) {
//
        int k = 2, w = 0;
        int[] profits = {1, 2, 3}, capital = {0, 1, 1};
        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }

    static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];

        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{capital[i], profits[i]};

        }
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;
        while (k-- > 0) {
            while (i < n && projects[i][0] <= w) {
                maxHeap.offer(projects[i][1]);
                i++;
            }
            if (maxHeap.isEmpty())
                break;
            w += maxHeap.poll();
        }
        return w;
    }

}
