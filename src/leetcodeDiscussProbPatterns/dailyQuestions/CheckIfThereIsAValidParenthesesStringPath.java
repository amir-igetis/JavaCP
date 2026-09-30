package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckIfThereIsAValidParenthesesStringPath {
    public static void main(String[] args) {
        char[][] grid = {
                {'(', '(', '('},
                {')', '(', ')'},
                {'(', '(', ')'},
                {'(', '(', ')'}
        };
        System.out.println(hasValidPath(grid));
    }

    // dp

    /// Let W be the machine word size.
    ///
    /// Time complexity: O((nm(n+m))/w) or O(nm(n+m)).
    ///
    /// Suppose the input grid is an n×m matrix. There are n⋅m cells, and each cell has at most n+m states. The time complexity of these transitions depends on the implementation; see the State Transition Implementation Details section above.
    ///
    /// Space complexity: O(nm(n+m)).
    ///
    /// This is the space cost for the dynamic programming array.
    static boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int pathLen = n + m - 1;

        if (pathLen % 2 == 1) {
            return false;
        }
        if (grid[0][0] != '(' || grid[n - 1][m - 1] != ')') {
            return false;
        }

        boolean[][][] dp = new boolean[n][m][pathLen + 1];

        dp[0][0][1] = true;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int change = grid[i][j] == '(' ? 1 : -1;

                if (i > 0) {
                    for (int balance = 0; balance <= pathLen; ++balance) {
                        if (!dp[i - 1][j][balance]) {
                            continue;
                        }

                        int next = balance + change;

                        if (next >= 0) {
                            dp[i][j][next] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int balance = 0; balance <= pathLen; ++balance) {
                        if (!dp[i][j - 1][balance]) {
                            continue;
                        }

                        int next = balance + change;

                        if (next >= 0) {
                            dp[i][j][next] = true;
                        }
                    }
                }
            }
        }
        return dp[n - 1][m - 1][0];
    }
}
