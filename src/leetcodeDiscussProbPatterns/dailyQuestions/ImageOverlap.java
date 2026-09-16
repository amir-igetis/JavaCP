package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class ImageOverlap {
    public static void main(String[] args) {
        int[][] img1 = {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, img2 = {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
        System.out.println(largestOverlap(img1, img2));
    }

    /// I first walk through both images and collect the coordinates of every cell that holds a 1. Call the lists A for img1 and B for img2.
    ///
    /// Then I consider every pair (a from A, b from B). The shift that would move a exactly onto b is simply
    ///
    /// dx = b.row - a.row
    /// dy = b.col - a.col
    ///
    /// I keep a counter of how many pairs produce each (dx, dy). The highest count I ever see is the answer, because that many 1s will land on top of each other under that exact shift. Bits that slide outside the matrix simply never appear in any pair, so they are automatically ignored.
    ///
    /// Here is the small example from the problem worked out by hand:
    ///
    /// img1 1s: (0,0) (0,1) (1,1) (2,1)
    /// img2 1s: (1,1) (1,2) (2,2)
    ///
    /// Pairs and the shifts they need:
    ///
    /// (0,0) -> (1,1) : dx=1, dy=1
    /// (0,0) -> (1,2) : dx=1, dy=2
    /// (0,0) -> (2,2) : dx=2, dy=2
    /// (0,1) -> (1,1) : dx=1, dy=0
    /// (0,1) -> (1,2) : dx=1, dy=1
    /// (0,1) -> (2,2) : dx=2, dy=1
    /// (1,1) -> (1,1) : dx=0, dy=0
    /// (1,1) -> (1,2) : dx=0, dy=1
    /// (1,1) -> (2,2) : dx=1, dy=1
    /// (2,1) -> (1,1) : dx=-1, dy=0
    /// (2,1) -> (1,2) : dx=-1, dy=1
    /// (2,1) -> (2,2) : dx=0, dy=1
    ///
    /// Counting the shifts:
    ///
    /// (1,1) appears three times
    /// everything else appears once
    ///
    /// So the largest overlap is 3, which matches the official answer. The same shift (1,1) lines up three different pairs of 1s at once.
    static int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        // collect every coordinate that holds a 1
        List<int[]> A = new ArrayList<>();
        List<int[]> B = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (img1[i][j] == 1) A.add(new int[]{i, j});
                if (img2[i][j] == 1) B.add(new int[]{i, j});
            }
        }
        int[][] cnt = new int[2 * n][2 * n];
        int best = 0;
        for (int[] a : A) {
            for (int[] b : B) {
                int dx = b[0] - a[0] + n;
                int dy = b[1] - a[1] + n;
                best = Math.max(best, ++cnt[dx][dy]);
            }
        }
        return best;
    }
}
