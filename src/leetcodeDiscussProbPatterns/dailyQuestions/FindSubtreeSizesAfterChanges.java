package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class FindSubtreeSizesAfterChanges {
    public static void main(String[] args) {
        int[] parent = new int[]{-1, 0, 0, 1, 1, 1};
        String s = "abaabc";
        System.out.println(Arrays.toString(findSubtreeSizes(parent, s)));
        System.out.println(Arrays.toString(findSubtreeSizesI(parent, s)));

    }

    /// Time O(n): every node is entered and left once.
    /// Space O(n) for the children lists and the recursion.
    static int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> children = new ArrayList<>();
        for (int x = 0; x < n; x++)
            children.add(new ArrayList<>());
        for (int x = 1; x < n; x++)
            children.get(parent[x]).add(x);
        int[] size = new int[n], above = new int[n], last = new int[26];  // last[c]: the deepest node with letter c on the path from the root
        Arrays.fill(size, 1);
        Arrays.fill(last, -1);
        Deque<Integer> stack = new ArrayDeque<>(List.of(0));  // x enters x, ~x leaves it: no recursion, so no stack overflow on a deep tree
        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (x >= 0) {
                int c = s.charAt(x) - 'a';
                above[x] = last[c];  // the closest ancestor with the same letter, or -1
                last[c] = x;
                stack.push(~x);  // leave x after all its children
                for (int child : children.get(x))
                    stack.push(child);
            } else {
                x = ~x;
                last[s.charAt(x) - 'a'] = above[x];
                if (x > 0)  // the root has no parent
                    size[above[x] >= 0 ? above[x] : parent[x]] += size[x];
            }
        }
        return size;

    }

    // Using Array
    static int[] findSubtreeSizesI(int[] parent, String s) {
        int n = parent.length;
        int[] firstChild = new int[n], nextSibling = new int[n];  // children as a linked list in two int arrays
        Arrays.fill(firstChild, -1);
        for (int x = n - 1; x > 0; x--) {
            nextSibling[x] = firstChild[parent[x]];
            firstChild[parent[x]] = x;
        }
        int[] size = new int[n], above = new int[n], last = new int[26], stack = new int[2 * n];  // last[c]: the deepest node with letter c on the path from the root
        Arrays.fill(size, 1);
        Arrays.fill(last, -1);
        int top = 0;
        stack[top++] = 0;  // x enters x, ~x leaves it: no recursion
        while (top > 0) {
            int x = stack[--top];
            if (x >= 0) {
                int c = s.charAt(x) - 'a';
                above[x] = last[c];  // the closest ancestor with the same letter, or -1
                last[c] = x;
                stack[top++] = ~x;  // leave x after all its children
                for (int child = firstChild[x]; child != -1; child = nextSibling[child])
                    stack[top++] = child;
            } else {
                x = ~x;
                last[s.charAt(x) - 'a'] = above[x];
                if (x > 0)  // the root has no parent
                    size[above[x] >= 0 ? above[x] : parent[x]] += size[x];
            }
        }
        return size;
    }
}
