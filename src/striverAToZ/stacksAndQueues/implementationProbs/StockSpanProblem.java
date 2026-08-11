package striverAToZ.stacksAndQueues.implementationProbs;

import java.util.Stack;

public class StockSpanProblem {

    /// Question 2
    /// Problem Statement: Given an array arr of size n, where each element arr(i) represents the stock price on day i. Calculate the span of stock prices for each day.
    ///
    /// The span Sᵢ for a specific day i is defined as the maximum number of consecutive previous days (including the current day) for which the stock price was less than or equal to the price on day i.

    public static void main(String[] args) {
        int n = 7; // Number of days
        int[] arr = {120, 100, 60, 80, 90, 110, 115}; // Stock prices for each day

        int[] ans = stockSpan(arr, n);

        // Print the span of stock prices
        System.out.print("The span of stock prices is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    // brute force

    /// Time Complexity: O(N²), since we are using two nested loops.
    ///
    /// Space Complexity: O(1), since we are using only a couple of variables.
    static int[] stockSpan(int[] arr, int n) {
        // To store the answer (stock span for each day)
        int[] ans = new int[n];

        // Traverse through the array
        for (int i = 0; i < n; i++) {
            // To store the current span of stocks
            int currSpan = 0;

            // Traverse backwards to find stock span
            for (int j = i; j >= 0; j--) {
                // Update stock span if the current price is less than or equal to current price
                if (arr[j] <= arr[i]) {
                    currSpan++;
                }
                // Else, break the loop when a higher stock price is found
                else {
                    break;
                }
            }

            // Store the current span in the result array
            ans[i] = currSpan;
        }

        // Return the computed stock span for each day
        return ans;
    }

    // optimal

    /// Time Complexity: O(N), since finding the indices of previous greater elements takes O(N) time and we traverse the array once to compute the stock span, that takes O(N) as well.
    ///
    /// Space Complexity: O(N), the stack space used to find the previous greater elements can go up to O(N) in the worst case.

        /* Function to find the indices of the previous
        greater element for each element in the array */
    private static int[] findPGE(int[] arr) {
        int n = arr.length; // Size of the array

        // To store the previous greater elements
        int[] ans = new int[n];

        // Stack to get elements in LIFO fashion
        Stack<Integer> st = new Stack<>();

        // Start traversing from the front
        for (int i = 0; i < n; i++) {
            // Get the current element
            int currEle = arr[i];

            // Pop elements from the stack until we find a greater element
            while (!st.isEmpty() && arr[st.peek()] <= currEle) {
                st.pop();
            }

            // If the stack is empty, it means there's no greater element, so assign -1
            if (st.isEmpty())
                ans[i] = -1;
            else
                ans[i] = st.peek(); // Otherwise, the top element is the previous greater

            // Push the current index in the stack
            st.push(i);
        }

        // Return the result (indices of previous greater elements)
        return ans;
    }

    // Function to find the span of stock prices for each day
    static int[] stockSpanI(int[] arr, int n) {
        // Get the indices of previous greater elements
        int[] PGE = findPGE(arr);

        // To store the final span results
        int[] ans = new int[n];

        // Compute the span for each element using the previous greater element indices
        for (int i = 0; i < n; i++) {
            ans[i] = i - PGE[i]; // Calculate span based on previous greater element
        }

        // Return the result (stock span for each day)
        return ans;
    }

}
