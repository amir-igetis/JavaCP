package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class DestroyingAsteroids {
    public static void main(String[] args) {
        int mass = 10;
        int[] asteroids = {3, 9, 19, 5, 21};
        System.out.println(asteroidsDestroyed(mass, asteroids));
    }


    // greedy tc O(n) sc O(logN)
    static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids); // Sort by mass in ascending order
        long currentMass = mass; // Preventing integer overflow
        for (int asteroid : asteroids) {
            // Traverse the asteroids in order, attempt to destroy and update mass or return the result
            if (currentMass < asteroid) {
                return false;
            }
            currentMass += asteroid;
        }
        return true;
    }


}
