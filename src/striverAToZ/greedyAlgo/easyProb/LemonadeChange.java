package striverAToZ.greedyAlgo.easyProb;

public class LemonadeChange {
    public static void main(String[] args) {
        int N = 5;
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println(lemonadeChange(N, bills));
    }

    // soln for https://leetcode.com/problems/lemonade-change/description/
    // soln for https://practice.geeksforgeeks.org/problems/lemonade-change/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=lemonade-change
    static boolean lemonadeChange(int N, int bills[]) {
        int fives = 0;
        int tens = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fives++;
            } else if (bills[i] == 10) {
                tens++;
                fives--;
            } else if (tens > 0) {
                tens--;
                fives--;
            } else {
                fives -= 3;
            }
            if (fives < 0) {
                return false;
            }
        }
        return true;
    }

}
