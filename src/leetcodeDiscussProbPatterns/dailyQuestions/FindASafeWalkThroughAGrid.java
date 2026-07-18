package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FindASafeWalkThroughAGrid {
    public static void main(String[] args) {
        List<List<Integer>> grid = List.of(
                List.of(0, 1, 0, 0, 0),
                List.of(0, 1, 0, 1, 0),
                List.of(0, 0, 0, 1, 0));
        int health = 1;
        System.out.println(findSafeWalk(grid, health));
    }


    // dijkstra tc & sc O(mn)
    private static final int[][] DIRS = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    static boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dis = new int[m][n];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque
                <int[]> q = new ArrayDeque<>();
        q.offerFirst(new int[]{0, 0});
        dis[0][0] = grid.get(0).get(0);

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int cx = cur[0];
            int cy = cur[1];
            // the first time it leaves the queue, the shortest distance is guaranteed
            if (cx == m - 1 && cy == n - 1) {
                return true;
            }

            for (int[] dir : DIRS) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                int cost = dis[cx][cy] + grid.get(nx).get(ny);
                // pruning: the new distance does not meet health requirements
                if (cost >= health) {
                    continue;
                }
                if (cost < dis[nx][ny]) {
                    dis[nx][ny] = cost;
                    if (grid.get(nx).get(ny) == 0) {
                        q.offerFirst(new int[]{nx, ny});
                    } else {
                        q.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return false;

    }
}
