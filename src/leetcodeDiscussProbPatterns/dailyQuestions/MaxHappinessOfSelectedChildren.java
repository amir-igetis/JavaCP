package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MaxHappinessOfSelectedChildren {
    public static void main(String[] args) {
        int[] happiness = {1, 2, 3};
        int k = 2;
        System.out.println(maximumHappinessSum(happiness, k));
    }

    //    sort + greedy
    static long maximumHappinessSum(int[] happiness, int k) {
        int size = happiness.length;
        Integer[] happArr = new Integer[size];
        for (int i = 0; i < size; i++) {
            happArr[i] = happiness[i];
        }

        Arrays.sort(happArr, Collections.reverseOrder());
        long totalHapp = 0, turns = 0;
        for (int i = 0; i < k; i++) {
            totalHapp += Math.max(happArr[i] - turns, 0);
            turns++;
        }

        return totalHapp;
    }

    //    maxHeap , pq + greedy
    static long maximumHappinessSumI(int[] happiness, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int h : happiness)
            pq.add(h);
        long totalHappinessSum = 0, turns = 0;
        for (int i = 0; i < k; i++) {
            totalHappinessSum += Math.max(pq.poll() - turns, 0);
            turns++;
        }
        return totalHappinessSum;
    }

    // my code
    public long maximumHappinessSumII(int[] happiness, int k) {
        Arrays.sort(happiness);
        Stack<Integer> stack = new Stack<>();
        for (int num : happiness) {
            stack.add(num);
        }
        long max = 0;
        int i = 0;
        while (k-- != 0 && !stack.isEmpty()) {
            int curr = stack.pop();
            if ((curr - i) > 0) {
                max += (curr - i);
            }
            i++;
        }
        return max;
    }

}
