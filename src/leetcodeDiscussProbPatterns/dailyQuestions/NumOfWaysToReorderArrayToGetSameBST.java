package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumOfWaysToReorderArrayToGetSameBST {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        System.out.println(numOfWays(nums));
    }

    // recursion
    /// Let m be the size of nums.
    ///
    /// Time complexity: O(m^2)
    ///
    /// In Java or C++, a table of Pascal's triangle of size m×m is built, which takes O(m ^2) time.
    ///
    /// dfs(nums) recursively calls itself to process the left and right subtrees of the current node nums[0]. Since the total size of the subtrees decreases by 1 at each level of the recursion, the maximum height of the recursion tree is m. Thus the total time complexity of the recursive solution is O(m^2) because in each call we are doing O(m) work creating the subsequences.
    ///
    /// Space complexity: O(m^2) or O(m)
    ///
    /// In Java or C++, a table of Pascal's triangle of size m×m is built.
    /// The recursive solution uses the call stack to keep track of the current subtree being processed. The maximum depth of the call stack is equal to the height of the BST constructed from the input array. In the worst case, nums may form a degenerate BST (e.g., a sorted array), which has a height of m−1, and the stack can hold up to m−1 calls, resulting in a space complexity of O(m).
    private static long mod = (long) 1e9 + 7;
    private static long[][] table;

    static int numOfWays(int[] nums) {
        int m = nums.length;

        // Table of Pascal's triangle
        table = new long[m][m];
        for (int i = 0; i < m; ++i) {
            table[i][0] = table[i][i] = 1;
        }
        for (int i = 2; i < m; i++) {
            for (int j = 1; j < i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % mod;
            }
        }
        List<Integer> arrList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return (int) ((dfs(arrList) - 1) % mod);
    }

    private static long dfs(List<Integer> nums) {
        int m = nums.size();
        if (m < 3) {
            return 1;
        }

        List<Integer> leftNodes = new ArrayList<>();
        List<Integer> rightNodes = new ArrayList<>();
        for (int i = 1; i < m; ++i) {
            if (nums.get(i) < nums.get(0)) {
                leftNodes.add(nums.get(i));
            } else {
                rightNodes.add(nums.get(i));
            }
        }
        long leftWays = dfs(leftNodes) % mod;
        long rightWays = dfs(rightNodes) % mod;

        return (((leftWays * rightWays) % mod) * table[m - 1][leftNodes.size()]) % mod;

    }
}
