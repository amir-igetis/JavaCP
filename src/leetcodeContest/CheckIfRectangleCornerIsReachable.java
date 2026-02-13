package leetcodeContest;

import java.util.ArrayDeque;
import java.util.Arrays;

public class CheckIfRectangleCornerIsReachable {
    public static void main(String[] args) {
        int X = 3, Y = 3;
        int[][] circles = {{1, 1, 2}};
        System.out.println(canReachCorner(X, Y, circles));
    }

//    soln from uwi
    static boolean canReachCornerI(int X, int Y, int[][] circles) {
        int m = circles.length;
        DJSet ds = new DJSet(m+2);
        for(int i = 0;i < m;i++){
            for(int j = i+1;j < m;j++){
                if(intersect(circles[i], circles[j])){
                    ds.unite(i, j);
                }
            }
            if(circles[i][0] - circles[i][2] <= 0){
                ds.unite(i, m);
            }
            if(circles[i][0] + circles[i][2] >= X){
                ds.unite(i, m+1);
            }
            if(circles[i][1] - circles[i][2] <= 0){
                ds.unite(i, m+1);
            }
            if(circles[i][1] + circles[i][2] >= Y){
                ds.unite(i, m);
            }
        }
        return !ds.equiv(m, m+1);
    }

    static boolean intersect(int[] a, int[] b)
    {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        int r = a[2] + b[2];
        return dx*dx + dy*dy <= r*r;
    }

    static boolean canReachCorner(int X, int Y, int[][] circles) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < circles.length; i++) {
            if (circles[i][0] - circles[i][2] <= 0 || circles[i][1] + circles[i][2] >= Y) {
                deque.offer(circles[i]);
                circles[i] = null;
            }
        }
        for (int[] circle; !deque.isEmpty(); ) {
            if ((circle = deque.poll())[0] + circle[2] >= X || circle[1] - circle[2] <= 0)
                return false;
            for (int i = 0; i < circles.length; i++) {
                if (circles[i] != null && Math.abs(circle[0] - circles[i][0]) *
                        Math.abs(circle[0] - circles[i][0]) + Math.abs(circle[1] - circles[i][1]) *
                        Math.abs(circle[1] - circles[i][1]) <= (circle[2] + circles[i][2]) * (circle[2] + circles[i][2])) {
                    deque.offer(circles[i]);
                    circles[i] = null;
                }
            }
        }
        return true;
    }
}

//public static
class DJSet {
    public int[] upper;

    public DJSet(int n) {
        upper = new int[n];
        Arrays.fill(upper, -1);
    }

    public int root(int x) {
        return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
    }

    public boolean equiv(int x, int y) {
        return root(x) == root(y);
    }

    public boolean unite(int x, int y) {
        x = root(x);
        y = root(y);
        if (x != y) {
            if (upper[y] < upper[x]) {
                int d = x;
                x = y;
                y = d;
            }
            upper[x] += upper[y];
            upper[y] = x;
        }
        return x == y;
    }

    public int count() {
        int ct = 0;
        for (int u : upper) if (u < 0) ct++;
        return ct;
    }

    public int[][] toBucket() {
        int n = upper.length;
        int[][] ret = new int[n][];
        int[] rp = new int[n];
        for (int i = 0; i < n; i++) if (upper[i] < 0) ret[i] = new int[-upper[i]];
        for (int i = 0; i < n; i++) {
            int r = root(i);
            ret[r][rp[r]++] = i;
        }
        return ret;
    }
}