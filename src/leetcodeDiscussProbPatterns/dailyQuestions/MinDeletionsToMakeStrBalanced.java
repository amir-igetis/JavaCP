package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class MinDeletionsToMakeStrBalanced {
    public static void main(String[] args) {
        String s = "aababbab";
        System.out.println(minimumDeletionsIII(s));
    }

    //    three pass count method tc & sc O(n)
    static int minimumDeletions(String s) {
        int n = s.length();
        int[] countA = new int[n];
        int[] countB = new int[n];
        int bCount = 0;
        for (int i = 0; i < n; i++) {
            countB[i] = bCount;
            if (s.charAt(i) == 'b')
                bCount++;
        }
        int aCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            countA[i] = aCount;
            if (s.charAt(i) == 'a')
                aCount++;
        }
        int minDel = n;
        for (int i = 0; i < n; i++) {
            minDel = Math.min(minDel, countA[i] + countB[i]);
        }
        return minDel;
    }


    //    combined pass method tc & sc O(n)
    static int minimumDeletionsI(String s) {
        int n = s.length();
        int[] countA = new int[n];
        int aCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            countA[i] = aCount;
            if (s.charAt(i) == 'a')
                aCount++;
        }
        int minDels = n;
        int bCount = 0;
        for (int i = 0; i < n; i++) {
            minDels = Math.min(countA[i] + bCount, minDels);
            if (s.charAt(i) == 'b')
                bCount++;
        }
        return minDels;
    }

    //    two variable method tc O(n) & sc O(1)
    static int minimumDeletionsII(String s) {
        int n = s.length();
        int aCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a')
                aCount++;
        }
        int bCount = 0;
        int minDels = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a')
                aCount--;
            minDels = Math.min(minDels, aCount + bCount);
            if (s.charAt(i) == 'b')
                bCount++;
        }
        return minDels;
    }

    //    one pass stack method tc O(n) & sc O(n)
    static int minimumDeletionsIII(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();
        int delCount = 0;
        for (int i = 0; i < n; i++) {
            if (!st.isEmpty() && st.peek() == 'b'
                    && s.charAt(i) == 'a') {
                st.pop();
                delCount++;
            } else {
                st.push(s.charAt(i));
            }
        }
        return delCount;
    }

}
