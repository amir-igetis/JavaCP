package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class DesignTaskManager {
    private PriorityQueue<int[]> pq;
    private Map<Integer, Integer> taskPriority;
    private Map<Integer, Integer> taskOwner;

    public DesignTaskManager(List<List<Integer>> tasks) {
        pq = new PriorityQueue<>((a, b) -> {
            if (b[0] != a[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
        taskPriority = new HashMap<>();
        taskOwner = new HashMap<>();
        for (List<Integer> t : tasks) add(t.get(0), t.get(1), t.get(2));
    }

    public void add(int userId, int taskId, int priority) {
        pq.add(new int[]{priority, taskId});
        taskPriority.put(taskId, priority);
        taskOwner.put(taskId, userId);
    }

    public void edit(int taskId, int newPriority) {
        pq.add(new int[]{newPriority, taskId});
        taskPriority.put(taskId, newPriority);
    }

    public void rmv(int taskId) {
        taskPriority.put(taskId, -1);
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int p = t[0], id = t[1];
            if (taskPriority.getOrDefault(id, -2) == p) {
                taskPriority.put(id, -1);
                return taskOwner.getOrDefault(id, -1);
            }
        }
        return -1;
    }
}


/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */

//class Main {
//    public static void main(String[] args) {
//        List<int[]> tasks = Arrays.asList(
//                new int[]{1, 101, 10},
//                new int[]{2, 102, 20},
//                new int[]{3, 103, 15}
//        );
//
//        TaskManager taskManager = new TaskManager(tasks);
//
//        taskManager.add(4, 104, 5);
//        taskManager.edit(102, 8);
//        System.out.println(taskManager.execTop()); // 3 (executes 103)
//        taskManager.rmv(101);
//        taskManager.add(5, 105, 15);
//        System.out.println(taskManager.execTop()); // 5 (executes 105)
//    }
//}
