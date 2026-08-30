package leetcodeContest.biWeekly190;

import java.util.LinkedList;
import java.util.Queue;

public class MinBishopMovesToReachTarget {
    public static void main(String[] args) {
        int[] source = { 8, 1 }, target = { 1, 8 };
        int[] source2 = { 4, 2 }, target2 = { 1, 3 };
        int[] source3 = { 1, 1 }, target3 = { 3, 4 };
        System.out.println(minBishopMoves(source, target));
        System.out.println(minBishopMoves(source2, target2));
        System.out.println(minBishopMoves(source3, target3));
    }

    private static class Tuple {
        int x, y, moves;

        Tuple(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    static int minBishopMoves(int[] source, int[] target) {
        int sr = source[0], sc = source[1], tr = target[0], tc = target[1];
        if (sr == tr && sc == tc)
            return 0;

        int[][] vis = new int[9][9];
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(sr, sc, 0));
        vis[sr][sc] = 1;

        int[] dr = { -1, -1, 1, 1 }, dc = { -1, 1, -1, 1 };
        while (!q.isEmpty()) {
            Tuple curr = q.poll();
            int r = curr.x, c = curr.y, moves = curr.moves;
            for (int i = 0; i < 4; i++) {
                int stepMul = 1;
                while (true) {
                    int nr = r + dr[i] * stepMul, nc = c + dc[i] * stepMul;
                    if (nr < 1 || nr > 8 || nc < 1 || nc > 8)
                        break;
                    if (vis[nr][nc] == 0) {
                        vis[nr][nc] = 1;
                        if (nr == tr && nc == tc)
                            return moves + 1;
                        q.add(new Tuple(nr, nc, moves + 1));
                    }
                    stepMul++;
                }
            }
        }
        return -1;
    }

}
