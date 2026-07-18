package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class CountTheNumberOfCompleteComponents {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}};
        System.out.println(countCompleteComponents(n, edges));
        System.out.println(countCompleteComponentsI(n, edges));
        System.out.println(countCompleteComponentsII(n, edges));
        System.out.println(countCompleteComponentsIII(n, edges));

    }

    // Adjacency List O(n+mlogn) sc O(n+m+S)
    static int countCompleteComponentsIII(int n, int[][] edges) {
        // Adjacency lists for each vertex
        List<Integer>[] graph = new ArrayList[n];
        // Map to store frequency of each unique adjacency list
        Map<List<Integer>, Integer> componentFreq = new HashMap<>();

        // Initialize adjacency lists with self-loops
        for (int vertex = 0; vertex < n; vertex++) {
            graph[vertex] = new ArrayList<>();
            graph[vertex].add(vertex);
        }

        // Build adjacency lists from edges
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // Count frequency of each unique adjacency pattern
        for (int vertex = 0; vertex < n; vertex++) {
            List<Integer> neighbors = graph[vertex];
            Collections.sort(neighbors);
            componentFreq.put(
                    neighbors,
                    componentFreq.getOrDefault(neighbors, 0) + 1
            );
        }

        // Count complete components where size equals frequency
        int completeCount = 0;
        for (Map.Entry<
                List<Integer>,
                Integer
                > entry : componentFreq.entrySet()) {
            if (entry.getKey().size() == entry.getValue()) {
                completeCount++;
            }
        }

        return completeCount;
    }


    // DFS tc & sc O(n + m)
    static int countCompleteComponentsII(int n, int[][] edges) {
        // Adjacency lists for each vertex
        List<Integer>[] graph = new ArrayList[n];

        // Initialize empty adjacency lists
        for (int vertex = 0; vertex < n; vertex++) {
            graph[vertex] = new ArrayList<>();
        }

        // Build adjacency lists from edges
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int completeCount = 0;
        Set<Integer> visited = new HashSet<>();

        // Process each unvisited vertex
        for (int vertex = 0; vertex < n; vertex++) {
            if (visited.contains(vertex)) continue;

            // arr[0] = vertices count, arr[1] = total edges count
            int[] componentInfo = new int[2];
            dfs(vertex, graph, visited, componentInfo);

            // Check if component is complete - edges should be vertices * (vertices-1)
            if (componentInfo[0] * (componentInfo[0] - 1) == componentInfo[1]) {
                completeCount++;
            }
        }
        return completeCount;
    }

    private static void dfs(
            int curr,
            List<Integer>[] graph,
            Set<Integer> visited,
            int[] componentInfo
    ) {
        visited.add(curr);
        componentInfo[0]++; // Increment vertex count
        componentInfo[1] += graph[curr].size(); // Add edges from current vertex

        // Explore unvisited neighbors
        for (int next : graph[curr]) {
            if (!visited.contains(next)) {
                dfs(next, graph, visited, componentInfo);
            }
        }
    }


    // BFS tc & sc O(n + m)
    static int countCompleteComponentsI(int n, int[][] edges) {
        // Create adjacency list representation of the graph
        List<Integer>[] graph = new ArrayList[n];
        for (int vertex = 0; vertex < n; vertex++) {
            graph[vertex] = new ArrayList<>();
        }

        // Build graph from edges
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        // Process each unvisited vertex
        for (int vertex = 0; vertex < n; vertex++) {
            if (!visited[vertex]) {
                // BFS to find all vertices in the current component
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(vertex);
                visited[vertex] = true;

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    component.add(current);

                    // Process neighbors
                    for (int neighbor : graph[current]) {
                        if (!visited[neighbor]) {
                            queue.add(neighbor);
                            visited[neighbor] = true;
                        }
                    }
                }

                // Check if component is complete (all vertices have the right number of edges)
                boolean isComplete = true;
                for (int node : component) {
                    if (graph[node].size() != component.size() - 1) {
                        isComplete = false;
                        break;
                    }
                }

                if (isComplete) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }


    // disjoint set tc O(n+mα(n)) sc O(n)
    static int countCompleteComponents(int n, int[][] edges) {
        // Initialize Union Find and edge counter
        UnionFind dsu = new UnionFind(n);
        Map<Integer, Integer> edgeCount = new HashMap<>();

        // Connect components using edges
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        // Count edges in each component
        for (int[] edge : edges) {
            int root = dsu.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        // Check if each component is complete
        int completeCount = 0;
        for (int vertex = 0; vertex < n; vertex++) {
            if (dsu.find(vertex) == vertex) { // If vertex is root
                int nodeCount = dsu.size[vertex];
                int expectedEdges = (nodeCount * (nodeCount - 1)) / 2;
                if (edgeCount.getOrDefault(vertex, 0) == expectedEdges) {
                    completeCount++;
                }
            }
        }
        return completeCount;
    }

    private static class UnionFind {

        int[] parent;
        int[] size; // Tracks size of each component

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);
        }

        // Find root of component with path compression
        int find(int node) {
            if (parent[node] == -1) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        // Union by size
        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) {
                return;
            }

            // Merge smaller component into larger one
            if (size[root1] > size[root2]) {
                parent[root2] = root1;
                size[root1] += size[root2];
            } else {
                parent[root1] = root2;
                size[root2] += size[root1];
            }
        }

    }
}