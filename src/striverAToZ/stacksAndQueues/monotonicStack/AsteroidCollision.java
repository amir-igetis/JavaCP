package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.ArrayList;
import java.util.List;

public class AsteroidCollision {

    /// Problem Statement: Given an array of integers asteroids, where each integer represents an asteroid in a row, determine the state of the asteroids after all collisions. In this array, the absolute value represents the size of the asteroid, and the sign represents its direction (positive meaning right and negative meaning left). All asteroids move at the same speed.
    ///
    /// When two asteroids meet, the smaller one will explode. If they are the same size, both will explode. Asteroids moving in the same direction will never meet.
    public static void main(String[] args) {
// Input array representing asteroid directions and sizes
        int[] arr = {10, 20, -10};

        int[] ans = asteroidCollision(arr);

        // Print the final state of asteroids
        System.out.print("The state of asteroids after collisions is: ");
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }

    }

    /// Time Complexity: O(N), since traversing all the asteroids takes O(N) time.
    ///
    /// Space Complexity: O(N), since in the worst case, all asteroids will be stored in the stack if there are no collisions, leading to a space requirement of O(N).
    static int[] asteroidCollision(int[] asteroids) {
        // Size of the array
        int n = asteroids.length;

        // List acting as a stack to store surviving asteroids
        List<Integer> st = new ArrayList<>();

        // Traverse through each asteroid
        for (int i = 0; i < n; i++) {

            // If asteroid is moving right, push it to the stack
            if (asteroids[i] > 0) {
                st.add(asteroids[i]);
            }

            // If asteroid is moving left, handle possible collisions
            else {
                // Destroy all smaller right-moving asteroids
                while (!st.isEmpty() && st.get(st.size() - 1) > 0 &&
                        st.get(st.size() - 1) < Math.abs(asteroids[i])) {
                    st.remove(st.size() - 1);
                }

                // Destroy both if sizes are equal
                if (!st.isEmpty() && st.get(st.size() - 1) == Math.abs(asteroids[i])) {
                    st.remove(st.size() - 1);
                }

                // If top of stack is a left-moving or no asteroid, add this one
                else if (st.isEmpty() || st.get(st.size() - 1) < 0) {
                    st.add(asteroids[i]);
                }
            }
        }

        // Convert the list to an array
        int[] result = new int[st.size()];
        for (int i = 0; i < st.size(); i++) {
            result[i] = st.get(i);
        }

        // Return the final state of asteroids
        return result;
    }
}