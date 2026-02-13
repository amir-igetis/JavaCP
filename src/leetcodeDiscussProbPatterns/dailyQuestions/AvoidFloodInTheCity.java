package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class AvoidFloodInTheCity {
    public static void main(String[] args) {
        int[] rains = {1, 2, 0, 0, 2, 1};
        System.out.println(Arrays.toString(avoidFloodI(rains)));
    }

    static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];

        Map<Integer, Integer> lakeToDay = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                ans[i] = 1;
            } else {
                int lake = rains[i];
                ans[i] = -1;
                if (lakeToDay.containsKey(lake)) {
                    Integer dryDay = dryDays.higher(lakeToDay.get(lake));
                    if (dryDay == null) {
                        return new int[0];
                    }
                    ans[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                lakeToDay.put(lake, i);
            }
        }
        return ans;
    }

    //    using priority queue
    static int[] avoidFloodI(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        Map<Integer, Queue<Integer>> nextRain = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0)
                nextRain.computeIfAbsent(rains[i], k -> new LinkedList<>()).offer(i);
        }
        Set<Integer> full = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                int lake = rains[i];
                nextRain.get(lake).poll();
                if (full.contains(lake))
                    return new int[0];

                full.add(lake);
                ans[i] = -1;

                if (!nextRain.get(lake).isEmpty())
                    pq.offer(new int[]{nextRain.get(lake).peek(), lake});
            } else {
                if (!pq.isEmpty()) {
                    int[] top = pq.poll();
                    int lake = top[1];
                    ans[i] = lake;
                    full.remove(lake);
                }
            }
        }
        return ans;
    }

}
