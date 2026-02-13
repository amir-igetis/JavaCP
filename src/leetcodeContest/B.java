package leetcodeContest;

import java.util.*;

public class B {
    private int memoryLimit;
    private Queue<Packet> q;
    private Set<String> pktSet;
    private Map<Integer, TreeMap<Integer, Integer>> destMap;

    public B(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.q = new LinkedList<>();
        this.pktSet = new HashSet<>();
        this.destMap = new HashMap<>();
    }

    boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "-" + destination + "-" + timestamp;
        if (pktSet.contains(key)) return false;
        if (q.size() == memoryLimit) {
            Packet removed = q.poll();
            String removedKey = removed.source + "-" + removed.destination + "-" + removed.timestamp;
            pktSet.remove(removedKey);
            TreeMap<Integer, Integer> map = destMap.get(removed.destination);
            map.put(removed.timestamp, map.get(removed.timestamp) - 1);
            if (map.get(removed.timestamp) == 0) {
                map.remove(removed.timestamp);
            }
        }

        Packet newPacket = new Packet(source, destination, timestamp);
        q.offer(newPacket);
        pktSet.add(key);


        destMap.putIfAbsent(destination, new TreeMap<>());
        TreeMap<Integer, Integer> timeMap = destMap.get(destination);
        timeMap.put(timestamp, timeMap.getOrDefault(timestamp, 0) + 1);

        return true;
    }

    public int[] forwardPacket() {
        if (q.isEmpty()) return new int[0];

        Packet p = q.poll();
        String key = p.source + "-" + p.destination + "-" + p.timestamp;
        pktSet.remove(key);

        TreeMap<Integer, Integer> map = destMap.get(p.destination);
        map.put(p.timestamp, map.get(p.timestamp) - 1);
        if (map.get(p.timestamp) == 0) {
            map.remove(p.timestamp);
        }

        return new int[]{p.source, p.destination, p.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) return 0;

        TreeMap<Integer, Integer> map = destMap.get(destination);
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : map.subMap(startTime, true, endTime, true).entrySet()) {
            count += entry.getValue();
        }

        return count;
    }

    private static class Packet {
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }
    }

}