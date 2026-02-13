package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class ImplementRouter {

    private final int size;
    private final Map<Integer, List<Integer>> counts;
    private final Map<Long, int[]> packets;
    private final Queue<Long> queue;

    public ImplementRouter(final int memoryLimit) {
        this.size = memoryLimit;
        this.packets = new HashMap<>();
        this.counts = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    public boolean addPacket(final int source, final int destination, final int timestamp) {
        final long key = encode(source, destination, timestamp);

        if (packets.containsKey(key))
            return false;

        if (packets.size() >= size)
            forwardPacket();

        packets.put(key, new int[]{source, destination, timestamp});
        queue.offer(key);

        counts.putIfAbsent(destination, new ArrayList<>());
        counts.get(destination).add(timestamp);

        return true;
    }

    public int[] forwardPacket() {
        if (packets.isEmpty())
            return new int[]{};

        final long key = queue.poll();
        final int[] packet = packets.remove(key);

        if (packet == null)
            return new int[]{};

        final int destination = packet[1];
        final List<Integer> list = counts.get(destination);

        list.remove(0);

        return packet;
    }

    public int getCount(final int destination, final int startTime, final int endTime) {
        final List<Integer> list = counts.get(destination);
        if (list == null || list.isEmpty())
            return 0;

        final int left = lowerBound(list, startTime);
        final int right = upperBound(list, endTime);

        return right - left;
    }

    private long encode(final int source, final int destination, final int timestamp) {
        return ((long) source << 40) | ((long) destination << 20) | timestamp;
    }

    private int lowerBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();

        while (low < high) {
            final int mid = (low + high) >>> 1;
            if (list.get(mid) < target) low = mid + 1;
            else high = mid;
        }

        return low;
    }

    private int upperBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();

        while (low < high) {
            final int mid = (low + high) >>> 1;

            if (list.get(mid) <= target)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
}

//public class Main {
//    public static void main(String[] args) {
//        ImplementRouter router = new ImplementRouter(3);
//        System.out.println(router.addPacket(1, 4, 90));   // true
//        System.out.println(router.addPacket(2, 5, 90));   // true
//        System.out.println(router.addPacket(1, 4, 90));   // false
//        System.out.println(router.addPacket(3, 5, 95));   // true
//        System.out.println(router.addPacket(4, 5, 105));  // true (evicts [1,4,90])
//        System.out.println(Arrays.toString(router.forwardPacket())); // [2,5,90]
//        System.out.println(router.addPacket(5, 2, 110));  // true
//        System.out.println(router.getCount(5, 100, 110)); // 1
//    }
//}

