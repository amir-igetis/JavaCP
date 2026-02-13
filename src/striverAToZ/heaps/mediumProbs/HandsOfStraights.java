package striverAToZ.heaps.mediumProbs;

import java.util.HashMap;
import java.util.TreeMap;

public class HandsOfStraights {
    public static void main(String[] args) {
        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize1 = 3;
        System.out.println(isNStraightHand(hand1, groupSize1)); // true

        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;
        System.out.println(isNStraightHand(hand2, groupSize2));
    }

    static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0)
            return false;
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int card : hand)
            freq.put(card, freq.getOrDefault(card, 0) + 1);

        while (!freq.isEmpty()) {
            int start = freq.firstKey();
            int count = freq.get(start);
            for (int i = 0; i < groupSize; i++) {
                int card = start + i;
                if (!freq.containsKey(card) || freq.get(card) < count)
                    return false;
                if (freq.get(card) == count)
                    freq.remove(card);
                else freq.put(card, freq.get(card) - count);
            }
        }
        return true;
    }
}
