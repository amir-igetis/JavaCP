package striverAToZ.heaps.mediumProbs;

import java.util.*;

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        System.out.println(leastInterval(tasks, n));
    }

    static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> mp = new HashMap<>();
        for (char task : tasks)
            mp.put(task, mp.getOrDefault(task, 0) + 1);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : mp.values())
            maxHeap.add(count);

        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cycle = n + 1, i = 0;
            while (i < cycle && !maxHeap.isEmpty()) {
                int count = maxHeap.poll();
                count--;
                if (count > 0) {
                    temp.add(count);
                }
                time++;
                i++;
            }
            for (int rem : temp)
                maxHeap.add(rem);
            if (!maxHeap.isEmpty())
                time += (cycle - i);
        }
        return time;
    }
}
