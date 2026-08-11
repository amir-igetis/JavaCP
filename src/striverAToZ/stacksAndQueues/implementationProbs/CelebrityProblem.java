package striverAToZ.stacksAndQueues.implementationProbs;

public class CelebrityProblem {

    /// Question 3
    /// Problem Statement: A celebrity is a person who is known by everyone else at the party but does not know anyone in return. Given a square matrix M of size N x N where M(i)(j) is 1 if person i knows person j, and 0 otherwise, determine if there is a celebrity at the party. Return the index of the celebrity or -1 if no such person exists.
    ///
    /// Note that M(i)(i)is always 0.
    public static void main(String[] args) {
        int[][] M = {
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 1, 0}
        };
        int ans = celebrity(M);

        // Print the result (index of the celebrity)
        System.out.println("The index of the celebrity is: " + ans);
    }

    // brute force

    /// Time Complexity: O(N²), since we are using two nested loops to traverse the square matrix to populate the lists.
    ///
    /// Space Complexity: O(N), since we are using two lists of size N to store the count of how many people each person knows and how many people know each person.
    static int celebrity(int[][] M) {
        // Size of the given matrix
        int n = M.length;

        // To store count of people who know person of index i
        int[] knowMe = new int[n];

        // To store count of people who the person of index i knows
        int[] Iknow = new int[n];

        // Traverse the matrix to calculate knowMe and Iknow
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // If person i knows person j
                if (M[i][j] == 1) {
                    knowMe[j]++;  // Person j is known by person i
                    Iknow[i]++;   // Person i knows person j
                }
            }
        }

        // Traverse all persons to find the celebrity
        for (int i = 0; i < n; i++) {
            // If person i knows no one and is known by everyone else
            if (knowMe[i] == n - 1 && Iknow[i] == 0) {
                return i;  // Person i is the celebrity
            }
        }

        // Return -1 if no celebrity is found
        return -1;
    }

    // optimal

    /// Time Complexity: O(N), since eliminating persons and checking if the last candidate is a celebrity both take O(N) time.
    ///
    /// Space Complexity: O(1), since we are using only a couple of variables.
    static int celebrityI(int[][] M) {
        // Size of the given matrix
        int n = M.length;

        // Top and Down pointers for narrowing the possible celebrity
        int top = 0, down = n - 1;

        // Traverse for all the people to find potential celebrity
        while (top < down) {
            // If top knows down, top cannot be a celebrity
            if (M[top][down] == 1) {
                top = top + 1;
            }
            // If down knows top, down cannot be a celebrity
            else if (M[down][top] == 1) {
                down = down - 1;
            }
            // If neither knows each other, both are not the celebrity
            else {
                top++;
                down--;
            }
        }

        // If top exceeds down, no celebrity is found
        if (top > down) return -1;

        // Check if the person pointed by top is a celebrity
        for (int i = 0; i < n; i++) {
            if (i == top) continue; // Skip checking the person itself

            // If top knows someone or someone doesn't know top, it's not a celebrity
            if (M[top][i] == 1 || M[i][top] == 0) {
                return -1;
            }
        }

        // Return the index of the celebrity
        return top;
    }
}

