package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinInitialEnergyToFinishTasks {
    public static void main(String[] args) {
        int[][] tasks = {{1, 2}, {2, 4}, {4, 8}};
        System.out.println(minimumEffort(tasks));
    }


    //    greedy (Difference Increasing) tc O(nlog n) and sc O(log n)
    static int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - a[0] - (b[1] - b[0]));
        int ans = 0;
        for (int[] task : tasks) {
            ans = Math.max(ans + task[0], task[1]);
        }
        return ans;
    }

    //    Greedy (Difference Decreases) tc O(nlog n) and sc O(log n)
    static int minimumEffortI(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int ans = 0;
        int remain = 0;
        for (int[] task : tasks) {
            if (remain <= task[1]) {
                ans += task[1] - remain;
            }
            remain = Math.max(task[1] - task[0], remain - task[0]);
        }
        return ans;
    }
}
