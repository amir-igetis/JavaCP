package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class CreateBTFromDescription {
    public static void main(String[] args) {
        int[][] description = {{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}};
        TreeNode root = createBinaryTreeII(description);
        printList(root);
    }

    // Constructing Tree From Directly Map and TreeNode Object tc & sc O(n)
    static TreeNode createBinaryTree(int[][] descriptions) {
        // Maps values to TreeNode pointers
        Map<Integer, TreeNode> nodeMap = new HashMap<>();

        // Stores values which are children in the descriptions
        Set<Integer> children = new HashSet<>();

        // Iterate through descriptions to create nodes and set up tree structure
        for (int[] description : descriptions) {
            // Extract parent value, child value, and whether it is a
            // left child (1) or right child (0)
            int parentValue = description[0];
            int childValue = description[1];
            boolean isLeft = description[2] == 1;

            // Create parent and child nodes if not already created
            if (!nodeMap.containsKey(parentValue)) {
                nodeMap.put(parentValue, new TreeNode(parentValue));
            }
            if (!nodeMap.containsKey(childValue)) {
                nodeMap.put(childValue, new TreeNode(childValue));
            }

            // Attach child node to parent's left or right branch
            if (isLeft) {
                nodeMap.get(parentValue).left = nodeMap.get(childValue);
            } else {
                nodeMap.get(parentValue).right = nodeMap.get(childValue);
            }

            // Mark child as a child in the set
            children.add(childValue);
        }

        // Find and return the root node
        for (TreeNode node : nodeMap.values()) {
            if (!children.contains(node.val)) {
                return node; // Root node found
            }
        }

        return null; // Should not occur according to problem statement
    }

    // Convert to Graph with Depth First Search tc & sc O(n)
    static TreeNode createBinaryTreeI(int[][] descriptions) {
        // Step 1: Organize data
        Map<Integer, List<int[]>> parentToChildren = new HashMap<>();
        Set<Integer> allNodes = new HashSet<>();
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parent = desc[0];
            int child = desc[1];
            int isLeft = desc[2];

            // Store child information under parent node
            if (!parentToChildren.containsKey(parent)) {
                parentToChildren.put(parent, new ArrayList<>());
            }
            parentToChildren.get(parent).add(new int[]{child, isLeft});
            allNodes.add(parent);
            allNodes.add(child);
            children.add(child);
        }

        // Step 2: Find the root
        int rootVal = 0;
        for (int node : allNodes) {
            if (!children.contains(node)) {
                rootVal = node;
                break;
            }
        }

        // Step 3 & 4: Build the tree using DFS
        return dfs(parentToChildren, rootVal);
    }

    // DFS function to recursively build binary tree
    private static TreeNode dfs(Map<Integer, List<int[]>> parentToChildren, int val) {
        // Create new TreeNode for current value
        TreeNode node = new TreeNode(val);

        // If current node has children, recursively build them
        if (parentToChildren.containsKey(val)) {
            for (int[] childInfo : parentToChildren.get(val)) {
                int child = childInfo[0];
                int isLeft = childInfo[1];

                // Attach child node based on isLeft flag
                if (isLeft == 1) {
                    node.left = dfs(parentToChildren, child);
                } else {
                    node.right = dfs(parentToChildren, child);
                }
            }
        }

        return node;
    }

    // Convert to Graph with Breadth First Search tc & sc O(n)
    static TreeNode createBinaryTreeII(int[][] descriptions) {
        // Sets to track unique children and parents
        Set<Integer> children = new HashSet<>(), parents = new HashSet<>();
        // Map to store parent to children relationships
        Map<Integer, List<int[]>> parentToChildren = new HashMap<>();

        // Build graph from parent to child, and add nodes to HashSets
        for (int[] d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            parents.add(parent);
            parents.add(child);
            children.add(child);
            parentToChildren
                    .computeIfAbsent(parent, l -> new ArrayList<>())
                    .add(new int[]{child, isLeft});
        }

        // Find the root node by checking which node is in parents but not in children
        parents.removeAll(children);
        TreeNode root = new TreeNode(parents.iterator().next());

        // Starting from root, use BFS to construct binary tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            // Iterate over children of current parent
            for (int[] childInfo : parentToChildren.getOrDefault(
                    parent.val,
                    Collections.emptyList()
            )) {
                int childValue = childInfo[0], isLeft = childInfo[1];
                TreeNode child = new TreeNode(childValue);
                queue.offer(child);
                // Attach child node to its parent based on isLeft flag
                if (isLeft == 1) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        }

        return root;
    }


    private static void printList(TreeNode root) {
        if (root != null) {
            printList(root.left);
            System.out.print(root.val + " ");
            printList(root.right);
        }
    }

}
