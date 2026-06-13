package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class FindThePrefixCommonArrOfTwoArr {
    public static void main(String[] args) {
        int[] A = {1, 3, 2, 4}, B = {3, 1, 2, 4};
        System.out.println(Arrays.toString(findThePrefixCommonArray(A, B)));
    }

    //    single pass with freq arr tc and sc O(n)
    static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] prefixCommonArr = new int[n];
        int[] freq = new int[n + 1];
        int commonCount = 0;
        for (int currIdx = 0; currIdx < n; currIdx++) {
            freq[A[currIdx]] += 1;
            if (freq[A[currIdx]] == 2)
                commonCount++;
            freq[B[currIdx]] += 1;
            if (freq[B[currIdx]] == 2)
                commonCount++;

            prefixCommonArr[currIdx] = commonCount;
        }
        return prefixCommonArr;
    }
}
