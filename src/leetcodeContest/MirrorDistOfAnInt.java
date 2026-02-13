package leetcodeContest;

public class MirrorDistOfAnInt {
    public static void main(String[] args) {
        int n = 25;
        System.out.println(mirrorDistance(n));
    }

    static int mirrorDistance(int n) {
        return Math.abs(n - rev(n));
//return rev(n);
    }

    private static int rev(int n) {
        int ans = 0;
        while (n > 0) {
            int rem = n % 10;
            ans = (ans * 10) + rem;
            n = n / 10;
        }
        return ans;
    }
}
