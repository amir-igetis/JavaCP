package leetcodeDiscussProbPatterns.stacks;

import java.util.*;

public class NextGreaterElemToRight {
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 3, 2, 4};
        System.out.println(Arrays.toString(NGER(n, arr)));
    }

    static int[] NGER(int n, int[] arr) {
        Stack<Integer> st = new Stack<>();
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i])
                st.pop();

            if (st.isEmpty())
                res.add(-1);
            else
                res.add(st.peek());

            st.push(arr[i]);
        }
        Collections.reverse(res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
