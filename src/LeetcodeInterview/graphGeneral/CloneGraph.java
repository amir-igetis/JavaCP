package LeetcodeInterview.graphGeneral;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    public static void main(String[] args) {
//

    }

    //    DFS
    private static Map<Node, Node> mp = new HashMap<>();

    static Node cloneGraph(Node node) {
        if (node == null)
            return null;

        return dfs(node);
    }

    private static Node dfs(Node node) {
        if (mp.containsKey(node))
            return mp.get(node);

        Node clone = new Node(node.val);
        mp.put(node, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor));
        }
        return clone;
    }

    //    BFS
    static Node cloneGraphI(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> mp = new HashMap<>();
        Queue<Node> q = new LinkedList<>();

        Node clone = new Node(node.val);
        mp.put(node, clone);
        q.add(node);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            for (Node neighbour : curr.neighbors) {
                if (!mp.containsKey(neighbour)) {
                    mp.put(neighbour, new Node(neighbour.val));
                    q.add(neighbour);
                }
                mp.get(curr).neighbors.add(mp.get(neighbour));
            }
        }
        return clone;
    }

}
