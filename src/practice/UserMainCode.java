package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class UserMainCode {

    public static void main(String[] args) {
        int[] input1 = {12, 2, 36, 10, 217, 36, 5, 36, 15, 10};
        int input2 = 10;
        System.out.println(findPassword(input1, input2));
    }

    public static int findPassword(int[] input1, int input2) {

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : input1) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> {
            if (b.getValue().equals(a.getValue())) {
                return b.getKey() - a.getKey();
            }
            return b.getValue() - a.getValue();
        });

        maxHeap.addAll(frequencyMap.entrySet());

        int part1 = maxHeap.poll().getKey();
        int part2 = maxHeap.poll().getKey();
        String passwordString = String.valueOf(part1) + String.valueOf(part2);
        return Integer.parseInt(passwordString);
//        throw new UnsupportedOperationException("findPassword(int[] input1, int input2)");
    }
}
