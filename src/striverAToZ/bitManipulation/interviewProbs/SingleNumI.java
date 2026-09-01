package striverAToZ.bitManipulation.interviewProbs;

public class SingleNumI {

    /// Question 2
    ///
    /// Problem Statement: Given a non-empty array of integers arr, every element appears twice except for one. Find that single one.
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElement(arr);

        System.out.println("The single element is: " + ans);

    }

    /// Time Complexity: O(N*N), since nested for loops are used
    ///
    /// Space Complexity: O(1). No extra space used
    static int getSingleElement(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int count = 0;

            // Count how many times num occurs
            for (int j = 0; j < n; j++) {
                if (arr[j] == num)
                    count++;
            }

            // If only once, return it
            if (count == 1) return num;
        }

        return -1; // fallback, won't be hit if array has a single element
    }

    // better

    /// Time Complexity: O(N)+O(N)+O(N), where N = size of the array. One O(N) is for finding the maximum, the second one is to hash the elements and the third one is to search the single element in the array.
    ///
    /// Space Complexity: O(maxElement+1) where maxElement = the maximum element of the array.
    static int getSingleElementI(int[] arr) {
        int n = arr.length;

        // Step 1: Find maximum element
        int maxi = arr[0];
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i]);
        }

        // Step 2: Create frequency array of size maxi+1
        int[] hash = new int[maxi + 1];

        // Step 3: Count frequencies
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        // Step 4: Find element with frequency = 1
        for (int i = 0; i < n; i++) {
            if (hash[arr[i]] == 1)
                return arr[i];
        }

        return -1; // fallback
    }

    // optimal

    /// Time Complexity: O(N). Where N is the size of the array
    ///
    /// Space Complexity: O(1). No extra space used
    static int getSingleElementII(int[] arr) {
        int xorr = 0;

        // XOR all elements — duplicates cancel each other out
        for (int num : arr) {
            xorr ^= num;
        }

        return xorr;
    }
}
