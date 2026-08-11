package striverAToZ.stacksAndQueues.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElemII {

    /// Questions 2
    /// Problem Statement: Given a circular integer array arr, return the next greater element for every element in arr.
    /// The next greater element for an element x is the first element greater than x that we come across while traversing the array in a clockwise manner.
    /// If it doesn't exist, return -1 for that element element.

    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 7, 6, 0};

        // Call function
        int[] ans = nextGreaterElements(arr);

        System.out.println("The next greater elements are: ");
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    /// Time Complexity: O(N2), since we are using two nested for loops to find the next greater elements.
    ///
    /// Space Complexity: O(N), The space required to store the answer is O(N).
    static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;

        // To store the next greater elements
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Traverse each element to find its next greater
        for (int i = 0; i < n; i++) {
            int currEle = arr[i];

            // Look ahead in circular manner
            for (int j = 1; j < n; j++) {
                int ind = (i + j) % n;

                if (arr[ind] > currEle) {
                    ans[i] = arr[ind];
                    break;
                }
            }
        }

        return ans;
    }

    /// Time Complexity: O(N), since traversing on the array takes O(N) time and traversing the stack will take overall O(N) time as all the elements are pushed in the stack once.
    ///
    /// Space Complexity: O(N), since the answer array takes O(N) space and the space used by stack will be O(N) in the worst case.
    static int[] nextGreaterElementsI(int[] arr) {
        int n = arr.length;

        // To store the next greater elements
        int[] ans = new int[n];

        // Stack to help find next greater elements in reverse traversal
        Stack<Integer> st = new Stack<>();

        // Traverse the array twice in reverse to simulate circular array
        for (int i = 2 * n - 1; i >= 0; i--) {
            int ind = i % n;
            int currEle = arr[ind];

            // Pop all elements from stack that are less than or equal to current
            while (!st.isEmpty() && st.peek() <= currEle) {
                st.pop();
            }

            // Only fill result in the first pass (i < n)
            if (i < n) {
                if (st.isEmpty()) {
                    ans[ind] = -1;
                } else {
                    ans[ind] = st.peek();
                }
            }

            // Push current element to stack
            st.push(currEle);
        }

        return ans;
    }
}
